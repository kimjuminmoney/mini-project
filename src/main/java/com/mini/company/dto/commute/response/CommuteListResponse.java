package com.mini.company.dto.commute.response;

import com.mini.company.domain.annual.Annual;
import com.mini.company.domain.commute.Commute;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommuteListResponse {

    private List<CommuteDetail> detail;
    private int sum;

    public CommuteListResponse(List<Commute> commuteList) {
        Long workingMinutes = 0L;
        detail = new ArrayList<>();
        CommuteDetail commuteDetail = null;
        for (Commute commute : commuteList) {
            workingMinutes = commute.getWorkingMinutes();
            sum += workingMinutes;

            commuteDetail = new CommuteDetail(commute);
            //checkDayoff(workingMinutes, commuteDetail);
            checkDayoff(commuteList,commuteDetail);
            detail.add(commuteDetail);
        }
    }

    private void checkDayoff(Long workingMinutes, CommuteDetail commuteDetail) {
        if (workingMinutes == 0) {
            commuteDetail.updateUsingDayoff();
        }
    }
    private void checkDayoff(List<Commute> commuteList, CommuteDetail commuteDetail) {
        List<Annual> annualList = commuteList.get(1).getMember().getAnnualList();
        for (int i = 0; i < annualList.size(); i++) {
            System.out.println("------------------------------------------");
            annualList.get(i);
        }


    }
}
