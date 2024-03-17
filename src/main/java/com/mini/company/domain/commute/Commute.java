package com.mini.company.domain.commute;

import com.mini.company.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
public class Commute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commute_id")
    private Long commuteId;
//    @Column(name = "member_id")
//    private Long memberId;
    @JoinColumn(nullable = false,name = "member_id")
    @ManyToOne
    private Member member;
    private LocalDate date;

    private LocalTime start;
    private LocalTime end;
    @Column(name = "workingminutes")
    private Long workingMinutes;

    public Commute(Member member) {
        this.member = member;
    }
    public void updateDate(){
        this.date = LocalDate.now();
    }

    public void updateStart(){
        this.start = LocalTime.now();
    }
    public void updateEnd(){
        this.end = LocalTime.now();
    }

    public Commute() {
    }

    public void updateWorkingMinutes() {
        // start와 end 사이의 Duration을 계산
        Duration duration = Duration.between(start, end);
        // 계산된 Duration을 분으로 변환
        this.workingMinutes = duration.toMinutes();
    }


}
