package org.hermez.paymenthistory.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hermez.paymenthistory.model.PaymentHistory;

/**
 * 결제 기록의 마이바티스 mapper 클래스입니다.
 */
@Mapper
public interface PaymentHistoryMapper {

  /**
   * 예약시 결제 금액과 결제일자를 저장합니다.
   *
   * @param paymentHistory 결제 기록 정보
   */
  @Insert("insert into payment_history(payment_amount, created_at) values (#{paymentAmount},now())")
  void save(PaymentHistory paymentHistory);

  /**
   * 강의 결제 취소/환불 시 결제 기록에 취소 일자를 추가합니다.
   *
   * @param merchantUid hermez서버에서 발급한 주문번호
   */
  @Update("update payment_history set cancel_at = now() where payment_history_id = (select payment_history_id from reservation where reservation.merchant_uid=#{merchantUid})")
  void updateCancelAt(String merchantUid);

  /**
   * 가장 최근 결제 기록에 대한 키값을 가져옵니다.
   *
   * @return 가장 최근 결제 기록 키값
   */
  @Select("select payment_history_id from payment_history order by payment_history_id desc limit 1")
  int findOne();
}
