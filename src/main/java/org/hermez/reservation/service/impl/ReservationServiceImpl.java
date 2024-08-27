package org.hermez.reservation.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hermez.common.page.Page;
import org.hermez.paymenthistory.model.PaymentHistory;
import org.hermez.paymenthistory.model.PaymentHistoryRepository;
import org.hermez.reservation.dto.MyReservationDTO;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.exception.NoSuchUniqueCourseTimeException;
import org.hermez.reservation.model.Reservation;
import org.hermez.reservation.model.ReservationRepository;
import org.hermez.reservation.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 예약시 사용되는 서비스 로직입니다.
 *
 * @author 허상범
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

  private final PaymentHistoryRepository paymentHistoryRepository;
  private final ReservationRepository reservationRepository;

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void save(int memberId, int courseId, int amount, String imp_uid, String merchantUid) {
    PaymentHistory paymentHistory = PaymentHistory.createPaymentHistory(amount);
    paymentHistoryRepository.save(paymentHistory);
    int findPaymentHistoryId = paymentHistoryRepository.findOne();
    Reservation createdReservation = Reservation.createReservation(memberId, courseId,
        findPaymentHistoryId, imp_uid, merchantUid);
    reservationRepository.save(createdReservation);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void cancel(Reservation reservation) {
    String merchantUid = reservation.getMerchantUid();
    reservationRepository.updateReservationStatus(merchantUid);
    paymentHistoryRepository.updateCancelAt(merchantUid);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void verifyCourseSchedule(int courseId, int memberId) {
    List<MyReservationDTO> myReservationList = reservationRepository.findMyReservationList(memberId);
    List<MyReservationDTO> reservationCourseScheduleList = reservationRepository.findReservationCourseSchedule(
        courseId);
    Multimap<List<LocalDateTime[]>, List<LocalDateTime[]>> multimap = ArrayListMultimap.create();
    for (MyReservationDTO reservationCourseSchedule : reservationCourseScheduleList) {
      for (MyReservationDTO myReservation : myReservationList) {
        List<LocalDateTime[]> courseSchedule = getCourseSchedule(reservationCourseSchedule);
        List<LocalDateTime[]> myCourseSchedule = getCourseSchedule(myReservation);
        multimap.put(courseSchedule, myCourseSchedule);
      }
    }
    for (Map.Entry<List<LocalDateTime[]>, List<LocalDateTime[]>> entry : multimap.entries()) {
      List<LocalDateTime[]> courseSchedule = entry.getKey();
      List<LocalDateTime[]> myCourseSchedule = entry.getValue();
      try {
        checkScheduleOverlap(courseSchedule, myCourseSchedule);
      } catch (IllegalStateException e) {

        throw new NoSuchUniqueCourseTimeException(e);
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Page<ReservationListResponse> getReservationListAll(int page) {
    return reservationRepository.getReservationListAll(page);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Page<ReservationListResponse> getReservationListAllByCourseId(int courseId, int page) {
    return reservationRepository.selectReservationListAllByCourseId(courseId, page);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Page<ReservationListResponse> getRefundListAll(int page) {
    return reservationRepository.getRefundListAll(page);
  }

  /**
   * 예약 DTO를 기반으로 강의 일정을 생성합니다.
   *
   * @param myReservationDTO 예약 DTO 객체
   * @return 강의 일정을 포함하는 LocalDateTime 배열의 리스트
   */
  private List<LocalDateTime[]> getCourseSchedule(MyReservationDTO myReservationDTO) {
    LocalDate startDate = myReservationDTO.getStartDate();
    LocalDate endDate = myReservationDTO.getEndDate();
    Map<DayOfWeek, LocalTime[]> weeklySchedule = new HashMap<>();
    weeklySchedule.put(DayOfWeek.valueOf(myReservationDTO.getDayOfWeek()),
        new LocalTime[]{myReservationDTO.getStartTime(), myReservationDTO.getEndTime()});
    return createCourseSchedule(startDate, endDate, weeklySchedule);
  }

  /**
   * 주어진 시작일과 종료일, 주간 일정에 따라 강의 일정을 생성합니다.
   *
   * @param startDate 시작 날짜
   * @param endDate 종료 날짜
   * @param weeklySchedule 주간 일정
   * @return 생성된 강의 일정의 LocalDateTime 배열 리스트
   */
  private List<LocalDateTime[]> createCourseSchedule(LocalDate startDate, LocalDate endDate,
      Map<DayOfWeek, LocalTime[]> weeklySchedule) {
    List<LocalDateTime[]> schedules = new ArrayList<>();
    LocalDate current = startDate;
    while (!current.isAfter(endDate)) {
      DayOfWeek currentDay = current.getDayOfWeek();
      if (weeklySchedule.containsKey(currentDay)) {
        LocalTime[] times = weeklySchedule.get(currentDay);
        LocalDateTime startDateTime = LocalDateTime.of(current, times[0]);
        LocalDateTime endDateTime = LocalDateTime.of(current, times[1]);
        schedules.add(new LocalDateTime[]{startDateTime, endDateTime});
      }
      current = current.plusDays(1);
    }
    return schedules;
  }

  /**
   * 두 개의 강의 일정이 겹치는지 확인합니다.
   *
   * @param schedule1 첫 번째 강의 일정
   * @param schedule2 두 번째 강의 일정
   * @throws IllegalStateException 일정이 겹칠 경우 발생
   */
  private void checkScheduleOverlap(List<LocalDateTime[]> schedule1,
      List<LocalDateTime[]> schedule2) {
    for (LocalDateTime[] timeSlot1 : schedule1) {
      for (LocalDateTime[] timeSlot2 : schedule2) {
        if (isOverlapping(timeSlot1, timeSlot2)) {
          throw new IllegalStateException("고객님의 예약이 학습중인 강의와 스케쥴이 겹칩니다: " +
              "\n 예약 하실 강의 스케쥴: " + timeSlot1[0] + " ~ " + timeSlot1[1]
              + "\n 기존 강의 스케쥴: " + timeSlot2[0] + " ~ " + timeSlot2[1]);
        }
      }
    }
  }

  /**
   * 두 개의 시간 슬롯이 겹치는지 확인합니다.
   *
   * @param timeSlot1 첫 번째 시간 슬롯
   * @param timeSlot2 두 번째 시간 슬롯
   * @return 시간 슬롯이 겹치면 true, 그렇지 않으면 false.
   */
  private boolean isOverlapping(LocalDateTime[] timeSlot1, LocalDateTime[] timeSlot2) {
    LocalDateTime start1 = timeSlot1[0];
    LocalDateTime end1 = timeSlot1[1];
    LocalDateTime start2 = timeSlot2[0];
    LocalDateTime end2 = timeSlot2[1];
    return start1.isBefore(end2) && end1.isAfter(start2);
  }
}
