package io.github.xiaoyureed.nextbootslim.domain.entity;

import io.github.xiaoyureed.nextbootslim.core.jpa.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

//@Entity
@Table(name = "account")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Account extends AbstractEntity {
    private String name;
    private String password;
    private String email;
}
