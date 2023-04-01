package quiz.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import quiz.entity.question.QuestionMini;
import quiz.entity.question.Question;
import quiz.entity.question.TypeOfQuestion;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(nativeQuery = true, value = "Select * from question where type_of_question = :#{#type.name()} order by rand() limit :limit")
    List<Question> findRandomByTypeOfQuestion(@Param("type") TypeOfQuestion type, @Param("limit") int limit);

    Optional<Question> findById(int id);

    @Query(value = "select id, question_text from Question", nativeQuery = true)
    List<QuestionMini> findAllMini();
}
