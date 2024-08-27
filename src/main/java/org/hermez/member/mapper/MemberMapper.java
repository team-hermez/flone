package org.hermez.member.mapper;

import org.apache.ibatis.annotations.*;
import org.hermez.member.dto.*;
import org.hermez.member.model.*;
import org.springframework.security.core.parameters.P;


@Mapper
public interface MemberMapper {
    @Insert("insert into member(role_id, name, email, encoded_password, phone, member_status, created_at) values (1, #{name}, #{email}, #{encodedPassword}, #{phone}, 1, NOW())")
    void registerMember(Member member);
    //   @Select("select member_id as memberId, role_id as roleId, email, encoded_password as encodedPassword, name, phone, created_at as createdAt from member where email=#{email} and encoded_password=#{password}")
    @Select("select member_id as memberId, role_id as roleId, email, social_login_id as socialLoginId, encoded_password as encodedPassword, name, phone, created_at as createdAt from member where email=#{email}")
    MemberLoginResponse loginMember(MemberLoginRequest memberLoginRequest);

    @Update("UPDATE member " +
            "SET phone = #{phoneConfirm} " +
            "WHERE member_id = #{memberId}")
    void updateMyAccountPhone(MyAccountEditRequest myAccountEditRequest);

    @Update("UPDATE member SET encoded_password = #{passwordConfirm} WHERE member_id = #{memberId}")
    void updateMyAccountPassword(MyAccountEditRequest myAccountEditRequest);
    @Update("UPDATE member SET role_id =1, name=#{name}, social_login_id = #{socialId}, email=#{email}, encoded_password = #{encodedPassword}, phone = #{phone}, member_status = 1, created_at=now() WHERE social_login_id = #{socialId}")
    void updateSocialMember(MemberSocialLoginResponse memberSocialLoginResponse);

    @Select("SELECT COUNT(1) > 0 FROM member WHERE email = #{email}")
    int selectMemberEmailExist(MemberLoginRequest memberLoginRequest);

    @Select("SELECT COUNT(1) > 0 FROM member WHERE email = #{email}")
    int selectMemberRegisterEmailExist(MemberRegisterRequest memberRegisterRequest);

    @Select("SELECT COUNT(1) > 0 FROM member WHERE phone = #{phone} and email = #{email}")
    int selectCheckPassword(MemberCheckPasswordRequest memberCheckPasswordRequest);

    @Select("SELECT COUNT(1) > 0 FROM member WHERE phone = #{phone}")
    int selectMemberPhoneExist(MemberRegisterRequest memberRegisterRequest);

    @Select("select member_id as memberId, email, name, encoded_password, phone from member where member_id = #{memberId};")
    MyAccountResponse getMyAccount(int memberId);

    @Insert("update member set member_status = 0 where member_id = #{memberId}")
    void insertMemberStatus(int memberId);

    @Select("SELECT COUNT(1) > 0 FROM instructor i, member m WHERE m.phone = #{phoneConfirm}")
    int selectPhoneExists(MyAccountEditRequest myAccountEditRequest);

    @Select("SELECT COUNT(1) > 0 from member where email = #{email} and member_status = #{memberStatus}")
    int selectMemberStatus(MemberLoginRequest memberLoginRequest);

    @Select("SELECT member_id as member_id, m.role_id as roleId, m.name as name, m.email as email, m.encoded_password as encodedPassword, m.phone as phone, m.social_login_id as socialLoginId, m.created_at as createdAt from member m , social_login s where s.SOCIAL_LOGIN_ID = #{socialLoginId}")
    MemberLoginResponse selectSocialLoginId(String socialLoginId);

    @Update("UPDATE member SET encoded_password = #{passwordConfirm} WHERE phone=#{phone} and email=#{email}")
    void changePassword(MemberCheckPasswordRequest memberCheckPasswordRequest);


}