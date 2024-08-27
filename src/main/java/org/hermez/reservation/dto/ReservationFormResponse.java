package org.hermez.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 현재 사용자가 예약할 강의 정보를 담을 DTO 클래스입니다.
 *
 * @author 허상범
 */
@Getter @Setter
@Builder
public class ReservationFormResponse {

  private int courseId;           // 강좌 ID
  private int instructorId;
  private String title;            // 과목명
  private int coursePrice;      // 강좌 가격
  private String description;      // 강좌 내용
  private String startDate;        // 강좌 시작일
  private String endDate;          // 강좌 종료일
  private String instructorName;
  private String merchantUid;
  private String memberEmail;
  private String memberName;
  private String memberPhone;

}
