package org.hermez.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerificationRequest {

  private String imp_uid;
  private String amount;
  private String merchant_uid;

}
