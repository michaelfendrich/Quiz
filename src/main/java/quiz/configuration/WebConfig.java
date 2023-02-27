package quiz.configuration;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import quiz.entity.TypeOfQuestion;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverter());
    }

    public class StringToEnumConverter implements Converter<String, TypeOfQuestion> {
        @Override
        public TypeOfQuestion convert(String source) {
            return TypeOfQuestion.valueOf(source.toUpperCase());
        }
    }
}
