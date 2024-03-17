package com.mini.company.dto.overtime.response;

import com.mini.company.domain.commute.Commute;
import com.mini.company.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class OvertimeResponse {
    private Long memberId;
    private String name;
    private Long overtimeMinutes;

    public OvertimeResponse(Member member, List<Commute> commuteList) {
        this.memberId = member.getMemberId();
        this.name = maskString(member.getName());
        this.overtimeMinutes = overtimeCalProcess(commuteList);
    }

    private String maskString(String str) {
        // 이름의 길이가 1이하면 그냥 반환
        if(str.length() <= 1){
            return str;
        }
        // 이름의 길이가 2이면 성을 제외한 문자를 '*'로 치환
        if (str.length() == 2) {
            return str.charAt(0) + "*";
        }

        // 첫 번째 문자와 마지막 문자를 제외한 중간 문자들을 '*'로 치환
        String middleMasked = str.substring(1, str.length() - 1)
                .replaceAll(".", "*");

        // 결과 문자열 생성
        return str.charAt(0) + middleMasked + str.charAt(str.length() - 1);
    }

    private Long overtimeCalProcess(List<Commute> commutes){
        // 월 근무시간
        Long monthWorkingMinutes = 0L;
        // 휴식시간
        Long breakTime = 60L;
        for (Commute commute : commutes) {
            // 월 근무시간 += 일일 근무시간 - 휴게시간
            monthWorkingMinutes += commute.getWorkingMinutes() - breakTime;
        }

        // 기준 일일근무시간 8 시간
        Long standardDailyWorkingHours = 8L;
        // 기준 월 근무시간 = 월근무일 * 기준 일일근무시간
        Long standardWorkingHours = commutes.size() * standardDailyWorkingHours;
        // 월 근무시간 - ( 월근무 시간 * 60)
        return monthWorkingMinutes - (standardWorkingHours * 60);
    }
}
