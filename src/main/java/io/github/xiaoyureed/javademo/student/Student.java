package io.github.xiaoyureed.javademo.student;

import org.hibernate.validator.constraints.Range;

import io.github.xiaoyureed.javademo.base.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Student
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student")
public class Student extends AbstractEntity {

    @Column(nullable = false, name = "name")
    private String name;

    @Column(name = "age")
    @Range(max = 150, min = 1)
    private Integer age;

    /**
     * 1 -> male
     * 2 -> female
     */
    @Column(name = "gender")
    @Range(max = 2, min = 1)
    private Integer gender;
}