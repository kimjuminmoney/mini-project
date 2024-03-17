package com.mini.company.dto.commute.response;

import com.mini.company.domain.commute.Commute;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CommuteDetail {

    private LocalDate date;
    private Long workingMinutes;

    private boolean usingDayoff = false;

    public CommuteDetail(Commute commute) {
        this.date = commute.getDate();
        this.workingMinutes = commute.getWorkingMinutes();
    }

    public CommuteDetail(LocalDate localDate) {
        this.date = localDate;
        this.workingMinutes = 0L;
        this.usingDayoff = true;
    }

}
