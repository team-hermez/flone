package org.hermez.reservation.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 결제 요청시 정보를 담는 DTO 클래스입니다.
 *
 * @author 허상범
 */
@Getter @Setter
public class VerificationRequest {

  private String imp_uid;
  private int amount;
  private String merchant_uid;
  private int courseId;

  @Override
  public String toString() {
    return "VerificationRequest{" +
        "imp_uid='" + imp_uid + '\'' +
        ", amount='" + amount + '\'' +
        ", merchant_uid='" + merchant_uid + '\'' +
        ", courseId=" + courseId +
        '}';
  }
}
