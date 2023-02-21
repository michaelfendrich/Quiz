package quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quiz.entity.Question;
import quiz.entity.QuestionToForm;
import quiz.entity.ResultForm;
import quiz.service.QuestionService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.ok(questionService.findAll());
    }

    @PostMapping("/questions")
    @ResponseBody
    public ResultForm calculateTheResult(@RequestBody List<QuestionToForm> form) {
        return questionService.calculateTheResult(form);
    }
}
