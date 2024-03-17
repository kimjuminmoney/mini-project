package com.mini.company.service.member;

import com.mini.company.domain.annual.Annual;
import com.mini.company.domain.annual.AnnualPlan;
import com.mini.company.domain.annual.AnnualRepository;
import com.mini.company.domain.member.Member;
import com.mini.company.domain.member.MemberRepository;
import com.mini.company.domain.team.Team;
import com.mini.company.domain.team.TeamRepository;
import com.mini.company.dto.member.request.MemberCreateRequest;
import com.mini.company.dto.member.response.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final AnnualRepository annualRepository;

    public MemberService(MemberRepository memberRepository, TeamRepository teamRepository, AnnualRepository annualRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
        this.annualRepository = annualRepository;
    }

    @Transactional
    public void saveMember(MemberCreateRequest request) {
        //teamName으로 들어온 파라미터를 조회
        Optional<Team> team = teamRepository.findByName(request.getTeamName());
        //조회후 있으면 ID반환 없으면 예외처리
        Long teamId = team.map(Team::getTeamId).orElseThrow(() ->new IllegalArgumentException("팀 이름이 없습니다."));

        //member생성 및 teamId입력
        Member member = memberRepository.save(new Member(request,team.get()));

        //직원 등록시 annual 생성 및 member에 annual_id 업데이트
        AnnualPlan annualPlan = getWorkStartDate(request.getWorkStartDate());
        Annual annual = annualRepository.save(new Annual(annualPlan.getValue(),member, member.getTeam()));
        //annual테이블 생성후 annualId를 member테이블에 업데이트(영속성)
        //member.updateAnnualId(annual.getAnnualId());
    }

    private AnnualPlan getWorkStartDate(LocalDate workStartDate) {
        // 현재 연도 가져오기
        int currentYear = LocalDate.now().getYear();

        // workStartDate의 연도 가져오기
        int yearOfWorkStartDate = workStartDate.getYear();

        // 이번년도면 11, 다른년도면 15를 반환
        return (currentYear == yearOfWorkStartDate) ? AnnualPlan.THIS_YEAR : AnnualPlan.OTHER;
    }
    @Transactional(readOnly = true)
    public List<MemberResponse> getMembers() {
        return memberRepository.findAllMembersWithTeamTeamName();
    }
}
