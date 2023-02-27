package quiz.service;

import org.springframework.stereotype.Service;
import quiz.entity.Question;
import quiz.entity.QuestionToForm;
import quiz.entity.ResultForm;
import quiz.entity.TypeOfQuestion;
import quiz.repostitory.QuestionRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question save(Question question) {
        return repository.save(question);
    }

    public List<Question> findAll() {
        List<Question> listOfQuestion = repository.findAll();
        return listOfQuestion;
    }

    public List<Question> findAllByType(TypeOfQuestion type) {
        return repository.findAllByTypeOfQuestion(type);
    }

    public ResultForm calculateTheResult(List<QuestionToForm> forms) {
        ResultForm resultForm = new ResultForm();
        for (QuestionToForm item : forms) {
            item.setCorrectedAnswer(repository.findAll()
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
