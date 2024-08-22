package org.hermez.paymenthistory.model;

/**
 * hermez서버로 결제 기록 정보를 입력하고 가져오기 위한 클래스입니다.
 */
public interface PaymentHistoryRepository {

  /**
   * 예약시 결제 금액과 결제일자를 저장합니다.
   *
   * @param paymentHistory 결제 기록 정보
   */
  void save(PaymentHistory paymentHistory);

  /**
   * 강의 결제 취소/환불 시 결제 기록에 취소 일자를 추가합니다.
   *
   * @param merchantUid hermez서버에서 발급한 주문번호
   */
  void updateCancelAt(String merchantUid);

  /**
   * 최근 결제 기록에 대한 키값을 가져옵니다.
   *
   * @return 가장 최근 결제 기록 키값
   */
  int findOne();
}
