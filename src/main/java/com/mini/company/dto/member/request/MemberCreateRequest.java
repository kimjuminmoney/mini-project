package com.mini.company.dto.member.request;

import com.mini.company.domain.member.Role;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberCreateRequest {
    private String name;
    private Role role;
    private LocalDate workStartDate;
    private LocalDate birthday;
    private Long teamId;
    private String teamName;
}
