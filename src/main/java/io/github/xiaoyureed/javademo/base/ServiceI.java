package io.github.xiaoyureed.javademo.base;

import java.util.List;

/**
 * this base service class includes the common methods to be able to shared with the normal service class
 * 
 */
public interface ServiceI<T extends AbstractEntity, ID> {
  
    RepositoryI<T, ID> getRepository();

    default List<T> findAll() {
        return this.getRepository().findAll();
    }

    default T save(T t) {
        return this.getRepository().saveAndFlush(t);
    }
}
