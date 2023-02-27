package quiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "")
    private int id;

    @NotNull(message = "Cannot be empty")
    @NotBlank(message = "Cannot be empty")
    private String questionText;

    @NotNull(message = "Cannot be empty")
    @NotBlank(message = "Cannot be empty")
    @Column(name = "answer_a")
    private String answerA;

    @NotNull(message = "Cannot be empty")
    @NotBlank(message = "Cannot be empty")
    @Column(name = "answer_b")
    private String answerB;

    @NotNull(message = "Cannot be empty")
    @NotBlank(message = "Cannot be empty")
    @Column(name = "answer_c")
    private String answerC;

    @NotNull(message = "Cannot be empty")
    @NotBlank(message = "Cannot be empty")
    @Size(max = 1)
    private String correctAnswer;

    @NotNull(message = "Cannot be empty")
    @Column(name = "type_of_question")
    @Enumerated(value = EnumType.STRING)
    private TypeOfQuestion typeOfQuestion;

    public Question(String questionText, String s, String s1, String s2, String c) {
        this.questionText = questionText;
        this.answerA = s;
        this.answerB = s1;
        this.answerC = s2;
        this.correctAnswer = c;
    }
}
