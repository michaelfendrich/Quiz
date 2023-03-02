package quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quiz.entity.Question;
import quiz.entity.QuestionDTO;
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

    @GetMapping
    public String getAllTypes(Model model) {
        model.addAttribute("types", TypeOfQuestion.getAllTypes());
        return "index.html";
    }

    //getting all questions in JSON
    @GetMapping("/questions")
    @ResponseBody
    public ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.ok(questionService.findAll());
    }

    //getting questions of a type in JSON
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Question>> getQuestionsByType(@RequestParam(value = "type", required = false) TypeOfQuestion type) {
        if (type != null) {
            return ResponseEntity.ok(questionService.findAllByType(type));
        }
        return ResponseEntity.ok(questionService.findAll());
    }

    //posting body in JSON
    @PostMapping("api")
    @ResponseBody
    public ResponseEntity<Question> addQuestion(@RequestBody @Valid QuestionDTO newQuestion) {
        Question savedQuestion = questionService.save(newQuestion);
        return ResponseEntity.created(URI.create("localhost:8080/api/" + savedQuestion.getId())).build();
    }
}
