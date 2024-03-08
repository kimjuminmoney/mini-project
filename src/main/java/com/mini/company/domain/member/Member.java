package com.mini.company.domain.member;

import com.mini.company.dto.member.request.MemberCreateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(name = "team_name")
    private String teamName;
    @NonNull
    private String name;
    private boolean role;
    @Column(name = "work_start_date")
    private LocalDate workStartDate;
    private LocalDate birthday;

    public Member(MemberCreateRequest request) {

        this.name = request.getName();
        this.role = request.isRole();
        this.teamName = request.getTeamName();
        this.workStartDate = request.getWorkStartDate();
        this.birthday = request.getBirthday();
    }

}
