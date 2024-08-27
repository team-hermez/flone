package org.hermez.reservation.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hermez.course.dto.CourseListResponse;
import org.hermez.reservation.dto.MyPaymentDetailResponse;
import org.hermez.reservation.dto.MyReservationDTO;
import org.hermez.reservation.dto.MyReservedReservationDTO;
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
   * 예약 정보를 데이터베이스에 저장합니다.
   *
   * 이 메서드는 주어진 {@link Reservation} 객체를 데이터베이스에 삽입합니다.
   * 삽입되는 정보에는 예약자의 ID, 강의 ID, 예약 상태, 결제 기록 ID, 상점 주문 ID, 그리고 결제 ID가 포함됩니다.
   *
   * @param reservation 저장할 예약 정보 {@link Reservation} 객체.
   */
  @Insert(
      " insert into reservation (member_id, course_id, reservation_status_id, payment_history_id, merchant_uid, imp_uid) "
          + "values (#{memberId}, #{courseId}, #{reservationStatusId}, #{paymentHistoryId}, #{merchantUid},#{imp_uid})")
  void save(Reservation reservation);

  /**
   * 주어진 주문 번호(merchantUid)에 대한 예약 정보를 조회합니다.
   *
   * 이 메서드는 Hermez에서 생성된 주문 번호를 사용하여 예약 정보를 검색합니다.
   *
   * @param merchantUid Hermez에서 생성된 주문 번호.
   * @return 주어진 주문 번호에 해당하는 {@link Reservation} 객체.
   */
  @Select("select reservation_id as reservationId,member_id as memberId,course_id as courseId,reservation_status_id as reservationStatusId,payment_history_id as paymentHistoryId, imp_uid as imp_uid, merchant_uid as merchantUid from reservation where merchant_uid=#{merchantUid}")
  Reservation findById(String merchantUid);

  /**
   * 특정 회원이 특정 강의에 대해 예약한 예약 정보의 ID를 조회합니다.
   *
   * 이 메서드는 특정 회원 ID와 강의 ID를 사용하여 예약 정보의 ID를 반환합니다.
   * 예약 상태가 활성(1)인 경우만 조회됩니다.
   *
   * @param memberId 회원 키값
   * @param courseId 강의 키값
   * @return 예약 정보 키값
   */
  @Select("select reservation_id as reservationId from reservation  where member_id=#{memberId} and course_id=#{courseId} and reservation.reservation_status_id = 1")
  Integer findMyCourseOne(@Param("memberId") int memberId, @Param("courseId") int courseId);

  /**
   * 주어진 주문 번호(merchantUid)에 대한 결제한 강의에 대한 금액을 조회합니다.
   *
   * 이 메서드는 Hermez에서 생성된 주문 번호를 사용하여 결제한 강의 대한 금액을 검색합니다.
   * 결제 금액은 강의 정보에서 가져옵니다.
   *
   * @param merchantUid Hermez에서 생성된 주문 번호
   * @return 예약 시 결제한 금액
   */

  @Select("select course_price  from course where course_id = (select course_id from reservation join payment_history using (payment_history_id)where merchant_uid=#{merchantUid})")
  Integer findPayAmount(String merchantUid);

  /**
   * 주어진 주문 번호(merchantUid)에 대해 예약 상태를 취소로 변경합니다.
   *
   * 이 메서드는 예약 상태를 취소(2)로 업데이트하여, 해당 예약을 취소된 상태로 표시합니다.
   *
   * @param merchantUid Hermez에서 생성된 주문 번호
   */
  @Update("update reservation set reservation_status_id= 2 where merchant_uid = #{merchantUid}")
  void updateReservationStatus(String merchantUid);

  /**
   * 특정 회원의 활성 예약 목록을 조회합니다.
   *
   * 이 메서드는 현재 날짜까지의 유효한 예약 정보를 반환합니다.
   * 반환된 리스트는 예약 ID를 기준으로 정렬됩니다.
   *
   * @param memberId 회원 키값
   * @return 특정 회원의 활성 예약 목록 {@link MyReservationDTO} 객체의 리스트
   */
  @Select(
      "select course_id as courseId ,start_date as startDate, end_date as endDate,day_of_week as DayOfWeek, start_time as startTime, end_time as endTime \n"
          + " from reservation\n"
          + " join course using (course_id)\n"
          + " join course_schedule using (course_id)\n"
          + " where reservation_status_id=1 and now()<=end_date and member_id=#{memberId}")
  List<MyReservationDTO> findMyReservationList(int memberId);

  /**
   * 특정 강의의 예약 일정 정보를 조회합니다.
   *
   * 이 메서드는 주어진 강의 ID를 기반으로 강의 일정 정보를 반환합니다.
   *
   * @param courseId 강의 키값
   * @return 특정 강의의 예약 일정 정보 {@link MyReservationDTO} 객체의 리스트
   */
  @Select(
      "select course_id as courseId ,start_date as startDate, end_date as endDate,day_of_week as DayOfWeek, start_time as startTime, end_time as endTime \n"
          + " from course join course_schedule using (course_id) where course_id=#{courseId}")
  List<MyReservationDTO> findReservationCourseSchedule(int courseId);

  /**
   * 특정 회원이 예약한 총 예약 수를 조회합니다.
   *
   * @param memberId 회원 키값
   * @return 특정 회원이 예약한 총 예약 수
   */
  @Select("select count(reservation_id) from reservation where member_id=#{memberId}")
  int countReservations(int memberId);

  /**
   * 전체 예약 수를 조회합니다.
   *
   * @return 전체 예약 수
   */
  @Select("select count(reservation_id) from reservation")
  int countReservationsAll();

  /**
   * 특정 강의에 대한 총 예약 수를 조회합니다.
   *
   * @param courseId 강의 키값
   * @return 특정 강의에 대한 총 예약 수
   */
  @Select("select count(reservation_id) from reservation where course_id=#{courseId}")
  int countReservationsAllByCourseId(int courseId);

  /**
   * 전체 환불된 예약 수를 조회합니다.
   *
   * @return 전체 환불된 예약
   */
  @Select("select count(reservation_id) from reservation where reservation_status_id=2")
  int countRefundAll();

  /**
   * 특정 회원이 예약한 강의 중 현재 날짜까지 유효한 강의 수를 조회합니다.
   *
   * @param memberId 회원 키값
   * @return 특정 회원이 예약한 유효한 강의 수
   */
  @Select("select count(reservation_id) from reservation\n"
      + " join course using (course_id)\n"
      + " where reservation_status_id=1 and now()<=end_date and member_id =#{memberId};")
  int countMyReservedCourse(int memberId);

  /**
   * 특정 회원의 예약 목록을 페이지네이션하여 조회합니다.
   *
   * 이 메서드는 특정 회원의 예약 목록을 페이지네이션하여 반환합니다.
   * 예약 정보는 최근 예약부터 정렬됩니다.
   *
   * @param memberId 회원 키값
   * @param offset 페이지네이션의 오프셋
   * @param itemsPerPage 페이지당 항목 수
   * @return 특정 회원의 예약 목록 {@link ReservationListResponse} 객체의 리스트
   */
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

  /**
   * 모든 예약 목록을 페이지네이션하여 조회합니다.
   *
   * 이 메서드는 모든 예약 목록을 페이지네이션하여 반환합니다.
   * 예약 정보는 최근 예약부터 정렬됩니다.
   *
   * @param offset 페이지네이션의 오프셋
   * @param itemsPerPage 페이지당 항목 수
   * @return 모든 예약 목록 {@link ReservationListResponse} 객체의 리스트
   */
  @Select(
      "select course_id as courseId,reservation_status_id as reservationStatusId, merchant_uid as merchantUid,payment_amount as paymentAmount ,payment_history.created_at as createdAt,cancel_at as cancelAt,title,start_date as startDate,end_date as endDate \n"
          + "from reservation \n"
          + "join payment_history using (payment_history_id) \n"
          + "join course using (course_id) \n"
          + "order by reservation_id desc \n"
          + "limit #{offset}, #{itemsPerPage}")
  List<ReservationListResponse> selectReservationListAll(@Param("offset") int offset,
      @Param("itemsPerPage") int itemsPerPage);

  /**
   * 특정 강의의 예약 목록을 페이지네이션하여 조회합니다.
   *
   * 이 메서드는 특정 강의의 예약 목록을 페이지네이션하여 반환합니다.
   * 예약 정보는 최근 예약부터 정렬됩니다.
   *
   * @param courseId 강의 키값
   * @param offset 페이지네이션의 오프셋
   * @param itemsPerPage 페이지당 항목 수
   * @return 특정 강의의 예약 목록 {@link ReservationListResponse} 객체의 리스트
   */
  @Select(
      "select course_id as courseId,reservation_status_id as reservationStatusId, merchant_uid as merchantUid,payment_amount as paymentAmount ,payment_history.created_at as createdAt,cancel_at as cancelAt,title,start_date as startDate,end_date as endDate \n"
          + "from reservation \n"
          + "join payment_history using (payment_history_id) \n"
          + "join course using (course_id) \n"
          + "where course_id=#{courseId} \n"
          + "order by reservation_id desc \n"
          + "limit #{offset}, #{itemsPerPage}")
  List<ReservationListResponse> selectReservationListAllByCourseId(@Param("courseId") int courseId ,@Param("offset") int offset,
      @Param("itemsPerPage") int itemsPerPage);

  /**
   * 전체 환불된 예약 목록을 페이지네이션하여 조회합니다.
   *
   * 이 메서드는 환불된 예약 목록을 페이지네이션하여 반환합니다.
   * 예약 정보는 최근 예약부터 정렬됩니다.
   *
   * @param offset 페이지네이션의 오프셋
   * @param itemsPerPage 페이지당 항목 수
   * @return 환불된 예약 목록 {@link ReservationListResponse} 객체의 리스트
   */
  @Select(
      "select course_id as courseId,reservation_status_id as reservationStatusId, merchant_uid as merchantUid,payment_amount as paymentAmount ,payment_history.created_at as createdAt,cancel_at as cancelAt,title,start_date as startDate,end_date as endDate \n"
          + "from reservation \n"
          + "join payment_history using (payment_history_id) \n"
          + "join course using (course_id) \n"
          + "where reservation_status_id=2 \n"
          + "order by reservation_id desc \n"
          + "limit #{offset}, #{itemsPerPage}")
  List<ReservationListResponse> selectRefundListAll(@Param("offset") int offset,
      @Param("itemsPerPage") int itemsPerPage);

  /**
   * 특정 회원이 예약한 강의의 예약 목록을 페이지네이션하여 조회합니다.
   *
   * 이 메서드는 특정 회원이 예약한 강의의 목록을 페이지네이션하여 반환합니다.
   * 예약 정보는 최근 예약부터 정렬됩니다.
   *
   * @param memberId 회원 키값
   * @param offset 페이지네이션의 오프셋
   * @param itemsPerPage 페이지당 항목 수
   * @return 특정 회원의 예약 강의 목록 {@link MyReservedReservationDTO} 객체의 리스트
   */
  @Select(
      "select merchant_uid as merchantUid,course_id as courseId, title, start_date as startDate, end_date as endDate \n"
          + " from reservation\n"
          + " join course using (course_id)\n"
          + " where reservation_status_id=1 and member_id=#{memberId} and now()<= end_date order by reservation_id desc")
  List<MyReservedReservationDTO> findMyReservedReservationList(@Param("memberId") int memberId,
      @Param("offset") int offset, @Param("itemsPerPage") int itemsPerPage);

  /**
   * 주어진 주문 번호(merchantUid)에 대한 결제 상세 정보를 조회합니다.
   *
   * 이 메서드는 주어진 주문 번호에 대한 결제의 상세 정보를 반환합니다.
   * 반환된 정보에는 강의 제목, 설명, 결제 금액, 생성일, 취소일 등이 포함됩니다.
   *
   * @param merchantUid Hermez에서 생성된 주문 번호
   * @return 주어진 주문 번호에 대한 결제 상세 정보 {@link MyPaymentDetailResponse} 객체
   */
  @Select("select merchant_uid as merchantUid ,title , course_id as courseId,description ,  start_date as startDate, end_date as endDate,payment_amount as paymentAmount ,payment_history.created_at as createdAt,\n"
      + " cancel_at as cancelAt\n"
      + " from reservation\n"
      + " join course using (course_id)\n"
      + " join payment_history using (payment_history_id)\n"
      + " where merchant_uid =#{merchantUid}")
  MyPaymentDetailResponse findMyPaymentDetail(@Param("merchantUid") String merchantUid);
}
