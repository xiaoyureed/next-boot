package io.github.xiaoyureed.nextbootslim.core.jpa;

import java.util.List;

/**
 * this base service class includes the common methods to be able to shared with the normal service class
 * 
 */
public interface BaseServiceI<T extends AbstractEntity, ID> {
  
    BaseRepositoryI<T, ID> getRepository();

    default List<T> findAll() {
        return this.getRepository().findAll();
    }

    default T save(T t) {
        return this.getRepository().saveAndFlush(t);
    }
}
