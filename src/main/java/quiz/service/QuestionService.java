package quiz.service;

import org.springframework.stereotype.Service;
import quiz.entity.Question;
import quiz.entity.QuestionDTO;
import quiz.entity.TypeOfQuestion;
import quiz.repostitory.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question save(QuestionDTO question) {
        Question toSave = question.toEntity();
        return repository.save(toSave);
    }

    public List<Question> findAll() {
        List<Question> listOfQuestion = repository.findAll();
        return listOfQuestion;
    }

    public List<Question> findAllByType(TypeOfQuestion type) {
        return repository.findAllByTypeOfQuestion(type);
    }
}
