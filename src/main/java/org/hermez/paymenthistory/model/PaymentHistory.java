package org.hermez.paymenthistory.model;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 결제 기록 정보를 저장하는 클래스입니다.
 *
 * @author  허상범
 */
@NoArgsConstructor(access = PRIVATE)
@Getter @Setter
public class PaymentHistory {

  private Long paymentHistoryId;

  private Double paymentAmount;

  private LocalDateTime createdAt;

  private LocalDateTime cancelAt;

  /**
   *
   * @param paymentAmount 강의 결제 금액
   * @return 강의 결제 기록 정보
   */
  public static PaymentHistory createPaymentHistory(Double paymentAmount) {
    PaymentHistory paymentHistory = new PaymentHistory();
    paymentHistory.setPaymentAmount(paymentAmount);
    paymentHistory.setCreatedAt(LocalDateTime.now());
    return paymentHistory;
  }

}
