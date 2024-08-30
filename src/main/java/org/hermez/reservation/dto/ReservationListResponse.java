package org.hermez.reservation.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * 현재 사용자가 결제한 강의에 대한 정보를 담은 DTO 클래스입니다.
 *
 * @author 허상범
 */
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
  private boolean isBefore;
  private boolean isAfter;

  /**
   * 현재 날짜가 {@code startDate}보다 이전인지 여부를 반환합니다.
   *
   * 이 메서드는 현재 날짜가 {@code startDate}보다 이전이면 {@code true}를 반환하고,
   * 그렇지 않으면 {@code false}를 반환합니다.
   *
   * @return 현재 날짜가 {@code startDate}보다 이전인 경우 {@code true}, 그렇지 않으면 {@code false}.
   */
  public boolean getIsBefore() {
    return LocalDate.now().isBefore(startDate);
  }

  /**
   * 현재 날짜가 {@code startDate}보다 이후인지 여부를 반환합니다.
   *
   * 이 메서드는 현재 날짜가 {@code startDate}보다 이후이면 {@code true}를 반환하고,
   * 그렇지 않으면 {@code false}를 반환합니다.
   *
   * @return 현재 날짜가 {@code startDate}보다 이후인 경우 {@code true}, 그렇지 않으면 {@code false}.
   */
  public boolean getIsAfter() {
    return (LocalDate.now()).isAfter(startDate);
  }


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
