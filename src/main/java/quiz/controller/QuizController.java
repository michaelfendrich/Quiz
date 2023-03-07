package quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        return "index_layout.html";
    }

    @GetMapping("/new_question")
    public String newQuestionForm(Model model) {
        model.addAttribute("dto", new QuestionDTO());
        return "new_question_layout.html";
    }

    @GetMapping("/edit")
    public String gettingAllItems(Model model) {
        List<Question> questionList = questionService.findAll();
        model.addAttribute("questions", questionList);
        if (questionList.isEmpty()) {
            model.addAttribute("message", "Repository is empty");
        }
        return "list.html";
    }

    @PostMapping("/new_question")
    public String saveNewQuestion(Model model, @Valid @ModelAttribute("dto") QuestionDTO questionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "new_question_layout.html";
        }
        model.addAttribute("dto", new QuestionDTO());
        model.addAttribute("message", "✅ Added successfully");
        questionService.save(questionDTO);
        return "new_question_layout.html";
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
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Question> addQuestion(@RequestBody @Valid QuestionDTO newQuestion) {
        Question savedQuestion = questionService.save(newQuestion);
        return ResponseEntity.created(URI.create("localhost:8080/api/" + savedQuestion.getId())).build();
    }

    @GetMapping("/edit/{id}")
    public String updateQuestion(@PathVariable("id") int id, Model model) {
        Question question = questionService.findById(id).orElseThrow(NullPointerException::new);
        model.addAttribute("dto", question);
        return "edit_question_layout.html";
    }

    @PostMapping("/edit/{id}")
    public String editing(@PathVariable("id") int id, Model model, @Valid @ModelAttribute("dto") QuestionDTO questionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "edit_question_layout.html";
        }
        model.addAttribute("message", "✅ Added successfully");
        questionService.editQuestion(id, questionDTO);
        return "edit_question_layout.html";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        questionService.delete(id);
        model.addAttribute("questions", questionService.findAll());
        model.addAttribute("message", "✅ Deleted successfully");
        return "list.html";
    }

    @ModelAttribute("types")
    public List<TypeOfQuestion> getList() {
        return TypeOfQuestion.getAllTypes();
    }
}
