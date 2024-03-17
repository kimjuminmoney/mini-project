package com.mini.company.service.overtime;

import com.mini.company.domain.annual.AnnualRepository;
import com.mini.company.domain.commute.Commute;
import com.mini.company.domain.commute.CommuteRepository;
import com.mini.company.domain.member.Member;
import com.mini.company.domain.member.MemberRepository;
import com.mini.company.dto.overtime.response.OvertimeResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class OvertimeService {

    private final MemberRepository memberRepository;
    private final CommuteRepository commuteRepository;
    private final AnnualRepository annualRepository;

    public OvertimeService(MemberRepository memberRepository, CommuteRepository commuteRepository, AnnualRepository annualRepository) {
        this.memberRepository = memberRepository;
        this.commuteRepository = commuteRepository;
        this.annualRepository = annualRepository;
    }

    public List<OvertimeResponse> getOvertimes(YearMonth yearMonth){
        List<Member> memberList = memberRepository.findAll();
        List<OvertimeResponse> responseList = new ArrayList<>();
        // 파라미터 값의 월 시작일
        LocalDate startDate = yearMonth.atDay(1);
        // 파라미터값이 이번달인지 확인
        LocalDate endDate = checkMonth(yearMonth);

        for (int i = 0; i < memberList.size(); i++) {
            Member member = memberList.get(i);
            List<Commute> commuteList = commuteRepository.findByMemberIdAndDateBetween(member.getMemberId(), startDate, endDate);
            OvertimeResponse overtimeResponse = new OvertimeResponse(member, commuteList);
            responseList.add(overtimeResponse);
        }
        return responseList;
    }

    private LocalDate checkMonth(YearMonth yearMonth){
        // 파라미터값이 이번달이면 어제 날짜 반환
        if(yearMonth.getMonth().equals(YearMonth.now().getMonth())){
            return LocalDate.now().minusDays(1);
        }
        //아니면 말일 반환
        return yearMonth.atEndOfMonth();
    }
}
