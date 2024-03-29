package quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import quiz.entity.question.QuestionMini;
import quiz.entity.question.Question;
import quiz.entity.question.QuestionDTO;
import quiz.entity.question.TypeOfQuestion;
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
    public String getAllTypes() {
        return "index.html";
    }

    @GetMapping("/new_question")
    public String newQuestionForm(Model model) {
        model.addAttribute("dto", new QuestionDTO());
        return "new_question.html";
    }

    @GetMapping("/edit")
    public String gettingAllItems(Model model) {
        List<QuestionMini> questionList = questionService.findAllMiniList();
        model.addAttribute("questions", questionList);
        if (questionList.isEmpty()) {
            model.addAttribute("message", "Repository is empty");
        }
        return "list.html";
    }

    @PostMapping("/new_question")
    public String saveNewQuestion(Model model, @Valid @ModelAttribute("dto") QuestionDTO questionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "new_question.html";
        }
        model.addAttribute("dto", new QuestionDTO());
        model.addAttribute("message", "✅ Added successfully");
        questionService.save(questionDTO);
        return "new_question.html";
    }

    //getting questions of a type in JSON
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Question>> getQuestionsByType(@RequestParam(value = "type", required = false) TypeOfQuestion type) {
        return ResponseEntity.ok(questionService.findByTypeOrderByRandom(type));
    }

    //posting body in JSON
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Question> addQuestion(@RequestBody @Valid QuestionDTO newQuestion) {
        Question savedQuestion = questionService.save(newQuestion);
        return ResponseEntity.created(URI.create("/api/" + savedQuestion.getId())).body(savedQuestion);
    }

    @GetMapping("/edit/{id}")
    public String updateQuestion(@PathVariable("id") int id, Model model) {
        Question question = questionService.findById(id);
        model.addAttribute("dto", question);
        return "edit_question.html";
    }

    //Put method
    @PostMapping("/edit/{id}")
    public String editing(@PathVariable("id") int id, Model model, @Valid @ModelAttribute("dto") QuestionDTO questionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "edit_question.html";
        }
        model.addAttribute("message", "✅ Updated successfully");
        questionService.editQuestion(id, questionDTO);
        return "edit_question.html";
    }

    //Delete method
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        questionService.delete(id);
        redirectAttributes.addFlashAttribute("message", "✅ Deleted successfully");
        return "redirect:/edit";
    }

    @ModelAttribute("types")
    public List<TypeOfQuestion> getList() {
        return TypeOfQuestion.getAllTypes();
    }
}
