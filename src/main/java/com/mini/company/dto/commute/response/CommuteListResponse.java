package com.mini.company.dto.commute.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CommuteListResponse {

    private List<CommuteDetail> detail;
    private int sum;
}
