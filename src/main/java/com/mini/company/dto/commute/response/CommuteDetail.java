package com.mini.company.dto.commute.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class CommuteDetail {

    private LocalDate date;
    private Long workingMinutes;
}
