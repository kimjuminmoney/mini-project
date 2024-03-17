package com.mini.company.domain.annual;

import com.mini.company.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AnnualRepository extends JpaRepository<Annual,Long> {

    Annual findTopByMember_MemberIdOrderByAnnualIdDesc(Long memberId);

//    @Query("SELECT a FROM Annual a WHERE a.member = :member AND a.date BETWEEN :startDate AND :endDate")
//    List<Annual> findAnnualsByMemberAndYearMonth(@Param("member") Member member, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
