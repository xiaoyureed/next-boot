package io.github.xiaoyureed.nextbootslim.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/*
 * Inside this base repository, you can place the shared data access method here
 * 
 * JpaSpecificationExecutor is dedicated to conduct complex query
 */
@NoRepositoryBean
public interface BaseRepositoryI<T extends AbstractEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    
}
