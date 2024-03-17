package com.mini.company.controller.annual;

import com.mini.company.dto.annual.AnnualApplicationRequest;
import com.mini.company.service.annual.AnnualService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnnualController {
    private final AnnualService annualService;

    public AnnualController(AnnualService annualService) {
        this.annualService = annualService;
    }

    @PostMapping("/annual/use")
    public void saveUseAnnual(@RequestBody AnnualApplicationRequest request) {
        annualService.saveUseAnnual(request);
    }

    @GetMapping("/annual")
    public Integer getAnnual(@RequestParam Long memberId){
        return annualService.getAnnual(memberId);
    }
}
