package org.hermez.reservation.service;

import org.hermez.common.page.Page;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.exception.NoSuchUniqueCourseTimeException;
import org.hermez.reservation.model.Reservation;

/**
 * 예약시 사용되는 서비스 로직입니다.
 *
 * @author 허상범
 */
public interface ReservationService {
  /**
   * 예약 정보를 저장합니다.
   *
   * @param memberId    예약자의 고유 식별자
   * @param courseId    예약할 강의의 고유 식별자
   * @param amount      예약 강의의 가격
   * @param imp_uid     결제 API(아임포트)에서 생성한 주문번호
   * @param merchantUid Hermez 서버에서 생성한 주문번호
   */
  void save(int memberId, int courseId, int amount, String imp_uid, String merchantUid);


  /**
   * 주어진 예약을 취소하고 관련된 결제 기록의 취소 시간을 업데이트합니다
   *
   * @param reservation 취소할 예약 정보 객체
   */
  void cancel(Reservation reservation);


  /**
   * 주어진 강의의 예약 일정과 회원의 기존 예약 일정을 확인하여 일정이 겹치는지 검증합니다.
   *
   * @param courseId 강의의 고유 식별자
   * @param memberId 회원의 고유 식별자
   * @throws NoSuchUniqueCourseTimeException 예약 일정이 기존 예약 일정과 겹치는 경우 발생
   */
  void verifyCourseSchedule(int courseId, int memberId);

  /**
   * 모든 예약 목록을 페이지 단위로 조회합니다.
   *
   * @param page 페이지 번호
   * @return 예약 목록을 포함하는 페이지 객체
   */
  Page<ReservationListResponse> getReservationListAll(int page);

  /**
   * 특정 강의의 모든 예약 목록을 페이지 단위로 조회합니다.
   *
   * @param courseId 강의의 고유 식별자
   * @param page 페이지 번호
   * @return 강의의 예약 목록을 포함하는 페이지 객체
   */
  Page<ReservationListResponse> getReservationListAllByCourseId(int courseId,int page);


  /**
   * 모든 환불 목록을 페이지 단위로 조회합니다.
   *
   * @param page 페이지 번호
   * @return 환불 목록을 포함하는 페이지 객체
   */
  Page<ReservationListResponse> getRefundListAll(int page);





}
