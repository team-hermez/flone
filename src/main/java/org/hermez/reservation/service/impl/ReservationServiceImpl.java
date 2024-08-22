package org.hermez.reservation.service.impl;

import lombok.RequiredArgsConstructor;
import org.hermez.paymenthistory.model.PaymentHistory;
import org.hermez.paymenthistory.model.PaymentHistoryRepository;
import org.hermez.reservation.model.Reservation;
import org.hermez.reservation.model.ReservationRepository;
import org.hermez.reservation.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 예약시 사용되는 서비스 로직입니다.
 *
 * @author 허상범
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

  private final PaymentHistoryRepository paymentHistoryRepository;
  private final ReservationRepository reservationRepository;

  /**
   * @param memberId    예약자 키값
   * @param courseId    예약 강의 키값
   * @param amount      예약 강의 가격
   * @param imp_uid     결제 API( 아임포트 ) 에서 생성한 주문번호
   * @param merchantUid hermez 서버에서 생성한 주문번호
   */
  @Override
  public void save(int memberId, int courseId, Double amount, String imp_uid, String merchantUid) {
    PaymentHistory paymentHistory = PaymentHistory.createPaymentHistory(amount);
    paymentHistoryRepository.save(paymentHistory);
    int findPaymentHistoryId = paymentHistoryRepository.findOne();
    Reservation createdReservation = Reservation.createReservation(memberId, courseId,
        findPaymentHistoryId, imp_uid, merchantUid);
    reservationRepository.save(createdReservation);
  }

  @Override
  public void cancel(Reservation reservation) {
    String merchantUid = reservation.getMerchantUid();
    paymentHistoryRepository.updateCancelAt(merchantUid);
  }
}
