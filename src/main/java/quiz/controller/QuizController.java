package quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quiz.entity.Question;
import quiz.entity.QuestionToForm;
import quiz.entity.ResultForm;
import quiz.entity.TypeOfQuestion;
import quiz.service.QuestionService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    @ResponseBody
    public ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.ok(questionService.findAll());
    }

    @PostMapping("/questions")
    @ResponseBody
    public ResultForm check(@RequestBody List<QuestionToForm> question) {
        return questionService.calculateTheResult(question);
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Question>> getQuestionsByType(@RequestParam("type") TypeOfQuestion type) {
        return ResponseEntity.ok(questionService.findAllByType(type));
    }

    @GetMapping
    public String getTemplate(Model model) {
        model.addAttribute("type", TypeOfQuestion.CS_HISTORY.quizName());
        model.addAttribute("parameter", TypeOfQuestion.CS_HISTORY.getParam());
        return "index.html";
    }

    @PostMapping("api")
    @ResponseBody
    public ResponseEntity<Question> addQuestion(@RequestBody @Valid Question newQuestion) {
        Question savedQuestion = questionService.save(newQuestion);
        return ResponseEntity.ok(savedQuestion);
    }
}
