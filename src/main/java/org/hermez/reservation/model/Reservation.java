package org.hermez.reservation.model;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 강의 예약 정보를 저장하는 클래스입니다.
 *
 * @author 허상범
 */
@Getter
@Setter
@NoArgsConstructor(access = PRIVATE)
public class Reservation {

  private int reservationId;

  private int memberId;

  private int courseId;

  private int paymentHistoryId;

  private int reservationStatusId;

  private String merchantUid;

  private String imp_uid;

  /**
   *
   * @param memberId 강의 예약자의 키값
   * @param courseId 예약할 강의의 키값
   * @param paymentHistoryId 결제 기록 키값
   * @param imp_uid 아엠포트API 서버에 저장되는 주문번호
   * @param merchantUid hermez에서 만든 주문번호
   * @return 예약정보
   */
  public static Reservation createReservation(int memberId, int courseId, int paymentHistoryId, String imp_uid, String merchantUid) {
    Reservation reservation = new Reservation();
    reservation.setMemberId(memberId);
    reservation.setCourseId(courseId);
    reservation.setPaymentHistoryId(paymentHistoryId);
    reservation.setReservationStatusId(1);
    reservation.setMerchantUid(merchantUid);
    reservation.setImp_uid(imp_uid);
    return reservation;
  }

}
