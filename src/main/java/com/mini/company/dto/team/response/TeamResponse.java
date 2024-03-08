package com.mini.company.dto.team.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeamResponse {
    private String name;
    private String manager;
    private Long memberCount;

}
