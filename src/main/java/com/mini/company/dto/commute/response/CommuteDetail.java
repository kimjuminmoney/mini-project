package com.mini.company.dto.commute.response;

import com.mini.company.domain.commute.Commute;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

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

    public void updateUsingDayoff(){
        this.usingDayoff = true;
    }
}
