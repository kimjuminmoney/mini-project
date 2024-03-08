package com.mini.company.service.team;

import com.mini.company.domain.member.Member;
import com.mini.company.domain.member.MemberRepository;
import com.mini.company.domain.team.Team;
import com.mini.company.domain.team.TeamRepository;
import com.mini.company.dto.team.request.TeamCreateRequest;
import com.mini.company.dto.team.response.TeamResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
    }


    public void saveTeam(TeamCreateRequest request) {
        teamRepository.save(new Team(request.getName()));
    }

    public List<TeamResponse> getTeams() {
        List<TeamResponse> teamResponses = new ArrayList<>();
                List<Team> teams = teamRepository.findAll();
        for (Team team : teams) {
            // findByTeamNameAndRole에서 반환된 Optional을 처리
            Optional<Member> memberOptional = memberRepository.findByTeamNameAndRole(team.getName(), true);
            // Optional이 값을 포함하고 있다면 값을 가져오고, 그렇지 않으면 "-"를 사용
            String leadName = memberOptional.map(Member::getName).orElse("-");
            teamResponses.add(
                    new TeamResponse(team.getName(),
                            leadName,
                            memberRepository.countByTeamName(team.getName()))
            );
        }

        return teamResponses;
    }
}
