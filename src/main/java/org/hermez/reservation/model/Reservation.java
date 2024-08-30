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

  public static final int RESERVED = 1;
  private int reservationId;

  private int memberId;

  private int courseId;

  private int paymentHistoryId;

  private int reservationStatusId;

  private String merchantUid;

  private String imp_uid;

  /**
   * 주어진 정보를 기반으로 예약 객체를 생성합니다.
   *
   * 이 메서드는 회원 ID, 강의 ID, 결제 기록 ID, 주문 번호 등을 사용하여 새로운 {@link Reservation} 객체를 생성합니다.
   * 생성된 예약 객체는 기본적으로 예약 상태가 '예약됨'(RESERVED)으로 설정됩니다.
   *
   * @param memberId         강의 예약자의 고유 식별자
   * @param courseId         예약할 강의의 고유 식별자
   * @param paymentHistoryId 결제 기록의 고유 식별자
   * @param imp_uid          아임포트 API 서버에 저장된 주문 번호
   * @param merchantUid      Hermez에서 생성된 주문 번호
   * @return 새로 생성된 {@link Reservation} 객체
   */
  public static Reservation createReservation(int memberId, int courseId, int paymentHistoryId,
      String imp_uid, String merchantUid) {
    Reservation reservation = new Reservation();
    reservation.setMemberId(memberId);
    reservation.setCourseId(courseId);
    reservation.setPaymentHistoryId(paymentHistoryId);
    reservation.setReservationStatusId(RESERVED);
    reservation.setMerchantUid(merchantUid);
    reservation.setImp_uid(imp_uid);
    return reservation;
  }

  @Override
  public String toString() {
    return "Reservation{" +
        "reservationId=" + reservationId +
        ", memberId=" + memberId +
        ", courseId=" + courseId +
        ", paymentHistoryId=" + paymentHistoryId +
        ", reservationStatusId=" + reservationStatusId +
        ", merchantUid='" + merchantUid + '\'' +
        ", imp_uid='" + imp_uid + '\'' +
        '}';
  }
}
