package com.mini.company.domain.member;

import com.mini.company.dto.member.response.MemberResponse;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByTeamTeamIdAndRole(Long teamId, Role role);
    Long countByTeamTeamId(@NonNull Long teamId);

    @Query("SELECT new com.mini.company.dto.member.response.MemberResponse(m.name, t.name, m.role, m.birthday, m.workStartDate) " +
            "FROM Member m JOIN m.team t")
    List<MemberResponse> findAllMembersWithTeamTeamName();
    }
