package org.hermez.member.mapper;

import org.apache.ibatis.annotations.*;
import org.hermez.member.dto.*;
import org.hermez.member.model.Member;
import org.springframework.security.core.parameters.P;

/**
 * MyBatis 매퍼 인터페이스 - 회원 관련 데이터베이스 작업합니다.
 *
 * 이 인터페이스는 회원 등록, 로그인, 정보 수정, 비밀번호 변경 등
 * 다양한 회원 관련 데이터베이스 작업을 정의합니다. 각 메서드는
 * SQL 쿼리를 사용하여 데이터베이스와 상호작용합니다.
 *
 * @author 김다은
 */
@Mapper
public interface MemberMapper {

    /**
     * 회원을 데이터베이스에 등록합니다.
     *
     * @param member 등록할 회원 정보
     */
    @Insert("insert into member(role_id, name, email, encoded_password, phone, member_status, created_at) " +
            "values (1, #{name}, #{email}, #{encodedPassword}, #{phone}, 1, NOW())")
    void registerMember(Member member);

    /**
     * 이메일을 사용하여 회원을 로그인합니다.
     *
     * @param memberLoginRequest 로그인 요청 정보 (이메일)
     * @return 로그인된 회원의 응답 정보
     */
    @Select("select member_id as memberId, role_id as roleId, email, social_login_id as socialLoginId, " +
            "encoded_password as encodedPassword, name, phone, created_at as createdAt " +
            "from member where email=#{email}")
    MemberLoginResponse loginMember(MemberLoginRequest memberLoginRequest);

    /**
     * 회원의 전화번호를 업데이트합니다.
     *
     * @param myAccountEditRequest 업데이트할 계정 정보
     */
    @Update("UPDATE member SET phone = #{phoneConfirm} WHERE member_id = #{memberId}")
    void updateMyAccountPhone(MyAccountEditRequest myAccountEditRequest);

    /**
     * 회원의 비밀번호를 업데이트합니다.
     *
     * @param myAccountEditRequest 업데이트할 계정 정보
     */
    @Update("UPDATE member SET encoded_password = #{passwordConfirm} WHERE member_id = #{memberId}")
    void updateMyAccountPassword(MyAccountEditRequest myAccountEditRequest);

    /**
     * 소셜 로그인 정보를 사용하여 회원을 업데이트합니다.
     *
     * @param memberSocialLoginResponse 업데이트할 소셜 로그인 회원 정보
     */
    @Update("UPDATE member SET role_id =1, name=#{name}, social_login_id = #{socialId}, email=#{email}, " +
            "encoded_password = #{encodedPassword}, phone = #{phone}, member_status = 1, created_at=now() " +
            "WHERE social_login_id = #{socialId}")
    void updateSocialMember(MemberSocialLoginResponse memberSocialLoginResponse);

    /**
     * 이메일 존재 여부를 확인합니다.
     *
     * @param memberLoginRequest 이메일 존재 여부를 확인할 정보
     * @return 이메일이 존재하면 1, 그렇지 않으면 0
     */
    @Select("SELECT COUNT(1) > 0 FROM member WHERE email = #{email}")
    int selectMemberEmailExist(MemberLoginRequest memberLoginRequest);

    /**
     * 회원 등록 시 이메일 존재 여부를 확인합니다.
     *
     * @param memberRegisterRequest 이메일 존재 여부를 확인할 정보
     * @return 이메일이 존재하면 1, 그렇지 않으면 0
     */
    @Select("SELECT COUNT(1) > 0 FROM member WHERE email = #{email}")
    int selectMemberRegisterEmailExist(MemberRegisterRequest memberRegisterRequest);

    /**
     * 비밀번호 확인을 위한 전화번호와 이메일 일치 여부를 확인합니다.
     *
     * @param memberCheckPasswordRequest 비밀번호 확인을 위한 정보
     * @return 일치하면 1, 그렇지 않으면 0
     */
    @Select("SELECT COUNT(1) > 0 FROM member WHERE phone = #{phone} and email = #{email}")
    int selectCheckPassword(MemberCheckPasswordRequest memberCheckPasswordRequest);

    /**
     * 전화번호 존재 여부를 확인합니다.
     *
     * @param memberRegisterRequest 전화번호 존재 여부를 확인할 정보
     * @return 전화번호가 존재하면 1, 그렇지 않으면 0
     */
    @Select("SELECT COUNT(1) > 0 FROM member WHERE phone = #{phone}")
    int selectMemberPhoneExist(MemberRegisterRequest memberRegisterRequest);

    /**
     * 회원 ID를 사용하여 계정 정보를 조회합니다.
     *
     * @param memberId 조회할 회원의 ID
     * @return 회원의 계정 정보
     */
    @Select("select member_id as memberId, email, name, encoded_password as encodedPassword, phone " +
            "from member where member_id = #{memberId};")
    MyAccountResponse getMyAccount(int memberId);

    /**
     * 회원의 상태를 비활성화로 변경합니다.
     *
     * @param memberId 상태를 변경할 회원의 ID
     */
    @Insert("update member set member_status = 0 where member_id = #{memberId}")
    void insertMemberStatus(int memberId);

    /**
     * 전화번호 존재 여부를 확인합니다. (인스트럭터와 회원)
     *
     * @param myAccountEditRequest 전화번호 존재 여부를 확인할 정보
     * @return 전화번호가 존재하면 1, 그렇지 않으면 0
     */
    @Select("SELECT COUNT(1) > 0 FROM instructor i, member m WHERE m.phone = #{phoneConfirm}")
    int selectPhoneExists(MyAccountEditRequest myAccountEditRequest);

    /**
     * 이메일과 회원 상태를 사용하여 회원 존재 여부를 확인합니다.
     *
     * @param memberLoginRequest 이메일과 회원 상태를 확인할 정보
     * @return 회원이 존재하면 1, 그렇지 않으면 0
     */
    @Select("SELECT COUNT(1) > 0 from member where email = #{email} and member_status = #{memberStatus}")
    int selectMemberStatus(MemberLoginRequest memberLoginRequest);

    /**
     * 소셜 로그인 ID를 사용하여 회원 정보를 조회합니다.
     *
     * @param socialLoginId 소셜 로그인 ID
     * @return 소셜 로그인 ID와 일치하는 회원의 정보
     */
    @Select("SELECT member_id as member_id, m.role_id as roleId, m.name as name, m.email as email, " +
            "m.encoded_password as encodedPassword, m.phone as phone, m.social_login_id as socialLoginId, " +
            "m.created_at as createdAt from member m, social_login s where s.SOCIAL_LOGIN_ID = #{socialLoginId}")
    MemberLoginResponse selectSocialLoginId(String socialLoginId);

    /**
     * 이메일과 전화번호를 사용하여 비밀번호를 변경합니다.
     *
     * @param memberCheckPasswordRequest 비밀번호 변경을 위한 정보
     */
    @Update("UPDATE member SET encoded_password = #{passwordConfirm} WHERE phone=#{phone} and email=#{email}")
    void changePassword(MemberCheckPasswordRequest memberCheckPasswordRequest);
}
