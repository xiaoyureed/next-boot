package io.github.xiaoyureed.javademo.base;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Note: this service represent a common service, which can not be abstract class, or the service bean would not be instantiated when the app start
 */
@Service
public class BaseService<T extends BaseEntity, ID> {
    private BaseRepository<T, ID> baseRepository;

    public BaseService(BaseRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public List<T> findAll() {
        return baseRepository.findAll();
    }

    public T save(T t) {
        return baseRepository.saveAndFlush(t);
    }
}
