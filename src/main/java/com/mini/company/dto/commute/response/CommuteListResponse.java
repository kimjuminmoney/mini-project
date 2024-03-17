package com.mini.company.dto.commute.response;

import com.mini.company.domain.annual.Annual;
import com.mini.company.domain.commute.Commute;
import com.mini.company.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Slf4j
public class CommuteListResponse {

    private List<CommuteDetail> detail;
    private int sum;

//    public CommuteListResponse(List<Commute> commuteList) {
//        Long workingMinutes = 0L;
//        detail = new ArrayList<>();
//        CommuteDetail commuteDetail = null;
//        for (Commute commute : commuteList) {
//            workingMinutes = commute.getWorkingMinutes();
//            sum += workingMinutes;
//
//            commuteDetail = new CommuteDetail(commute);
//            //checkDayoff(workingMinutes, commuteDetail);
//            //checkDayoff(commuteList,commuteDetail);
//            detail.add(commuteDetail);
//        }
//    }

    public CommuteListResponse(Member member, YearMonth yearMonth) {
        List<Annual> annualList = returnAnnualList(member, yearMonth);
        List<Commute> commuteList = returnCommuteList(member, yearMonth);
        //log.info("commuteList={},annualList={}",commuteList.size(),annualList.size());
        detail = new ArrayList<>();
        //CommuteDetail commuteDetail = null;
        LocalDate localDate = yearMonth.atDay(1);
        for (Commute commute : commuteList) {
            if(commute.getDate().isEqual(localDate)){
                sum += commute.getWorkingMinutes();
                detail.add(new CommuteDetail(commute));

            } else {
                for (Annual annual : annualList) {
                    if (annual.getUsingDate().isEqual(localDate)) {
                        detail.add(new CommuteDetail(localDate));
                    }
                }
            }
            //detail.add(commuteDetail);
            localDate = localDate.plusDays(1);
        }
        //log.info("detail={}",detail.size());
    }


    private List<Annual> returnAnnualList(Member member, YearMonth yearMonth) {
        return member.getAnnualList()
                .stream()
                .filter(x -> x.getUsingDate() != null &&
                        !x.getUsingDate().isBefore(yearMonth.atDay(1)) &&
                        !x.getUsingDate().isAfter(yearMonth.atEndOfMonth()))
                .collect(Collectors.toList());
    }

    private List<Commute> returnCommuteList(Member member, YearMonth yearMonth) {
        return member.getCommuteList()
                .stream()
                .filter(x -> x.getDate() != null &&
                        !x.getDate().isBefore(yearMonth.atDay(1)) &&
                        !x.getDate().isAfter(yearMonth.atEndOfMonth()))
                .collect(Collectors.toList());
    }

}
