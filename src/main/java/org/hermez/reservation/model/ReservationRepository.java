package org.hermez.reservation.model;

import java.util.List;
import org.hermez.common.page.Page;
import org.hermez.reservation.dto.MyReservationDTO;
import org.hermez.reservation.dto.MyReservedReservationDTO;
import org.hermez.reservation.dto.ReservationListResponse;

/**
 * hermez서버로 예약 정보를 입력하고 가져오기 위한 클래스입니다.
 *
 * @author 허상범
 */
public interface ReservationRepository {

  /**
   * 예약 정보를 저장합니다.
   *
   * @param reservation 예약 정보
   */
  void save(Reservation reservation);

  /**
   * hermez에서 생성한 주문번호로 예약 정보를 가져옵니다.
   *
   * @param merchantUid hermez에서 생성한 주문번호
   * @return reservation 예약 정보
   */
  Reservation findById(String merchantUid);

  /**
   * 특정 회원이 예약한 특정 강의의 예약 정보 키값을 가져옵니다.
   *
   * @param memberId 회원 키값
   * @param courseId 강의 키값
   * @return reservation_id 예약 정보 키값
   */
  Integer findMyCourseOne(int memberId, int courseId);

  /**
   * hermez에서 생성한 주문번호로 예약한 강의의 예약할 때 결제한 금액을 hermez 서버에서 가져옵니다.
   *
   * @param merchantUid hermez에서 생성한 주문번호
   * @return payment_amount hermez서버에 저장된 예약시 결제한 금액
   */
  Integer findPayAmount(String merchantUid);

  void updateReservationStatus(String merchantUid);

  List<MyReservationDTO> findMyReservationList(int memberId);

  List<MyReservationDTO> findReservationCourseSchedule(int courseId);

  Page<ReservationListResponse> getReservationList(int memberId, int page);

  Page<MyReservedReservationDTO> findMyReservedReservationList(int memberId, int page);
}
