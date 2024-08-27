package org.hermez.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 현재 사용자가 수강하는 특정 강의의 시간 정보를 전달하는 DTO 클래스입니다.
 *
 * @author 허상범
 */
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

  /**
   * 현재 날짜를 {@code LocalDate} 객체로 반환합니다.
   *
   * 이 메서드는 시스템의 현재 날짜를 {@code LocalDate} 객체로 반환합니다.
   * 반환된 날짜는 `LocalDate.now()` 메서드를 통해 얻어진 현재 날짜입니다.
   *
   * @return 현재 날짜를 나타내는 {@code LocalDate} 객체.
   *         반환된 {@code LocalDate} 객체는 시스템의 현재 날짜를 기준으로 합니다.
   */
  public LocalDate getCurrentDate() {
    currentDate = LocalDate.now();
    return currentDate;
  }

  /**
   * 현재 날짜가 {@code startDate}보다 이전인지 여부를 확인합니다.
   *
   *  이 메서드는 현재 날짜가 `startDate`보다 이전이면 {@code true}를 반환하고,
   *  그렇지 않으면 {@code false}를 반환합니다.
   *
   * @return 현재 날짜가 {@code startDate}보다 이전인 경우 {@code true}, 그렇지 않으면 {@code false}.
   */
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
