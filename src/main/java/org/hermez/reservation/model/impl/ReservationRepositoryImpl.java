package org.hermez.reservation.model.impl;

import lombok.RequiredArgsConstructor;
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
  public int findMyCourseOne(int memberId, int courseId) {
    return reservationMapper.findMyCourseOne(memberId, courseId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double findPayAmount(String merchantUid) {
    return reservationMapper.findPayAmount(merchantUid);
  }

}
