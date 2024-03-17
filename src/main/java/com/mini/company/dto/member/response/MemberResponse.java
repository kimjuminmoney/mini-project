package com.mini.company.dto.member.response;

import com.mini.company.domain.member.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    private String memberName;
    private String teamName;
    private Role role;
    private LocalDate birthday;
    private LocalDate workStartDate;
}
