package org.hermez.reservation.model;

import java.util.List;
import org.hermez.common.page.Page;
import org.hermez.reservation.dto.MyPaymentDetailResponse;
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
   * 예약 정보를 데이터베이스에 저장합니다.
   *
   * @param reservation 저장할 예약 정보 객체
   */
  void save(Reservation reservation);

  /**
   * Hermez에서 생성한 주문번호를 사용하여 예약 정보를 조회합니다.
   *
   * @param merchantUid Hermez에서 생성한 주문번호
   * @return 예약 정보 객체. 해당 주문번호에 대한 예약 정보를 포함
   */
  Reservation findById(String merchantUid);

  /**
   * 특정 회원이 예약한 특정 강의의 예약 정보 키값을 조회합니다.
   *
   * @param memberId 회원의 고유 식별자
   * @param courseId 강의의 고유 식별자
   * @return 예약 정보의 고유 식별자. 해당 회원이 예약한 특정 강의의 예약 정보 키값
   */
  Integer findMyCourseOne(int memberId, int courseId);

  /**
   * Hermez에서 생성한 주문번호를 사용하여 예약할 때 결제한 강의에 대한 금액을 조회합니다.
   *
   * @param merchantUid Hermez에서 생성한 주문번호
   * @return 예약 시 결제한 강의에 대한 금액 정보
   */
  Integer findPayAmount(String merchantUid);

  /**
   * 예약 상태를 업데이트합니다.
   *
   * @param merchantUid Hermez에서 생성한 주문번호. 해당 주문번호에 대한 예약 상태를 변경
   */
  void updateReservationStatus(String merchantUid);

  /**
   * 특정 회원이 예약한 강의 목록을 조회합니다.
   *
   * @param memberId 회원의 고유 식별자
   * @return 회원이 예약한 강의의 예약 정보 목록을 포함하는 DTO 리스트
   */
  List<MyReservationDTO> findMyReservationList(int memberId);

  /**
   * 특정 강의의 예약 일정 정보를 조회합니다.
   *
   * @param courseId 강의의 고유 식별자
   * @return 강의의 예약 일정 정보를 포함하는 DTO 리스트
   */
  List<MyReservationDTO> findReservationCourseSchedule(int courseId);

  /**
   * 특정 회원의 예약 목록을 페이지 단위로 조회합니다.
   *
   * @param memberId 회원의 고유 식별자
   * @param page 페이지 번호
   * @return 예약 목록을 포함하는 페이지 객체
   */
  Page<ReservationListResponse> getReservationList(int memberId, int page);

  /**
   * 모든 예약 목록을 페이지 단위로 조회합니다.
   *
   * @param page 페이지 번호
   * @return 모든 예약 목록을 포함하는 페이지 객체
   */
  Page<ReservationListResponse> getReservationListAll(int page);

  /**
   * 특정 강의의 예약 목록을 페이지 단위로 조회합니다.
   *
   * @param courseId 강의의 고유 식별자
   * @param page 페이지 번호
   * @return 강의의 예약 목록을 포함하는 페이지 객체
   */
  Page<ReservationListResponse> selectReservationListAllByCourseId(int courseId,int page);

  /**
   * 모든 환불 목록을 페이지 단위로 조회합니다.
   *
   * @param page 페이지 번호
   * @return 환불 목록을 포함하는 페이지 객체
   */
  Page<ReservationListResponse> getRefundListAll(int page);

  /**
   * 특정 회원의 예약된 예약 목록을 페이지 단위로 조회합니다.
   *
   * @param memberId 회원의 고유 식별자
   * @param page 페이지 번호
   * @return 회원이 예약한 예약 목록을 포함하는 페이지 객체
   */
  Page<MyReservedReservationDTO> findMyReservedReservationList(int memberId, int page);

  /**
   * Hermez에서 생성한 주문번호를 사용하여 결제 세부 정보를 조회합니다.
   *
   * @param merchantUid Hermez에서 생성한 주문번호
   * @return 결제 세부 정보를 포함하는 응답 객체
   */
  MyPaymentDetailResponse findMyPaymentDetail(String merchantUid);


}
