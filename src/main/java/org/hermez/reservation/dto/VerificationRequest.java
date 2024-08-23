package org.hermez.reservation.dto;

import lombok.Getter;
import lombok.Setter;

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
