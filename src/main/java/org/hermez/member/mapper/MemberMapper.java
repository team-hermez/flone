package org.hermez.member.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.dto.MemberLoginResponse;
import org.hermez.member.dto.MyAccountEditRequest;
import org.hermez.member.dto.MyAccountResponse;
import org.hermez.member.model.*;


@Mapper
public interface MemberMapper {
    @Insert("insert into member(role_id, name, email, password, phone, created_at) values (1, #{name}, #{email}, #{password}, #{phone}, NOW())")
    void registerMember(Member member);

    @Select("select member_id as memberId, role_id as roleId, email, password, name, phone, created_at as createdAt from member where email=#{email} and password=#{password}")
    MemberLoginResponse loginMember(MemberLoginRequest memberLoginRequest);

    @Select("SELECT COUNT(1) > 0 FROM member WHERE email = #{email}")
    boolean selectMemberEmailExist(MemberLoginRequest memberLoginRequest);

    @Select("select member_id as memberId, email, name, password, phone from member where member_id = #{memberId};")
    MyAccountResponse getMyAccount(int memberId);

    @Select("SELECT COUNT(1) > 0 FROM instructor i, member m WHERE m.phone = #{phoneConfirm}")
    int selectPhoneExists(MyAccountEditRequest myAccountEditRequest);

    @Update("UPDATE member " +
            "SET phone = #{phoneConfirm} " +
            "WHERE member_id = #{memberId}")
    void updateMyAccountPhone(MyAccountEditRequest myAccountEditRequest);

    @Update("UPDATE member SET password = #{passwordConfirm} WHERE member_id = #{memberId}")
    void updateMyAccountPassword(MyAccountEditRequest myAccountEditRequest);
}
