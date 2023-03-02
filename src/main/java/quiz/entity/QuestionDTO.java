package quiz.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuestionDTO {

    @NotNull(message = "Cannot be empty")
    @NotBlank(message = "Cannot be empty")
    @Size(min = 3)
    private String questionText;

    @NotNull(message = "Cannot be empty")
    @NotBlank(message = "Cannot be empty")
    private String answerA;

    @NotNull(message = "Cannot be empty")
    @NotBlank(message = "Cannot be empty")
    private String answerB;

    @NotNull(message = "Cannot be empty")
    @NotBlank(message = "Cannot be empty")
    private String answerC;

    @NotNull(message = "Cannot be empty")
    @NotBlank(message = "Cannot be empty")
    @Size(max = 1)
    private String correctAnswer;

    @NotNull(message = "Cannot be empty")
    private TypeOfQuestion typeOfQuestion;

    public QuestionDTO(String questionText, String answerA, String answerB, String answerC, String correctAnswer, TypeOfQuestion typeOfQuestion) {
        this.questionText = questionText;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.correctAnswer = correctAnswer;
        this.typeOfQuestion = typeOfQuestion;
    }

    public Question toEntity() {
        Question entity = new Question();
        entity.setQuestionText(this.questionText);
        entity.setAnswerA(this.answerA);
        entity.setAnswerB(this.answerB);
        entity.setAnswerC(this.answerC);
        entity.setCorrectAnswer(this.correctAnswer);
        entity.setTypeOfQuestion(this.typeOfQuestion);
        return entity;
    }
}
