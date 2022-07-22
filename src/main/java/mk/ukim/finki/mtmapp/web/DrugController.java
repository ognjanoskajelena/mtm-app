package mk.ukim.finki.mtmapp.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.mtmapp.model.enums.Use;
import mk.ukim.finki.mtmapp.model.form.DoseForm;
import mk.ukim.finki.mtmapp.service.DrugService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/drug")
@AllArgsConstructor
public class DrugController {

    private final DrugService drugService;

    @PostMapping("/{id}/edit")
    public String updateDrug(
            @PathVariable Long id,
            @RequestParam String name, @RequestParam Integer times, @RequestParam Integer pills,
            @RequestParam Use use, @RequestParam Integer stockpile) {
        this.drugService.update(id, name, new DoseForm(times, pills), use, stockpile);
        return "redirect:/med-therapy";
    }
}
