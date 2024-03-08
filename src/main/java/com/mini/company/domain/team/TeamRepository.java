package com.mini.company.domain.team;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {

}
