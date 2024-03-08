package com.mini.company.dto.commute.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommuteGetRequest {

    private Long memberId;
    private LocalDate date;

}
