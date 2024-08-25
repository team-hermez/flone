package org.hermez.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyReservedReservationDTO {

  private String merchantUid;
  private int courseId;
  private String title;
  private LocalDate startDate;
  private LocalDate endDate;
  private LocalDate currentDate;
  private boolean isBefore;

  public LocalDate getCurrentDate() {
    currentDate = LocalDate.now();
    return currentDate;
  }

  public boolean getIsBefore() {
    return (LocalDate.now()).isBefore(startDate);
  }

  @Override
  public String toString() {
    return "MyReservedReservationDTO{" +
        "merchantUid='" + merchantUid + '\'' +
        ", courseId=" + courseId +
        ", title='" + title + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        '}';
  }
}
