package mk.ukim.finki.mtmapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("headTitle", "Home");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "home.css");
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    @GetMapping("/about-us")
    public String getAboutUsPage(Model model) {
        model.addAttribute("headTitle", "About us");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "about-us.css");
        model.addAttribute("bodyContent", "about-us");
        return "master-template";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage() {
        return "access-denied";
    }

    @GetMapping("/not-found")
    public String getNotFoundPage() {
        return "not-found";
    }
}
