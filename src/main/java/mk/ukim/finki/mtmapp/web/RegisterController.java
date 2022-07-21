package mk.ukim.finki.mtmapp.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.mtmapp.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final AuthService authService;

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error + " Please try again.");
        }
        model.addAttribute("headTitle", "Register Page");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "auth.css");
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String email,
                           @RequestParam Integer age) {
        try {
            this.authService.register(name, surname, username, password, repeatedPassword, email, age);
            return "redirect:/login";
        } catch (RuntimeException e) {
            return "redirect:/register?error=" + e.getMessage();
        }
    }
}
