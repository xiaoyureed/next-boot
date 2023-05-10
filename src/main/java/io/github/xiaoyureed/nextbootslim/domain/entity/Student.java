package io.github.xiaoyureed.nextbootslim.domain.entity;

import org.hibernate.validator.constraints.Range;

import io.github.xiaoyureed.nextbootslim.core.jpa.AbstractEntity;
import io.github.xiaoyureed.nextbootslim.domain.common.enums.GenderEnum;
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
    @Range(max = 100, min = 1)
    private Integer age;

    @Column(name = "gender")
    private GenderEnum gender;
}