package io.github.xiaoyureed.javademo.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
}
