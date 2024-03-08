package com.mini.company.domain.commute;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Commute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "member_id")
    private Long memberId;
    private LocalDate date;

    private LocalTime start;
    private LocalTime end;
    @Column(name = "workingminutes")
    private long workingMinutes;

    public Commute(Long memberId) {
        this.memberId = memberId;
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
        long minutes = duration.toMinutes();
        this.workingMinutes = minutes;
    }

}
