package quiz.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import quiz.entity.question.Question;
import quiz.entity.question.QuestionDTO;
import quiz.entity.question.TypeOfQuestion;
import quiz.repostitory.QuestionRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("ok")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QuizControllerTest {

    private final static String URL_BASE = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private QuestionRepository repository;

    @Test
    @Order(1)
    public void shouldReturnAllEntities() {
        //given
        /*
        not necessary to add any object because of db.migration which is also migrated into
        testing database
         */
        //when
        Question[] result = template
                .getForObject(URL_BASE + port + "/api", Question[].class);

        //then
        assertThat(result.length).isEqualTo(9);
        assertThat(result[0].getQuestionText()).contains("Czechoslovakia");
    }

    @Test
    @Order(2)
    public void shouldSaveNewQuestion() {
        //given
        repository.deleteAll();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        QuestionDTO questionDTO = new QuestionDTO("Text question to be test",
                "test1", "test2", "test3",
                "a", TypeOfQuestion.GENERAL);
        HttpEntity<QuestionDTO> entity = new HttpEntity<>(questionDTO, headers);

        //when
        ResponseEntity<Question> result = template.postForEntity(URL_BASE + port + "/api", entity, Question.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getHeaders().getLocation().toString()).contains("/api/1");
    }

    @Test
    @Order(3)
    @DisplayName("It shouldn't pass because the value of answerA is null")
    public void shouldNotSaveBecauseAnswerAIsNull() {
        //given
        repository.deleteAll();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        QuestionDTO questionDTO = new QuestionDTO("Text question to be test",
                null, "test2", "test3",
                "a", TypeOfQuestion.GENERAL);
        HttpEntity<QuestionDTO> entity = new HttpEntity<>(questionDTO, headers);

        //when
        ResponseEntity<String> result = template.postForEntity(URL_BASE + port + "/api", entity, String.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
        assertThat(result.toString()).contains("Cannot be empty");
    }
}