package org.hermez.member.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hm.member.model.Member;

@Mapper
public interface MemberMapper {
    @Insert("insert into member(role_id, name, email, password, phone) values (1, #{name},#{email},#{password},#{phone})")
    void registerMember(Member member);

    @Select("select * from member where email=#{email} and password=#{password}")
    Member loginMember(Member member);
}
