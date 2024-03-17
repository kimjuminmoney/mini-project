package com.mini.company.service.commute;

import com.mini.company.domain.commute.Commute;
import com.mini.company.domain.commute.CommuteRepository;
import com.mini.company.domain.member.Member;
import com.mini.company.domain.member.MemberRepository;
import com.mini.company.dto.commute.request.CommuteGetRequest;
import com.mini.company.dto.commute.response.CommuteListResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class CommuteService {

    private CommuteRepository commuteRepository;
    private MemberRepository memberRepository;


    public CommuteService(CommuteRepository commuteRepository, MemberRepository memberRepository) {
        this.commuteRepository = commuteRepository;
        this.memberRepository = memberRepository;
    }

    //출근 저장
    @Transactional
    public void saveStart(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(IllegalAccessError::new);
        member.commute();
        Commute commute = commuteRepository.save(new Commute(member));
        commute.updateDate();
        commute.updateStart();
    }
    //퇴근 저장
    @Transactional
    public void saveEnd(Long memberId) {

        Commute commute = commuteRepository.findByMember_MemberIdAndDate(memberId, LocalDate.now())
                .orElseThrow(IllegalAccessError::new);
        commute.updateEnd();
        commute.updateWorkingMinutes();
    }

    // 특정 지원의 특정 달 근무 리스트
    @Transactional(readOnly = true)
    public CommuteListResponse getCommutesMember(CommuteGetRequest request) {
        LocalDate startOfMonth = request.getDate().atDay(1);
        LocalDate endOfMonth = request.getDate().atEndOfMonth();

        List<Commute> commuteList = commuteRepository.findByMemberIdAndDateBetween(
                request.getMemberId(), startOfMonth, endOfMonth);

        return new CommuteListResponse(commuteList);
    }
}
