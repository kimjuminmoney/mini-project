package com.mini.company.domain.commute;

import com.mini.company.dto.overtime.response.OvertimeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommuteRepository extends JpaRepository<Commute,Long> {

    //Optional<Commute> findByMemberIdAndDate(Long memberId, LocalDate date);
    Optional<Commute> findByMember_MemberIdAndDate(Long memberId, LocalDate date);
    //List<CommuteDetail> findByIdAndDate(Long memberId, LocalDate date);
    //List<CommuteDetail> findByMemberIdAndDateBetween(
    //       Long memberId, LocalDate startDate, LocalDate endDate);
    //@Query("SELECT c FROM Commute c WHERE c.memberId = :memberId AND c.date BETWEEN :startDate AND :endDate")
    @Query("SELECT c FROM Commute c WHERE c.member.id = :memberId AND c.date BETWEEN :startDate AND :endDate")
    List<Commute> findByMemberIdAndDateBetween(@Param("memberId") Long memberId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

//    @Query("SELECT new com.mini.company.dto.overtime.response.OvertimeResponse(m.memberId, m.name, SUM(c.workingMinutes)) " +
//            "FROM Commute c JOIN Member m ON c.memberId = m.memberId " +
//            "WHERE FUNCTION('YEAR', c.date) = :year AND FUNCTION('MONTH', c.date) = :month " +
//            "GROUP BY m.memberId, m.name")
//    List<OvertimeResponse> findOvertimeListByYearAndMonth(@Param("year") int year, @Param("month") int month);
    @Query("SELECT new com.mini.company.dto.overtime.response.OvertimeResponse(m.memberId, m.name, SUM(c.workingMinutes)) " +
            "FROM Member m JOIN m.commuteList c " +
            "WHERE FUNCTION('YEAR', c.date) = :year AND FUNCTION('MONTH', c.date) = :month " +
            "GROUP BY m.memberId, m.name")
    List<OvertimeResponse> findOvertimeListByYearAndMonth(@Param("year") int year, @Param("month") int month);
    @Query("SELECT c FROM Commute c WHERE YEAR(c.date) = :year AND MONTH(c.date) = :month")
    List<Commute> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
