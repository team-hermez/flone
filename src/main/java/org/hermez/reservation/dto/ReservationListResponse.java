package org.hermez.reservation.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationListResponse {

  private int courseId;
  private String reservationStatusId;
  private String merchantUid;
  private int paymentAmount;
  private LocalDate createdAt;
  private LocalDate cancelAt;
  private String title;
  private LocalDate startDate;
  private LocalDate endDate;

  @Override
  public String toString() {
    return "ReservationListResponse{" +
        "reservationStatusId='" + reservationStatusId + '\'' +
        ", merchantUid='" + merchantUid + '\'' +
        ", paymentAmount=" + paymentAmount +
        ", createdAt=" + createdAt +
        ", cancelAt=" + cancelAt +
        ", title='" + title + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        '}';
  }
}
