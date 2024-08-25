package org.hermez.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPaymentDetailResponse {

  private String merchantUid;
  private String title;
  private int courseId;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private int paymentAmount;
  private LocalDateTime createdAt;
  private LocalDateTime cancelAt;

  public String getCreatedAt() {
    return dateTimeFormatter(createdAt);
  }

  public String getCancelAt() {
    return dateTimeFormatter(cancelAt);
  }

  private String dateTimeFormatter(LocalDateTime localDateTime) {
    if (localDateTime != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      return localDateTime.format(formatter);
    }
    return null;
  }

  @Override
  public String toString() {
    return "MyPaymentDetailResponse{" +
        "merchantUid='" + merchantUid + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", paymentAmount=" + paymentAmount +
        ", createdAt=" + createdAt +
        ", cancelAt=" + cancelAt +
        '}';
  }
}
