package org.hermez.oauth.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hermez.member.dto.MemberLoginResponse;

/**
 * 소셜 로그인과 관련된 데이터베이스 작업을 수행하는 MyBatis Mapper 인터페이스입니다.
 *
 * 이 인터페이스는 소셜 로그인 ID를 사용하여 회원 정보를 조회하고,
 * 새로운 소셜 로그인 정보와 회원 정보를 데이터베이스에 삽입하는 기능을 제공합니다.
 *
 * @author 김다은
 */
@Mapper
public interface SocialMapper {

    /**
     * 주어진 소셜 로그인 ID가 데이터베이스에 존재하는지 확인합니다.
     *
     * @param socialLoginId 소셜 로그인 ID
     * @return 해당 소셜 로그인 ID가 존재하면 1, 그렇지 않으면 0
     */
    @Select("select count(*)>0 from member where social_login_id = #{socialLoginId}")
    int selectNaverSocialId(@Param("socialLoginId") String socialLoginId);

    /**
     * 새로운 소셜 로그인 정보를 데이터베이스에 삽입합니다.
     *
     * @param socialLoginId 소셜 로그인 ID
     * @param refreshToken 새로 발급받은 리프레시 토큰
     */
    @Insert("INSERT INTO social_login(SOCIAL_LOGIN_ID, REFRESH_TOKEN) values (#{socialLoginId}, #{refreshToken})")
    void insertSocialLogin(@Param("socialLoginId") String socialLoginId, @Param("refreshToken") String refreshToken);

    /**
     * 새로운 회원 정보를 데이터베이스에 삽입합니다.
     *
     * @param socialLoginId 소셜 로그인 ID
     */
    @Insert("INSERT INTO member(role_id, name, email, encoded_password, phone, social_login_id, member_status, created_at) values (1, '1', '1', '1','1',#{socialLoginId},1,Now())")
    void insertMemberSocial(@Param("socialLoginId") String socialLoginId);

    /**
     * 주어진 소셜 로그인 ID로 회원 정보를 조회합니다.
     *
     * @param socialLoginId 소셜 로그인 ID
     * @return 회원 정보
     */
    @Select("select member_id as memberId, role_id as roleId, email, encoded_password as encodedPassword, name, phone, social_login_id as socialLoginId, created_at as createdAt from member where social_login_id = #{socialLoginId}")
    MemberLoginResponse selectNaverlogin(@Param("socialLoginId") String socialLoginId);
}
