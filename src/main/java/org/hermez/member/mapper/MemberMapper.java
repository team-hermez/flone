package org.hermez.member.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.model.*;


@Mapper
public interface MemberMapper {
    @Insert("insert into member(role_id, name, email, password, phone, created_at) values (1, #{name}, #{email}, #{password}, #{phone}, NOW())")
    void registerMember(Member member);

    @Select("select member_id as memberId, role_id as roleId, email, password, name, phone, created_at as createdAt from member where email=#{email} and password=#{password}")
    Member loginMember(MemberLoginRequest memberLoginRequest);
}
