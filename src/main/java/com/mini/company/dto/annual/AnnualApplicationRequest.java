package com.mini.company.dto.annual;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnnualApplicationRequest {
    private Long memberId;
    private LocalDate usingDate;
}
