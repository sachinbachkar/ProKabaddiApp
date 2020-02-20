package com.app.kabaddi.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.stereotype.Component;

/**
 * Does LocalDateTime support Java8 & HB5 ? Convert LocalDateTime to support
 * persistence
 */
@Component("LocalDateTimeAttributeConverter")
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp>
{
  
  @Override
  public Timestamp convertToDatabaseColumn ( LocalDateTime locDateTime )
  {
    return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
  }
  
  @Override
  public LocalDateTime convertToEntityAttribute ( Timestamp sqlTimestamp )
  {
    return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
  }
}