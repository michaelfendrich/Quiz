package quiz.service;

import org.springframework.stereotype.Service;
import quiz.entity.Question;
import quiz.entity.QuestionToForm;
import quiz.entity.ResultForm;
import quiz.repostitory.QuestionRepository;

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
        return repository.findAll();
    }

    public ResultForm calculateTheResult(List<QuestionToForm> forms) {
        List<Question> questions = repository.findAll();
        ResultForm resultForm = new ResultForm();
        for (QuestionToForm item : forms) {
            item.setCorrectedAnswer(questions
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
