package org.hermez.paymenthistory.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 예약 취소시 필요한 데이터를 담을 DTO 클래스입니다.
 *
 * @author 허상범
 */
@Getter @Setter
public class CancelDTO {
  private String imp_uid;
  private String reason;
  private String checksum;
  private String refundHolder;
  private String merchant_uid;

  @Override
  public String toString() {
    return "CancelDTO{" +
        "imp_uid='" + imp_uid + '\'' +
        ", reason='" + reason + '\'' +
        ", checksum='" + checksum + '\'' +
        ", refundHolder='" + refundHolder + '\'' +
        ", merchant_uid='" + merchant_uid + '\'' +
        '}';
  }
}
