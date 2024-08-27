package org.hermez.oauth.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hermez.member.dto.MemberLoginResponse;


@Mapper
public interface SocialMapper {
    @Select("select count(*)>0 from member where social_login_id = #{socialLoginId}")
    int selectNaverSocialId(@Param("socialLoginId") String socialLoginId);

    @Insert("INSERT INTO social_login(SOCIAL_LOGIN_ID, REFRESH_TOKEN) values (#{socialLoginId}, #{refreshToken})")
    void insertSocialLogin(@Param("socialLoginId") String socialLoginId,@Param("refreshToken") String refreshToken);

    @Insert("INSERT INTO member(role_id, name, email, encoded_password, phone, social_login_id, member_status, created_at) values (1, '1', '1', '1','1',#{socialLoginId},1,Now())")
    void insertMemberSocial(@Param("socialLoginId") String socialLoginId);

    @Select("select member_id as memberId, role_id as roleId, email, encoded_password as encodedPassword, name, phone, social_login_id as socialLoginId, created_at as createdAt from member where social_login_id = #{socialLoginId}")
    MemberLoginResponse selectNaverlogin(String socialLoginId);
}
