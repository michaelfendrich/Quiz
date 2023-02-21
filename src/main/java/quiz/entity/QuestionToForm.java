package quiz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class QuestionToForm {

    private int id;
    private String selectedAnswer;
    private String correctedAnswer;
}
