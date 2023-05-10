//package io.github.xiaoyureed.nextbootslim.core.jpa.converter;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
//    @Override
//    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
//        return Optional.ofNullable(attribute).map(Timestamp::valueOf).orElse(null);
//    }
//
//    @Override
//    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
//        return Optional.ofNullable(dbData).map(Timestamp::toLocalDateTime).orElse(null);
//    }
//}
