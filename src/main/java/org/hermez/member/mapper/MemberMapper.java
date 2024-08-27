package org.hermez.member.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hermez.member.dto.*;
import org.hermez.member.model.*;


@Mapper
public interface MemberMapper {
    @Insert("insert into member(role_id, name, email, encoded_password, phone, social_login_id, member_status, created_at) values (1, #{name}, #{email}, #{encodedPassword}, #{phone}, #{socialLoginId}, 1, NOW())")
    void registerMember(Member member);
    //    @Select("select member_id as memberId, role_id as roleId, email, encoded_password as encodedPassword, name, phone, created_at as createdAt from member where email=#{email} and encoded_password=#{password}")
    @Select("select member_id as memberId, role_id as roleId, email, social_login_id as socialLoginId, encoded_password as encodedPassword, name, phone, created_at as createdAt from member where email=#{email}")
    MemberLoginResponse loginMember(MemberLoginRequest memberLoginRequest);

    @Select("SELECT COUNT(1) > 0 FROM member WHERE email = #{email}")
    boolean selectMemberEmailExist(MemberLoginRequest memberLoginRequest);

    @Select("SELECT COUNT(1) > 0 FROM member WHERE email = #{email}")
    boolean selectMemberRegisterEmailExist(MemberRegisterRequest memberRegisterRequest);

    @Select("SELECT COUNT(1) > 0 FROM member WHERE phone = #{phone}")
    boolean selectMemberPhoneExist(MemberRegisterRequest memberRegisterRequest);

    @Select("select member_id as memberId, email, name, encoded_password, phone from member where member_id = #{memberId};")
    MyAccountResponse getMyAccount(int memberId);

    @Insert("update member set member_status = 0 where member_id = #{memberId}")
    void insertMemberStatus(int memberId);

    @Select("SELECT COUNT(1) > 0 FROM instructor i, member m WHERE m.phone = #{phoneConfirm}")
    int selectPhoneExists(MyAccountEditRequest myAccountEditRequest);

    @Update("UPDATE member " +
            "SET phone = #{phoneConfirm} " +
            "WHERE member_id = #{memberId}")
    void updateMyAccountPhone(MyAccountEditRequest myAccountEditRequest);

    @Update("UPDATE member SET encoded_password = #{passwordConfirm} WHERE member_id = #{memberId}")
    void updateMyAccountPassword(MyAccountEditRequest myAccountEditRequest);
}
