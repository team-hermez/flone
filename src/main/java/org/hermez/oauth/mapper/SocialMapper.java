package org.hermez.oauth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hermez.member.dto.MemberLoginResponse;

@Mapper
public interface SocialMapper {
    @Select("select count(*)>0 from member where social_login_id = #{socialLoginId}")
    boolean selectNaverSocialId(String socialLoginId);

    @Select("select member_id as memberId, role_id as roleId, email, encoded_password as encodedPassword, name, phone, social_login_id as socialLoginId, created_at as createdAt from member where social_login_id = #{socialLoginId}")
    MemberLoginResponse selectNaverlogin(String socialLoginId);
}
