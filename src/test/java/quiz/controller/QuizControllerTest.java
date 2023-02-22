package quiz.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import quiz.entity.Question;
import quiz.repostitory.QuestionRepository;

import java.lang.reflect.Field;
import java.sql.SQLOutput;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("ok")
class QuizControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private QuestionRepository repository;

    @Test
    public void shouldReturnANewEntity() {
        //given
        /*
        not necessary to add any object because of db.migration which is also migrated into
        testing database
         */
        //when
        Question[] result = template
                .getForObject("http://localhost:" + port + "/questions", Question[].class);

        //then
        System.out.println(result.length);
        assertThat(result[0].getQuestionText()).contains("Czechoslovakia");
    }

}