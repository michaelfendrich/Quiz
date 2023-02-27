package quiz.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.Question;
import quiz.entity.TypeOfQuestion;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    public List<Question> findAllByTypeOfQuestion(TypeOfQuestion typeOfQuestion);
}
