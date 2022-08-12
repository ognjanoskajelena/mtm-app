package mk.ukim.finki.mtmapp.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/med-therapy")
@AllArgsConstructor
public class MedicalTherapyController {

    private final UserService userService;

    @GetMapping
    public String getMedicalTherapy(HttpServletRequest request, Model model) {
        Optional<User> optionalUser = this.userService.findByUsername(request.getRemoteUser());
        if (optionalUser.isPresent()) {
            model.addAttribute("headTitle", "Medical therapy");
            model.addAttribute("style1", "header.css");
            // model.addAttribute("style2", "med-therapy.css");
            model.addAttribute("bodyContent", "med-therapy");

            User user = optionalUser.get();
            model.addAttribute("user", user);
            model.addAttribute("medicalTherapy", user.getMedicalTherapy());
            return "master-template";
        }
        return "redirect:/login";
    }
}
