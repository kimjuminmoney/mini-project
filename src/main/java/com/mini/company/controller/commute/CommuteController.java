package com.mini.company.controller.commute;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.company.dto.commute.request.CommuteGetRequest;
import com.mini.company.dto.commute.response.CommuteListResponse;
import com.mini.company.service.commute.CommuteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

@RestController
public class CommuteController {

    private CommuteService commuteService;

    public CommuteController(CommuteService commuteService) {
        this.commuteService = commuteService;
    }

    @PostMapping("/commute/start")
    public void saveStart(@RequestBody Map<String,Long> data) {
        Long memberId = data.get("memberId");
        commuteService.saveStart(memberId);

    }

    @PostMapping("/commute/end")
    public void saveEnd(@RequestBody Map<String,Long> data){
        Long memberId = data.get("memberId");
        commuteService.saveEnd(memberId);
    }

    @GetMapping("/commutes/member")
    public CommuteListResponse getCommutesMember(
            @RequestParam Long memberId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth date) {
        return commuteService.getCommutesMember(new CommuteGetRequest(memberId,date.atDay(1)));
    }
}
