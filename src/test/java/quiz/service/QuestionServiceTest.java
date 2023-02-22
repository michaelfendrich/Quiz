package quiz.service;

import org.junit.jupiter.api.Test;
import quiz.entity.Question;
import quiz.repostitory.QuestionRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class QuestionServiceTest {

    @Test
    void findAllQuestion() {
        //given
        Question question = new Question("Warsaw", "1948", "1956", "1968", "c");
        QuestionRepository repository = mock(QuestionRepository.class);
        given(repository.save(question)).willReturn(question);
        QuestionService questionService = new QuestionService(repository);

        //when
        Question result = questionService.save(question);

        //then
        assertThat(result).isEqualTo(question);
    }

}