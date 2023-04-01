package quiz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import quiz.entity.question.QuestionMini;
import quiz.entity.question.Question;
import quiz.entity.question.TypeOfQuestion;
import quiz.repostitory.QuestionRepository;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    @Profile("ok")
    DataSource e2eTestDataSource () {
        var result = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        result.setDriverClassName("org.h2.Driver");
        return result;
    }
}
