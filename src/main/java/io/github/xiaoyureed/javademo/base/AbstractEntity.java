package io.github.xiaoyureed.javademo.base;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    // @CreatedDate
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @UpdateTimestamp
    // @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private LocalDate updatedAt;

}
