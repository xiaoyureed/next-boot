package io.github.xiaoyureed.javademo.base;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import io.github.xiaoyureed.javademo.student.Student;

/*
 * Inside this base repository, you can put the shared data access method into this, and then create other repositories extend this
 */
@NoRepositoryBean
public interface RepositoryI<T extends AbstractEntity, ID> extends JpaRepository<T, ID> {
    
}
