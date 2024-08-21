package org.hermez.reservation.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hermez.reservation.model.Reservation;

/**
 * 예약 패키지의 마이티스 mapper 클래스입니다.
 */
@Mapper
public interface ReservationMapper {

  /**
   * 예약정보를 저장합니다.
   *
   * @param reservation 예약 정보
   */
  @Insert(
      " insert into reservation (member_id, course_id, reservation_status_id, payment_history_id, merchant_uid, imp_uid) "
          + "values (#{memberId}, #{courseId}, #{reservationStatusId}, #{paymentHistoryId}, #{merchantUid},#{imp_uid})")
  void save(Reservation reservation);

  /**
   * hermez에서 생성한 주문번호로 예약 정보를 가져옵니다.
   *
   * @param merchantUid hermez에서 생성한 주문번호
   * @return reservation 예약 정보
   */
  @Select(
      "select reservation_id ,member_id ,course_id,reservation_status_id ,payment_history_id, imp_uid,merchant_uid\n"
          + " from reservation\n"
          + " where merchant_uid=#{merchantUid}")
  Reservation findById(String merchantUid);

  /**
   * 특정 회원이 예약한 특정 강의의 예약 정보 키값을 가져옵니다.
   *
   * @param memberId 회원 키값
   * @param courseId 강의 키값
   * @return reservation_id 예약 정보 키값
   */
  @Select("select reservation_id from reservation  where member_id=#{memberId} and course_id=#{courseId} and reservation.reservation_status_id = 1")
  Integer findMyCourseOne(@Param("memberId") int memberId, @Param("courseId") int courseId);

  /**
   * hermez에서 생성한 주문번호로 예약한 강의의 예약할 때 결제한 금액을 hermez 서버에서 가져옵니다.
   *
   * @param merchantUid hermez에서 생성한 주문번호
   * @return payment_amount hermez서버에 저장된 예약시 결제한 금액
   */
  @Select("select payment_amount from reservation join payment_history using (payment_history_id) where merchant_uid=#{merchantUid}")
  Double findPayAmount(String merchantUid);

}
