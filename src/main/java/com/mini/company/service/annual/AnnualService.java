package com.mini.company.service.annual;

import com.mini.company.domain.annual.Annual;
import com.mini.company.domain.annual.AnnualRepository;
import com.mini.company.domain.annual.AnnualTemp;
import com.mini.company.domain.team.Team;
import com.mini.company.domain.team.TeamRepository;
import com.mini.company.dto.annual.AnnualApplicationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AnnualService {
    private final AnnualRepository annualRepository;
    private final TeamRepository teamRepository;

    public AnnualService(AnnualRepository annualRepository, TeamRepository teamRepository) {
        this.annualRepository = annualRepository;
        this.teamRepository = teamRepository;
    }
    @Transactional
    public void saveUseAnnual(AnnualApplicationRequest request) {
        Annual annual = annualRepository.findTopByMember_MemberIdOrderByAnnualIdDesc(request.getMemberId());
        Team team = teamRepository.findById(annual.getTeam().getTeamId())
                .orElseThrow(IllegalAccessError::new);
        boolean isDateAllowed = checkAnnualRule(team.getAnnualRule(), request.getUsingDate());
        AnnualTemp annualTemp = new AnnualTemp(annual,request.getUsingDate());
        if (isDateAllowed) {
            // 사용 가능한 날짜인 경우 휴가 사용 로직 수행
            annualRepository.save(new Annual(annualTemp));
        } else {
            // 사용 불가능한 날짜인 경우 예외 처리 또는 처리 로직 수행t
            throw new IllegalArgumentException("신청 기한이 지났습니다. \n신청 기한을 확인해주세요");
        }
    }

    public boolean checkAnnualRule(int annualRule, LocalDate usingDate) {
        // 현재 날짜 구하기
        LocalDate currentDate = LocalDate.now();
        // 휴가 신청 가능 날짜 구하기 (usingDate - annualRule)
        LocalDate availableDate = usingDate.minusDays(annualRule);
        // 휴가 신청 가능한지 여부 반환
        return availableDate.isAfter(currentDate);
    }

    public Integer getAnnual(Long memberId) {
        return annualRepository.findTopByMember_MemberIdOrderByAnnualIdDesc(memberId).getRemainAnnual();
    }
}
