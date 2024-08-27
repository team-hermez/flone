package org.hermez.reservation.model.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hermez.common.page.Page;
import org.hermez.common.page.PaginationUtil;
import org.hermez.reservation.dto.MyPaymentDetailResponse;
import org.hermez.reservation.dto.MyReservationDTO;
import org.hermez.reservation.dto.MyReservedReservationDTO;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.mapper.ReservationMapper;
import org.hermez.reservation.model.Reservation;
import org.hermez.reservation.model.ReservationRepository;
import org.springframework.stereotype.Repository;

@Slf4j
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

  @Override
  public List<MyReservationDTO> findMyReservationList(int memberId) {
    return reservationMapper.findMyReservationList(memberId);
  }

  @Override
  public List<MyReservationDTO> findReservationCourseSchedule(int courseId) {
    return reservationMapper.findReservationCourseSchedule(courseId);
  }

  @Override
  public Page<ReservationListResponse> getReservationList(int memberId, int page) {
    int total = reservationMapper.countReservations(memberId);
    PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total,5,page);
     List<ReservationListResponse> reservations = reservationMapper.selectReservationList(memberId,pageInfo.getOffset(),pageInfo.getItemsPerPage());
    return new Page<>(reservations, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
  }

  @Override
  public Page<ReservationListResponse> getReservationListAll(int page) {
    int total = reservationMapper.countReservationsAll();
    PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total,5,page);
    List<ReservationListResponse> reservationAll = reservationMapper.selectReservationListAll(pageInfo.getOffset(),pageInfo.getItemsPerPage());
    return new Page<>(reservationAll, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
  }

  @Override
  public Page<ReservationListResponse> selectReservationListAllByCourseId(int courseId, int page) {
    int total = reservationMapper.countReservationsAllByCourseId(courseId);
    PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total,10,page);
    List<ReservationListResponse> reservationAll = reservationMapper.selectReservationListAll(pageInfo.getOffset(),pageInfo.getItemsPerPage());
    return new Page<>(reservationAll, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
  }

  @Override
  public Page<ReservationListResponse> getRefundListAll(int page) {
    int total = reservationMapper.countRefundAll();
    PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total,5,page);
    List<ReservationListResponse> refundListAll = reservationMapper.selectRefundListAll(pageInfo.getOffset(),pageInfo.getItemsPerPage());
    return new Page<>(refundListAll, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
  }


  @Override
  public Page<MyReservedReservationDTO> findMyReservedReservationList(int memberId, int page) {
    int total = reservationMapper.countMyReservedCourse(memberId);
    PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total,5,page);
    List<MyReservedReservationDTO> myReservations = reservationMapper.findMyReservedReservationList(
        memberId, pageInfo.getOffset(), pageInfo.getItemsPerPage());
    return new Page<>(myReservations, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
  }

  @Override
  public MyPaymentDetailResponse findMyPaymentDetail(String merchantUid) {
   return reservationMapper.findMyPaymentDetail(merchantUid);
  }


}
