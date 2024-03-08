package com.mini.company.service.commute;

import com.mini.company.domain.commute.Commute;
import com.mini.company.domain.commute.CommuteRepository;
import com.mini.company.domain.member.Member;
import com.mini.company.domain.member.MemberRepository;
import com.mini.company.dto.commute.request.CommuteGetRequest;
import com.mini.company.dto.commute.response.CommuteDetail;
import com.mini.company.dto.commute.response.CommuteListResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class CommuteService {

    private CommuteRepository commuteRepository;
    private MemberRepository memberRepository;


    public CommuteService(CommuteRepository commuteRepository, MemberRepository memberRepository) {
        this.commuteRepository = commuteRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void saveStart(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(IllegalAccessError::new);
        Commute commute = commuteRepository.save(new Commute(memberId));
        commute.updateDate();
        commute.updateStart();
    }
    @Transactional
    public void saveEnd(Long memberId) {
        Commute commute = commuteRepository.findByMemberIdAndDate(memberId, LocalDate.now())
                .orElseThrow(IllegalAccessError::new);
        commute.updateEnd();
        commute.updateWorkingMinutes();
    }

    public CommuteListResponse getCommutesMember(CommuteGetRequest request) {
        //List<CommuteDetail> commuteList = commuteRepository.findByIdAndDate(request.getMemberId(), request.getDate());
        YearMonth yearMonth = YearMonth.from(request.getDate());
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        List<CommuteDetail> commuteList = commuteRepository.findByMemberIdAndDateBetween(
                request.getMemberId(), startOfMonth, endOfMonth);
        int sum = 0;
        for (CommuteDetail commuteDetail : commuteList){
            sum +=commuteDetail.getWorkingMinutes();
        }
        return new CommuteListResponse(commuteList, sum);
    }
}
