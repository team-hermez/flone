package org.hermez.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MyReservationDTO {

  private int courseId;
  private LocalDate startDate;
  private LocalDate endDate;
  private String DayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;

}
