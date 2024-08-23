package org.hermez.reservation.service;

import org.hermez.reservation.model.Reservation;

/**
 * 예약시 사용되는 서비스 로직입니다.
 *
 * @author 허상범
 */
public interface ReservationService {

  /**
   * @param memberId    예약자 키값
   * @param courseId    예약 강의 키값
   * @param amount      예약 강의 가격
   * @param imp_uid     결제 API( 아임포트 ) 에서 생성한 주문번호
   * @param merchantUid hermez 서버에서 생성한 주문번호
   */
  void save(int memberId, int courseId, Double amount, String imp_uid, String merchantUid);

  /**
   * 결제 취소/환불시 사용되는 메서드입니다.
   *
   * @param reservation 환불 요청 예약 정보
   */
  void cancel(Reservation reservation);

  void verifyCourseSchedule(int courseId);
}
