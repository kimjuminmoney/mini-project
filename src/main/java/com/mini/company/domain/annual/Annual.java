package com.mini.company.domain.annual;

import com.mini.company.domain.member.Member;
import com.mini.company.domain.team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Annual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annualId;

    private int remainAnnual;
    private int usedAnnual;
    private LocalDate usingDate;
    //private Long memberId;
    //private Long teamId;
    @JoinColumn(nullable = false,name = "member_id")
    @ManyToOne
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;

    //member 등록시 생성자
    public Annual(int remainAnnual, Member member, Team team) {
        this.remainAnnual = remainAnnual;
        this.member = member;
        this.team = team;
    }
    //연차 사용시 생성자
    public Annual(AnnualTemp annualTemp){
        this.remainAnnual = annualTemp.getRemainAnnual();
        this.usedAnnual = annualTemp.getUsedAnnual();
        this.usingDate = annualTemp.getUsingDate();
        this.member = annualTemp.getMember();
        this.team = annualTemp.getTeam();
    }
}
