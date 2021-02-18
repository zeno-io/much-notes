package xyz.flysium.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 全局日期时间格式配置，如果要自定义可以使用注解<code>com.fasterxml.jackson.annotation.JsonFormat</code>
 *
 * @author zeno
 */
@Configuration
public class DateFormatConfiguration {

  @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
  private String pattern;

  @Bean
  @Primary
  public ObjectMapper serializingObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    //javaTimeModule.addSerializer(Date.class, new DateSerializer());
    //javaTimeModule.addDeserializer(Date.class, new DateDeserializer());
    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    objectMapper.registerModule(javaTimeModule);
    return objectMapper;
  }

  /**
   * Date 时间类型装换
   */
  @Component
  class DateSerializer extends JsonSerializer<Date> {

    private final DateFormat DATE_FORMAT;

    public DateSerializer() {
      DATE_FORMAT = new SimpleDateFormat(pattern);
    }

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException {
      String formattedDate = DATE_FORMAT.format(date);
      gen.writeString(formattedDate);
    }
  }

  /**
   * Date 时间类型装换
   */
  @Component
  class DateDeserializer extends JsonDeserializer<Date> {

    private final DateFormat DATE_FORMAT;

    public DateDeserializer() {
      DATE_FORMAT = new SimpleDateFormat(pattern);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
      try {
        return DATE_FORMAT.parse(jsonParser.getValueAsString());
      } catch (ParseException e) {
        throw new RuntimeException("Could not parse date", e);
      }
    }
  }

  /**
   * LocalDate 时间类型装换
   */
  class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeString(value.format(DateTimeFormatter.ofPattern(pattern)));
    }
  }

  /**
   * LocalDate 时间类型装换
   */
  class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
      return LocalDateTime.parse(p.getValueAsString(), DateTimeFormatter.ofPattern(pattern));
    }
  }

}
