package org.hermez.reservation.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hermez.course.dto.CourseListResponse;
import org.hermez.reservation.dto.MyReservationDTO;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.model.Reservation;

/**
 * 예약 패키지의 마이티스 mapper 클래스입니다.
 *
 * @author 허상범
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
  @Select("select reservation_id as reservationId,member_id as memberId,course_id as courseId,reservation_status_id as reservationStatusId,payment_history_id as paymentHistoryId, imp_uid as imp_uid, merchant_uid as merchantUid from reservation where merchant_uid=#{merchantUid}")
  Reservation findById(String merchantUid);

  /**
   * 특정 회원이 예약한 특정 강의의 예약 정보 키값을 가져옵니다.
   *
   * @param memberId 회원 키값
   * @param courseId 강의 키값
   * @return reservation_id 예약 정보 키값
   */
  @Select("select reservation_id as reservationId from reservation  where member_id=#{memberId} and course_id=#{courseId} and reservation.reservation_status_id = 1")
  Integer findMyCourseOne(@Param("memberId") int memberId, @Param("courseId") int courseId);

  /**
   * hermez에서 생성한 주문번호로 예약한 강의의 예약할 때 결제한 금액을 hermez 서버에서 가져옵니다.
   *
   * @param merchantUid 취소/환불시 주문 조회할 hermez에서 생성한 주문번호
   * @return payment_amount hermez서버에 저장된 예약시 결제한 금액
   */
  @Select("select payment_amount as paymentAmount from reservation join payment_history using (payment_history_id) where merchant_uid=#{merchantUid}")
  Integer findPayAmount(String merchantUid);

  /**
   * 예약 상태를 취소로 변경합니다.
   *
   * @param merchantUid 취소/환불시 주문 조회할 hermez에서 생성한 주문번호
   */
  @Update("update reservation set reservation_status_id= 2 where merchant_uid = #{merchantUid}")
  void updateReservationStatus(String merchantUid);

  @Select(
      "select course_id as courseId,reservation_status_id as reservationStatusId, merchant_uid as merchantUid,payment_amount as paymentAmount ,payment_history.created_at as createdAt,cancel_at as cancelAt,title,start_date as startDate,end_date as endDate \n"
          + "from reservation \n"
          + "join payment_history using (payment_history_id) \n"
          + "join course using (course_id) \n"
          + "where member_id=#{memberId} \n"
          + "order by reservation_id desc")
  List<ReservationListResponse> reservationList(int memberId);

  @Select(
      "select course_id as courseId ,start_date as startDate, end_date as endDate,day_of_week as DayOfWeek, start_time as startTime, end_time as endTime \n"
          + " from reservation\n"
          + " join course using (course_id)\n"
          + " join course_schedule using (course_id)\n"
          + " where reservation_status_id=1 and now()<=end_date and member_id=#{memberId}")
  List<MyReservationDTO> findMyReservationList(int memberId);

  @Select(
      "select course_id as courseId ,start_date as startDate, end_date as endDate,day_of_week as DayOfWeek, start_time as startTime, end_time as endTime \n"
          + " from course join course_schedule using (course_id) where course_id=#{courseId}")
  List<MyReservationDTO> findReservationCourseSchedule(int courseId);

  @Select("select count(reservation_id) from reservation where member_id=#{memberId}")
  int countReservations(int memberId);

  @Select(
      "select course_id as courseId,reservation_status_id as reservationStatusId, merchant_uid as merchantUid,payment_amount as paymentAmount ,payment_history.created_at as createdAt,cancel_at as cancelAt,title,start_date as startDate,end_date as endDate \n"
          + "from reservation \n"
          + "join payment_history using (payment_history_id) \n"
          + "join course using (course_id) \n"
          + "where member_id=#{memberId} \n"
          + "order by reservation_id desc \n"
          + "limit #{offset}, #{itemsPerPage}")
  List<ReservationListResponse> selectReservationList(@Param("memberId") int memberId,
      @Param("offset") int offset, @Param("itemsPerPage") int itemsPerPage);
}
