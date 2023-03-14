package quiz.entity.question;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "")
    @Setter(AccessLevel.NONE)
    private int id;
    private String questionText;
    @Column(name = "answer_a")
    private String answerA;
    @Column(name = "answer_b")
    private String answerB;
    @Column(name = "answer_c")
    private String answerC;
    @Size(max = 1)
    private String correctAnswer;
    @Enumerated(value = EnumType.STRING)
    private TypeOfQuestion typeOfQuestion;

    public Question(String questionText, String s, String s1, String s2, String correctAnswer, TypeOfQuestion typeOfQuestion) {
        this.questionText = questionText;
        this.answerA = s;
        this.answerB = s1;
        this.answerC = s2;
        this.correctAnswer = correctAnswer;
        this.typeOfQuestion = typeOfQuestion;
    }
}
