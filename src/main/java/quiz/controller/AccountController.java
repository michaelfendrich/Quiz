package quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import quiz.entity.user.UserDTO;
import quiz.exception.DuplicateEmailException;
import quiz.exception.PasswordDoNotEqualException;
import quiz.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String renderLogin() {
        return "/account/login.html";
    }

    @GetMapping("/register")
    public String renderRegisterForm(@ModelAttribute("userDTO") UserDTO userDTO) {
        return "/account/registration.html";
    }
    @PostMapping("/register")
    public String processRegistration(
            @ModelAttribute("userDTO") @Valid UserDTO userDTO,
            BindingResult result,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "/account/registration.html";
        }
        try {
            userService.create(userDTO, false);
        } catch (PasswordDoNotEqualException e) {
            result.rejectValue("password", "error", "⛔ Passwords are not equal.");
            result.rejectValue("confirmPassword", "error", "⛔ Passwords are not equal.");
            return "/account/registration.html";
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error", "⛔ Email has already been using.");
            return "/account/registration.html";
        }
        attributes.addFlashAttribute("message", "✅ User registered");
        return "redirect:/account/login";
    }
}
