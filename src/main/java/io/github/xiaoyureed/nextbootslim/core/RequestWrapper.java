package io.github.xiaoyureed.nextbootslim.core;

import java.io.Serializable;
import java.util.HashMap;

import io.github.xiaoyureed.nextbootslim.core.util.BeanUtils;
import lombok.Data;

@Data
public class RequestWrapper extends HashMap<String, Object> implements Serializable {

    public <T> T to(Class<T> clazz) {
        return BeanUtils.toBean(this, clazz);
    }

}
