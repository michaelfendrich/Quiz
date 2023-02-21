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
import quiz.entity.Question;
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

    @Bean
    @Primary
    @Profile("int")
    public QuestionRepository createQuestionRepository() {
        return new QuestionRepository() {
            Map<Integer, Question> questions = new HashMap<>();
            @Override
            public List<Question> findAll() {
                return questions.values().stream().toList();
            }

            @Override
            public List<Question> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Question> findAllById(Iterable<Integer> integers) {
                return null;
            }

            @Override
            public <S extends Question> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Question> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Question> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Question> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Integer> integers) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Question getOne(Integer integer) {
                return null;
            }

            @Override
            public Question getById(Integer integer) {
                return null;
            }

            @Override
            public Question getReferenceById(Integer integer) {
                return null;
            }

            @Override
            public <S extends Question> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Question> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Question> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public Question save(Question entity) {
                int id = questions.size() + 1;
                try {
                    Field field = Question.class.getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity, id);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                questions.put(id, entity);
                return questions.get(id);
            }

            @Override
            public Optional<Question> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Question entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> integers) {

            }

            @Override
            public void deleteAll(Iterable<? extends Question> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Question> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Question> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Question> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Question> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Question, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }
}
