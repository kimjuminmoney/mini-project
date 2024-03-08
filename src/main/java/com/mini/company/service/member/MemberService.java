package com.mini.company.service.member;

import com.mini.company.domain.member.Member;
import com.mini.company.domain.member.MemberRepository;
import com.mini.company.dto.member.request.MemberCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void saveMember(MemberCreateRequest request) {
        memberRepository.save(new Member(request));
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }
}
