package com.mini.company.domain.commute;

import com.mini.company.dto.commute.response.CommuteDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommuteRepository extends JpaRepository<Commute,Long> {

    Optional<Commute> findByMemberIdAndDate(Long memberId, LocalDate date);

    //List<CommuteDetail> findByIdAndDate(Long memberId, LocalDate date);
    List<CommuteDetail> findByMemberIdAndDateBetween(
            Long memberId, LocalDate startDate, LocalDate endDate);
}
