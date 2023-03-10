package quiz.service;

import org.springframework.stereotype.Service;
import quiz.entity.QuestionMini;
import quiz.entity.Question;
import quiz.entity.QuestionDTO;
import quiz.entity.TypeOfQuestion;
import quiz.exception.QuestionNotFoundException;
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

    public Question editQuestion(int id, QuestionDTO question) {
        Question questionToUpdate = repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        questionToUpdate.setQuestionText(question.getQuestionText());
        questionToUpdate.setAnswerA(question.getAnswerA());
        questionToUpdate.setAnswerB(question.getAnswerB());
        questionToUpdate.setAnswerC(question.getAnswerC());
        questionToUpdate.setCorrectAnswer(question.getCorrectAnswer());
        questionToUpdate.setTypeOfQuestion(question.getTypeOfQuestion());
        return repository.save(questionToUpdate);
    }

    public void delete(int id) {
        Question question = repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        repository.delete(question);
    }

    public Question findById(int id) {
        return repository.findById(id).orElseThrow(QuestionNotFoundException::new);
    }

    public List<Question> findAllByType(TypeOfQuestion type) {
        return repository.findAllByTypeOfQuestion(type);
    }

    public List<QuestionMini> findAllMiniList() {
        return repository.findAllMini();
    }
}
