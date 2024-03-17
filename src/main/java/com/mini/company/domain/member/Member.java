package com.mini.company.domain.member;

import com.mini.company.domain.annual.Annual;
import com.mini.company.domain.commute.Commute;
import com.mini.company.domain.team.Team;
import com.mini.company.dto.member.request.MemberCreateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId = null;

    //private Long teamId;
    @NonNull
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Convert(converter = RoleConverter.class)
    private Role role;

    @Column(name = "work_start_date")
    private LocalDate workStartDate;

    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_Id")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Annual> annualList;

    @OneToMany(mappedBy = "member")
    private List<Commute> commuteList = new ArrayList<>();

    public Member(MemberCreateRequest request, Team team) {

        this.name = request.getName();
        this.role = request.getRole();
        this.workStartDate = request.getWorkStartDate();
        this.birthday = request.getBirthday();
        this.team = team;
    }
//    public void updateAnnualId(Long annualId){
//        this.annualId = annualId;
//    }


    public void commute(){
        this.commuteList.add(new Commute(this));
    }

    // 출근 기록을 필터링하는 메소드
    public List<Commute> filterCommutesByYearMonth(YearMonth yearMonth) {
        // 문자열을 YearMonth 객체로 변환

        // commuteList에서 해당 연도와 월에 해당하는 출근 기록만 필터링
        return this.commuteList.stream()
                .filter(commute -> {
                    LocalDate date = commute.getDate();
                    return date != null && YearMonth.from(date).equals(yearMonth);
                })
                .collect(Collectors.toList());
    }

}
