package io.github.xiaoyureed.nextbootslim.core.jpa.converter;

import io.github.xiaoyureed.nextbootslim.domain.common.enums.GenderEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderEnumConverter implements AttributeConverter<GenderEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GenderEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public GenderEnum convertToEntityAttribute(Integer dbData) {
        return GenderEnum.of(dbData);
    }

    
}
