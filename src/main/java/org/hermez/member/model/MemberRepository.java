package org.hermez.member.model;

import lombok.RequiredArgsConstructor;
import org.hm.member.mapper.MemberMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final MemberMapper memberMapper;

    public void save(Member member) {
        memberMapper.registerMember(member);
    }
    public Member loginMember(Member member){
        member = memberMapper.loginMember(member);
        return member;
    }
}
