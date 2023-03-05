package quiz.service;

import org.springframework.stereotype.Service;
import quiz.entity.Question;
import quiz.entity.QuestionDTO;
import quiz.entity.TypeOfQuestion;
import quiz.repostitory.QuestionRepository;

import java.util.List;
import java.util.Optional;

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

    public Question editQuestion(int id, QuestionDTO question) {
        Question questionFromRepository = repository.findById(id).orElseThrow(NullPointerException::new);
        questionFromRepository.setQuestionText(question.getQuestionText());
        questionFromRepository.setAnswerA(question.getAnswerA());
        questionFromRepository.setAnswerB(question.getAnswerB());
        questionFromRepository.setAnswerC(question.getAnswerC());
        questionFromRepository.setCorrectAnswer(question.getCorrectAnswer());
        questionFromRepository.setTypeOfQuestion(question.getTypeOfQuestion());
        return repository.save(questionFromRepository);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public Optional<Question> findById(int id) {
        return repository.findById(id);
    }

    public List<Question> findAllByType(TypeOfQuestion type) {
        return repository.findAllByTypeOfQuestion(type);
    }
}
