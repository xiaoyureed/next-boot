package io.github.xiaoyureed.nextbootslim.core.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BeanUtils {

    private static ObjectMapper objectMapper;

    private static Map<String, BeanCopier> cache = new ConcurrentHashMap<>();

    public BeanUtils(ObjectMapper objectMapper) {
        BeanUtils.objectMapper = objectMapper;
    }

    public static  <T> T toBean(Object source, Class<T> target) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(source), target);
        } catch (JsonProcessingException e) {
            log.error("Error of convert map to [" + target.getName() + "]", e);
            throw new RuntimeException(e);
        }
    }

    public static  <T> Map<String, Object> toMap(Object source) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(source), Map.class);
        } catch (JsonProcessingException e) {
            log.error("Error of convert bean ["+source.getClass().getName()+"] to map", e);
            throw new RuntimeException(e);
        }
    }

    public static <S, T> List<T> copy(List<S> source, Class<T> target) {
        ArrayList<T> result = new ArrayList<>(source.size());

        for (S s : source) {
            T copy = copy(s, target, null);
            result.add(copy);
        }

        return result;
    }

    public static <S, T> List<T> copy(List<S> source, Class<T> target, Converter converter) {
        ArrayList<T> result = new ArrayList<>(source.size());

        for (S s : source) {
            T copy = copy(s, target, converter);
            result.add(copy);
        }

        return result;
    }

    public static <S, T> T copy(S source, Class<T> target) {
        return copy(source, target, null);
    }

    public static <S, T> T copy(S source, Class<T> target, Converter converter) {
        var key = generateKey(source, target);

        // double check to prevent the concurrent issue
        if (!cache.containsKey(key)) {
            synchronized (BeanUtils.class) {
                if (!cache.containsKey(key)) {
                    BeanCopier beanCopier = BeanCopier.create(source.getClass(), target, converter != null);
                    cache.put(key, beanCopier);
                }
            }
        }

        T result = newInstance(target);
        cache.get(key).copy(source, result, converter);

        return result;
    }

    public static <T> T newInstance(Class<T> clazz) {
        Constructor<T> constructorNoArgs;

        try {
            constructorNoArgs = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            log.error("Class[{}] has no constructor with no args", clazz.getName());
            throw new RuntimeException(e);
        }

        T result;

        try {
            result = constructorNoArgs.newInstance();
        } catch (Exception e) {
            log.error("Error of new instance from constructor [" + constructorNoArgs.getName() + "]", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    private static String generateKey(Object source, Object target) {
        return source.getClass().getName() + target.getClass().getName();
    }
}
