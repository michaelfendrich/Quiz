package quiz.service;

import org.springframework.stereotype.Service;
import quiz.entity.question.Question;
import quiz.entity.question.QuestionDTO;
import quiz.entity.question.QuestionMini;
import quiz.entity.question.TypeOfQuestion;
import quiz.exception.QuestionNotFoundException;
import quiz.repostitory.QuestionRepository;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository repository;
    private static final int LIMIT = 15;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question save(QuestionDTO question) {
        Question toSave = question.toEntity();
        return repository.save(toSave);
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

    public List<Question> findByTypeOrderByRandom(TypeOfQuestion type) {
        if (type == null) {
            return repository.findAll();
        }
        return repository.findRandomByTypeOfQuestion(type, LIMIT);
    }

    public List<QuestionMini> findAllMiniList() {
        return repository.findAllMini();
    }
}
