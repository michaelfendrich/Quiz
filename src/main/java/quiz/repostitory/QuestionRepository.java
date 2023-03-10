package quiz.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quiz.entity.QuestionMini;
import quiz.entity.Question;
import quiz.entity.TypeOfQuestion;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    public List<Question> findAllByTypeOfQuestion(TypeOfQuestion typeOfQuestion);

    public Optional<Question> findById(int id);

    public void deleteById(int id);

    @Query(value = "select id, question_text from Question", nativeQuery = true)
    public List<QuestionMini> findAllMini();
}
