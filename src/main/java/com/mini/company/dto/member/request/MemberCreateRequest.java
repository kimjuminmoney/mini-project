package com.mini.company.dto.member.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberCreateRequest {
    private String name;
    private boolean role;
    private LocalDate workStartDate;
    private LocalDate birthday;
    private String teamName;
}
