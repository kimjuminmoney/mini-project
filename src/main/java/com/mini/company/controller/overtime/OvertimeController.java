package com.mini.company.controller.overtime;

import com.mini.company.dto.overtime.response.OvertimeResponse;
import com.mini.company.service.overtime.OvertimeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.List;

@RestController
public class OvertimeController {
    private final OvertimeService overtimeService;

    public OvertimeController(OvertimeService overtimeService) {
        this.overtimeService = overtimeService;
    }

    @GetMapping("overtimes")
    public List<OvertimeResponse> getOvertimes(
            @DateTimeFormat(pattern = "yyyy-MM")
            YearMonth yearMonth
    ){
        return overtimeService.getOvertimes(yearMonth);

    }
}
