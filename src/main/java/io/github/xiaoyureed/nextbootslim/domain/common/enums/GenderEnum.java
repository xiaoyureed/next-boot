package io.github.xiaoyureed.nextbootslim.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    MALE(0, "男"), FEMALE(1, "女");

    private Integer code;
    private String desc;

    public static GenderEnum of(Integer code) {
        if (MALE.code.equals(code)) {
            return MALE;
        }

        if (FEMALE.code.equals(code)) {
            return FEMALE;
        }

        throw new RuntimeException("unsupported gender enum code [" + code + "]");
    }
}
