package com.mini.company.domain.annual;

import com.mini.company.domain.member.Member;
import com.mini.company.domain.team.Team;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class AnnualTemp {
    private int remainAnnual;
    private int usedAnnual;
    private LocalDate usingDate;
    //private Long memberId;
    private Member member;
    private Team team;

    public AnnualTemp(Annual annual, LocalDate date) {
        int tempRemainAnnual = annual.getRemainAnnual();
        int tempUsedAnnual = annual.getUsedAnnual();
        this.remainAnnual = --tempRemainAnnual;
        this.usedAnnual = ++tempUsedAnnual;
        this.usingDate = date;
        this.member = annual.getMember();
        this.team = annual.getTeam();
    }
}
