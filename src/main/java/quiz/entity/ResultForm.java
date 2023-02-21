package quiz.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResultForm {

    private float result;
    private List<QuestionToForm> questions;
}
