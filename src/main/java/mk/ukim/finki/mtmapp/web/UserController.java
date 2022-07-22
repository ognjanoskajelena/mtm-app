package mk.ukim.finki.mtmapp.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/user"})
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String getUserPage(Model model, HttpServletRequest request) {
        Optional<User> optionalUser = this.userService.findByUsername(request.getRemoteUser());
        if (optionalUser.isPresent()) {
            model.addAttribute("headTitle", "Profile");
            model.addAttribute("style1", "header.css");
            model.addAttribute("style2", "profile.css");
            model.addAttribute("bodyContent", "profile");

            model.addAttribute("user", optionalUser.get());
            return "master-template";
        }
        return "redirect:/login";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(
            @PathVariable Long id,
            @RequestParam String name, @RequestParam String surname,
            @RequestParam String username, @RequestParam(required = false) String password,
            @RequestParam String email, @RequestParam Integer age) {

        this.userService.update(id, name, surname, username, password, email, age);
        return "redirect:/user";
    }
}
