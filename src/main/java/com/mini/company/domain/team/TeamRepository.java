package com.mini.company.domain.team;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team,Long> {

    Optional<Team> findByName(String teamName);

}
