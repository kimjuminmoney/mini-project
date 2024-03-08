package com.mini.company.controller.member;

import com.mini.company.domain.member.Member;
import com.mini.company.dto.member.request.MemberCreateRequest;
import com.mini.company.service.member.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping("/member")
    public void saveMember(@RequestBody MemberCreateRequest request){
        memberService.saveMember(request);
    }

    @GetMapping("/members")
    public List<Member> getMembers(){
        return memberService.getMembers();
    }
}
