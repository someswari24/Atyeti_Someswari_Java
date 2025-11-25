package bankSchedular.controller;

import bankSchedular.service.ReconciliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reconcile")
@RequiredArgsConstructor
public class ReconciliationController {
    private final ReconciliationService recon;

    @PostMapping("/run")
    public Map<String, String> runNow() {
        return recon.reconcileNow();
    }
}
