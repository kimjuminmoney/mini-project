package com.mini.company.dto.commute.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CommuteGetRequest {

    private Long memberId;
    //private LocalDate date;
    private YearMonth date;

}
