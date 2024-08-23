package org.hermez.reservation.model.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hermez.reservation.dto.MyReservationDTO;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.mapper.ReservationMapper;
import org.hermez.reservation.model.Reservation;
import org.hermez.reservation.model.ReservationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

  private final ReservationMapper reservationMapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public void save(Reservation reservation) {
    reservationMapper.save(reservation);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Reservation findById(String merchantUid) {
    return reservationMapper.findById(merchantUid);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Integer findMyCourseOne(int memberId, int courseId) {
    return reservationMapper.findMyCourseOne(memberId, courseId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Integer findPayAmount(String merchantUid) {
    return reservationMapper.findPayAmount(merchantUid);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateReservationStatus(String merchantUid) {
    reservationMapper.updateReservationStatus(merchantUid);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ReservationListResponse> reservationList(int memberId) {
    return reservationMapper.reservationList(memberId);
  }

  @Override
  public List<MyReservationDTO> findMyReservationList(int memberId) {
    return reservationMapper.findMyReservationList(memberId);
  }

  @Override
  public List<MyReservationDTO> findReservationCourseSchedule(int courseId) {
    return reservationMapper.findReservationCourseSchedule(courseId);
  }

}
