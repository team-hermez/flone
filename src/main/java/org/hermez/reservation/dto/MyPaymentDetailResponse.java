package org.hermez.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자가 결제 및 취소한 강의에 대한 정보를 전달하는 DTO 클래스입니다.
 *
 * @author 허상범
 */
@Getter @Setter
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

  /**
   * 주문 생성 날짜를 yyyy-MM-dd HH:mm 형식으로 반환합니다.
   *
   * 이 메서드는 주문 생성 시간을 포맷하여 문자열 형태로 반환합니다.
   * 만약 주문 생성 시간이 {@code null}일 경우 {@code null}을 반환합니다.
   *
   * @return yyyy-MM-dd HH:mm 형식으로 포맷된 주문 생성 날짜. 주문 생성 시간이 {@code null}일 경우 {@code null}.
   */
  public String getCreatedAt() {
    return dateTimeFormatter(createdAt);
  }

  /**
   * 주문 취소 날짜를 yyyy-MM-dd HH:mm 형식으로 반환합니다.
   *
   * 이 메서드는 주문 취소 시간을 포맷하여 문자열 형태로 반환합니다.
   * 만약 주문 취소 시간이 {@code null}일 경우 {@code null}을 반환합니다.
   *
   * @return yyyy-MM-dd HH:mm 형식으로 포맷된 주문 취소 날짜. 주문 취소 시간이 {@code null}일 경우 {@code null}.
   */
  public String getCancelAt() {
    return dateTimeFormatter(cancelAt);
  }

  /**
   * 주어진 {@code LocalDateTime} 객체를 yyyy-MM-dd HH:mm 형식의 문자열로 반환합니다.
   *
   * 이 메서드는 주어진 {@code LocalDateTime} 객체를 포맷하여 문자열로 변환합니다.
   * 만약 입력된 {@code LocalDateTime} 객체가 {@code null}일 경우, {@code null}을 반환합니다.
   *
   * @param localDateTime 포맷할 {@code LocalDateTime} 객체
   * @return yyyy-MM-dd HH:mm 형식으로 포맷된 날짜 문자열. 입력된 {@code LocalDateTime} 객체가 {@code null}일 경우 {@code null}.
   */
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
