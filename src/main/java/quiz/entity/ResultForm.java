package quiz.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResultForm {

    private float result;
    private long takingSeconds;
    private List<QuestionToForm> questions;
}
