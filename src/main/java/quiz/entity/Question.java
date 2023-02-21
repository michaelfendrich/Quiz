package quiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String questionText;
    @Column(name = "answer_a")
    private String answerA;
    @Column(name = "answer_b")
    private String answerB;
    @Column(name = "answer_c")
    private String answerC;

    @JsonIgnore
    private String correctAnswer;

    public Question(String questionText, String s, String s1, String s2, String c) {
        this.questionText = questionText;
        this.answerA = s;
        this.answerB = s1;
        this.answerC = s2;
        this.correctAnswer = c;
    }
}
