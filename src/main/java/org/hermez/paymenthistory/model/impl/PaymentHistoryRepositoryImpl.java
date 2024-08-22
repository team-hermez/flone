package org.hermez.paymenthistory.model.impl;

import lombok.RequiredArgsConstructor;
import org.hermez.paymenthistory.mapper.PaymentHistoryMapper;
import org.hermez.paymenthistory.model.PaymentHistory;
import org.hermez.paymenthistory.model.PaymentHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentHistoryRepositoryImpl implements PaymentHistoryRepository {

  private final PaymentHistoryMapper paymentHistoryMapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public void save(PaymentHistory paymentHistory) {
    paymentHistoryMapper.save(paymentHistory);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateCancelAt(String merchantUid) {
    paymentHistoryMapper.updateCancelAt(merchantUid);
  }
}
