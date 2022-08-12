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

    // clearing therapy status, every day at noon
    // 0/30 * * * * ? (every 30 seconds, for testing purposes)
    @Scheduled(cron = "0 0 12 * * ?")
    public void refreshMedicalTherapyStatus() {
        // System.out.println("EXE");
        List<MedicalTherapy> medicalTherapies = this.medicalTherapyService.findAll();
        for (MedicalTherapy mt: medicalTherapies) {
            mt.clearTherapyStatus();
            this.medicalTherapyService.update(mt);
        }
    }
}
