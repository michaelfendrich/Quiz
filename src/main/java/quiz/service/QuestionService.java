package quiz.service;

import org.springframework.stereotype.Service;
import quiz.entity.Question;
import quiz.entity.QuestionToForm;
import quiz.entity.ResultForm;
import quiz.repostitory.QuestionRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository repository;
    List<Question> listOfAllQuestionsFromRepository = new ArrayList<>();
    Instant startingTime;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question save(Question question) {
        return repository.save(question);
    }

    public List<Question> findAll() {
        listOfAllQuestionsFromRepository = repository.findAll();
        startingTime = Instant.now();
        return listOfAllQuestionsFromRepository;
    }

    public ResultForm calculateTheResult(List<QuestionToForm> forms) {
        Duration timeForTheText = Duration.between(startingTime, Instant.now());
        ResultForm resultForm = new ResultForm();
        resultForm.setTakingSeconds(timeForTheText.toSeconds());
        for (QuestionToForm item : forms) {
            item.setCorrectedAnswer(listOfAllQuestionsFromRepository
                    .stream()
                    .filter(question -> question.getId() == item.getId())
                    .findFirst()
                    .get()
                    .getCorrectAnswer());
        }
        resultForm.setQuestions(forms);
        long countOfCorrectAnswers = forms
                .stream()
                .filter(item -> item.getCorrectedAnswer().equals(item.getSelectedAnswer()))
                .count();
        resultForm.setResult(countOfCorrectAnswers * 100 / forms.size());
        return resultForm;
    }
}
