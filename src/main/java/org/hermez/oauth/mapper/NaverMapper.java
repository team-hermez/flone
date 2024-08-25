package org.hermez.oauth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hermez.member.dto.MemberLoginResponse;

@Mapper
public interface NaverMapper {
    @Select("select count(*)>0 from member where refresh_token = #{refreshToken}")
    boolean selectNaverRefreshToken(String refreshToken);

    @Select("select member_id as memberId, role_id as roleId, email, password, name, phone, created_at as createdAt from member where email=#{email} and password=#{password}")
    MemberLoginResponse selectNaverlogin(String refreshToken);
}
