package mk.ukim.finki.mtmapp.job;

import lombok.AllArgsConstructor;
import mk.ukim.finki.mtmapp.model.MedicalTherapy;
import mk.ukim.finki.mtmapp.service.MedicalTherapyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ScheduledTasks {

    private final MedicalTherapyService medicalTherapyService;

//    @Scheduled(fixedDelay = 30000)
//    public void refreshMedicalTherapyStatus() {
//        List<MedicalTherapy> medicalTherapies = this.medicalTherapyService.findAll();
//        for (MedicalTherapy mt: medicalTherapies) {
//            mt.refreshTherapyStatus();
//            this.medicalTherapyService.update(mt);
//        }
//    }
}
