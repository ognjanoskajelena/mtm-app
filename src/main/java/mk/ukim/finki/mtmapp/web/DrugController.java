package mk.ukim.finki.mtmapp.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.mtmapp.model.MedicalTherapy;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.model.enums.Use;
import mk.ukim.finki.mtmapp.model.form.DoseForm;
import mk.ukim.finki.mtmapp.service.DrugService;
import mk.ukim.finki.mtmapp.service.MedicalTherapyService;
import mk.ukim.finki.mtmapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/drug")
@AllArgsConstructor
public class DrugController {

    private final DrugService drugService;
    private final MedicalTherapyService medicalTherapyService;
    private final UserService userService;

    @PostMapping("/{id}/edit")
    public String updateDrug(
            @PathVariable Long id,
            @RequestParam String name, @RequestParam Integer times, @RequestParam Integer pills,
            @RequestParam Use use, @RequestParam Integer stockpile) {
        this.drugService.update(id, name, new DoseForm(times, pills), use, stockpile);
        return "redirect:/med-therapy";
    }


    @GetMapping("/{id}/get")
    public String getDrug(@PathVariable Long id, HttpServletRequest request) {
        Optional<User> optionalUser = this.userService.findByUsername(request.getRemoteUser());
        if (optionalUser.isPresent()) {
            this.drugService.getDrug(id);
            this.medicalTherapyService.addDrugGottenToTherapy(id, optionalUser.get().getMedicalTherapy());
            return "redirect:/med-therapy";
        }
        return "redirect:/login";
    }
}
