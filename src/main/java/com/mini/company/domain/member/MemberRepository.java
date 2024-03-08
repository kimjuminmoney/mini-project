package com.mini.company.domain.member;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByTeamNameAndRole(String name, boolean b);
    Long countByTeamName(@NonNull String name);
}
