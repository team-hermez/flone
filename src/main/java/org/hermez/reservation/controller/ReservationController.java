package org.hermez.reservation.controller;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hermez.classroom.classroom.dto.ClassroomCardResponse;
import org.hermez.classroom.classroom.service.ClassroomService;
import org.hermez.common.page.Page;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.model.CourseTime;
import org.hermez.course.service.CourseService;
import org.hermez.member.dto.MemberLoginResponse;
import org.hermez.paymenthistory.dto.CancelDTO;
import org.hermez.paymenthistory.service.payapi.IamportService;
import org.hermez.reservation.dto.MyPaymentDetailResponse;
import org.hermez.reservation.dto.MyReservedReservationDTO;
import org.hermez.reservation.dto.ReservationFormResponse;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.dto.VerificationRequest;
import org.hermez.reservation.exception.NoSuchUniqueCourseTimeException;
import org.hermez.reservation.exception.NotActiveClassException;
import org.hermez.reservation.model.Reservation;
import org.hermez.reservation.model.ReservationRepository;
import org.hermez.reservation.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 클라이언트의 요청을 처리하기 위한 예약관련 컨트롤러입니다.
 *
 * 이 컨트롤러는 다음과 같은 HTTP 요청을 처리합니다:
 * <p>- GET /flone/reservation/detail.hm: 특정 강의에 대한 결제 상세 페이지 조회<p/>
 * <p>- GET /flone/reservation/cancel-payment.hm: 결제 실패시 결과 페이지 조회</p>
 * <p>- GET /flone/reservation/success-page.hm: 결제 성공시 결과 페이지 조회</p>
 * <p>- GET /flone/reservation/list.hm: 현재 사용자의 결제 목록 조회</p>
 * <p>- GET /flone/reservation/reservation-detail.hm: 특정 강의에 대한 수강 예약 강의 상세 페이지 조회</p>
 * <p>- GET /flone/reservation/reserved-course-list.hm: 현재 사용자의 강의 목록 조회</p>
 * <p>- GET /flone/reservation/my-payment-detail.hm: 주문한 강의 결제/환불 명세서 조회</p>
 * <p>- POST /flone/reservation/verify-iamport.hm: 결제 요청에 대한 주문 생성</p>
 * <p>- POST /flone/reservation/cancel-payment.hm: 주문 취소/환불 및 잘못된 결제 요청에 대한 결제 취소</p>
 *
 * @author 허상범
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/flone/reservation")
public class ReservationController {


  private final ReservationRepository reservationRepository;
  private final ReservationService reservationService;
  private final IamportService iamportService;
  private final CourseService courseService;
  private final ClassroomService classroomService;

  /**
   * 예약하고 싶은 강의에 대한 예약 상세 페이지를 반환합니다.
   *
   * @param courseId 예약할 강의의 키값
   * @param session 현재 사용자의 세션 객체
   * @param model 뷰에 데이터를 전달하기 위한 모델 객체
   * @return 현재 사용자가 예약할 강의 정보를 표시하는 예약 상세 페이지
   */
  @GetMapping("/detail.hm")
  public String getReservationForm(
      @RequestParam int courseId,
      HttpSession session
      , Model model) {
    MemberLoginResponse memberLoginResponse = (MemberLoginResponse) session.getAttribute("MEMBER");
    CourseDetailResponse courseDetailList = courseService.courseDetailService(courseId);
    List<CourseTime> courseTimeList = courseService.courseDetailTime(courseId);
    model.addAttribute("courseDetailList", courseDetailList);
    model.addAttribute("courseTimeList", courseTimeList);
    ReservationFormResponse reserveForm = ReservationFormResponse.builder()
        .courseId(courseDetailList.getCourseId())
        .title(courseDetailList.getTitle())
        .coursePrice(courseDetailList.getCoursePrice())
        .description(courseDetailList.getDescription())
        .startDate(courseDetailList.getStartDate())
        .endDate(courseDetailList.getEndDate())
        .instructorName(courseDetailList.getInstructorName())
        .merchantUid(createMerchantUid())
        .memberName(memberLoginResponse.getName())
        .memberEmail(memberLoginResponse.getEmail())
        .memberPhone(memberLoginResponse.getPhone()).build();
    model.addAttribute("courseTime", courseTimeList);
    model.addAttribute("reserveForm", reserveForm);
    return "/flone/payment-detail";
  }

  /**
   * 사용자의 결제 실패시 결과 페이지를 반환합니다.
   *
   * @return 사용자의 강의 결제 실패시 결과를 표시하는 페이지
   */
  @GetMapping("/cancel-payment.hm")
  public String getCancelPage() {
    return "/flone/cancel-payment";
  }

  /**
   * 사용자의 결제 성공시 결과 페이지를 반환합니다.
   *
   * @param courseId 예약한 강의의 키값
   * @param model 예약한 강의의 키값을 담는 모델 객체
   * @return 사용자의 강의 결제 성공시 결과를 표시하는 페이지
   */
  @GetMapping("/success-page.hm")
  public String getCompletePage(@RequestParam int courseId, Model model) {
    model.addAttribute("courseId", courseId);
    return "/flone/complete-payment";
  }

  /**
   * 사용자의 강의 결제 목록 페이지를 반환합니다.
   *
   * @param page 현재 페이지
   * @param session 현재 사용자의 세션 객체
   * @param model 현재 사용자의 키값과 예약한 강의에 대한 정보를 담은 객체를 담은 모델 객체
   * @return 현재 사용의 결제 목록 페이지
   */
  @GetMapping("/list.hm")
  public String getReservationListForm(
      @RequestParam(value = "page", defaultValue = "1") int page,
      HttpSession session,
      Model model) {
    MemberLoginResponse memberLoginResponse = (MemberLoginResponse) session.getAttribute("MEMBER");
    int memberId = memberLoginResponse.getMemberId();
    Page<ReservationListResponse> reservationPage = reservationRepository.getReservationList(memberId,page);
    model.addAttribute("memberId", memberId);
    model.addAttribute("reservationPage", reservationPage);
    return "/flone/reservation-list";
  }

  /**
   * 현재 사용자가 예약한 특정 강의의 수강 예약 강의 상세 페이지를 반환합니다.
   *
   * @param courseId 특정강의 키값
   * @param model 클래스룸, 강의 상세정보 그리고 강의 시간을 담은 객체를 담는 모델 객체
   * @return 현재 사용자가 예약한 특정 강의의 수강 예약 강의 상세 페이지
   */
  @GetMapping("/reservation-detail.hm")
  public String getReservationDetailPage(@RequestParam int courseId, Model model) {
    CourseDetailResponse courseDetailList = courseService.courseDetailService(courseId);
    List<CourseTime> courseTimeList = courseService.courseDetailTime(courseId);
    Page<ClassroomCardResponse> classrooms = classroomService.getClassroomList(courseId, 1);
    model.addAttribute("classrooms", classrooms);
    model.addAttribute("courseDetailList", courseDetailList);
    model.addAttribute("courseTimeList", courseTimeList);
    return "flone/reservation-detail";
  }

  /**
   * 현재 사용자의 강의 목록 페이지를 반환합니다.
   *
   * @param page 현재 페이지
   * @param session 현재 사용자의 세션 객체
   * @param model 현재 사용자가 수강하고 있는 강의 목록에 대한 정보를 가진 객체를 담은 모델 객체
   * @return 현재 사용자의 강의 목록 페이지
   */
  @GetMapping("/reserved-course-list.hm")
  public String getReservationList(@RequestParam(value = "page", defaultValue = "1") int page,
      HttpSession session, Model model) {
    MemberLoginResponse memberLoginResponse = (MemberLoginResponse) session.getAttribute("MEMBER");
    int memberId = memberLoginResponse.getMemberId();
    Page<MyReservedReservationDTO> myReservationPage = reservationRepository.findMyReservedReservationList(
        memberId, page);
    model.addAttribute("myReservationPage", myReservationPage);
    return "/flone/my-reservation-list";
  }

  /**
   * 현재 사용자가 결제/예약 및 취소한 강의의 결제 상세 페이지를 반환합니다.
   *
   * @param merchantUid hermez 서버에서 발급한 주문번호
   * @param model 현재 사용자가 결제/예약 및 취소한 강의의 상세정보와 시간을 가진 객체를 담는 모델 객체
   * @return 현재 사용자가 결제/예약 및 취소한 강의의 결제 상세 페이지
   */
  @GetMapping("/my-payment-detail.hm")
  public String getMyPaymentDetailPage(@RequestParam String merchantUid, Model model) {
    MyPaymentDetailResponse myPaymentDetail = reservationRepository.findMyPaymentDetail(merchantUid);
    int courseId = myPaymentDetail.getCourseId();
    CourseDetailResponse courseDetailList = courseService.courseDetailService(courseId);
    List<CourseTime> courseTimeList = courseService.courseDetailTime(courseId);
    model.addAttribute("courseDetailList", courseDetailList);
    model.addAttribute("courseTimeList", courseTimeList);
    model.addAttribute("myPaymentDetail", myPaymentDetail);
    return "/flone/my-payment-detail";
  }

  /**
   * 현재 사용자가 요청한 결제를 검증하고 응답하는 메서드입니다.
   *
   * @param verificationRequest 결제 요청시 필요한 데이터를 담는 DTO
   * @param session 현재 사용자의 세션 객체
   * @return 결제 요청에 대한 응답
   * @throws IamportResponseException 결제 API( 아임포트 ) 서버의 응답이 실패했거나, 서버에서 오류 응답을 반환한 경우 발생
   * @throws IOException 결제 API 서버와의 통신 중 I/O 예외가 발생. 네트워크 장애, 서버 불가용 상태 등으로 인해 아임포트 서버와의 통신이 실패할 때 발생.
   */
  @ResponseBody
  @PostMapping("/verify-iamport.hm")
  public IamportResponse<Payment> postVerifyPayment(
      @RequestBody VerificationRequest verificationRequest,
      HttpSession session
  )
      throws IamportResponseException, IOException {
    int courseId = verificationRequest.getCourseId();
    MemberLoginResponse memberLoginResponse = (MemberLoginResponse) session.getAttribute("MEMBER");
    int memberId = memberLoginResponse.getMemberId();
    Integer myCourseOne = reservationRepository.findMyCourseOne(memberId, courseId);
    CourseDetailResponse courseDetailResponse = courseService.courseDetailService(courseId);
    LocalDate courseStartDate = LocalDate.parse(courseDetailResponse.getStartDate());
    if (LocalDate.now().isAfter(courseStartDate)) {
      throw new NotActiveClassException("수강 시작일이 지난 강의입니다.");
    }
    if (myCourseOne != null) {
      throw new NoSuchUniqueCourseTimeException("이미 수강 중인 강의입니다.");
    }
    reservationService.verifyCourseSchedule(courseId,memberId);
    String impUid = verificationRequest.getImp_uid();//실 결제 금액 확인을 위한 아임포트 쪽에서 주는 아이디
    int amount = verificationRequest.getAmount();// 실제 유저가 결제한 금액
    String merchantUid = verificationRequest.getMerchant_uid(); //내가 만든 주문번호
    IamportResponse<Payment> iamportResponse = iamportService.getIamportClient().paymentByImpUid(impUid);
    reservationService.save(memberId, courseId, amount, impUid, merchantUid);
    iamportService.verifyPayment(iamportResponse, amount, merchantUid);
    return iamportResponse;
  }

  /**
   * 결제 취소/환불 요청시 응답하는 메서드입니다.
   *
   * @param cancelDTO 결제 취소/환불 요청시 필요한 데이터를 담은 DTO
   * @return 결제 취소 요청에 대한 응답
   * @throws IamportResponseException 결제 API( 아임포트 ) 서버의 응답이 실패했거나, 서버에서 오류 응답을 반환한 경우 발생
   * @throws IOException 결제 API 서버와의 통신 중 I/O 예외가 발생. 네트워크 장애, 서버 불가용 상태 등으로 인해 아임포트 서버와의 통신이 실패할 때 발생.
   */
  @ResponseBody
  @PostMapping("/cancel-payment.hm")
  public IamportResponse<Payment> postCancelPayment(@RequestBody CancelDTO cancelDTO)
      throws IamportResponseException, IOException {
    Reservation findReservation = null;
    if (cancelDTO.getImp_uid() == null) {
      findReservation = reservationRepository.findById(cancelDTO.getMerchant_uid());
      int courseId = findReservation.getCourseId();
      CourseDetailResponse courseDetailResponse = courseService.courseDetailService(courseId);
      LocalDate courseStartDate = LocalDate.parse(courseDetailResponse.getStartDate());
      if (LocalDate.now().isAfter(courseStartDate)) {
        throw new NotActiveClassException("강의일이 지난 강의는 환불이 어렵습니다");
      }
      reservationService.cancel(findReservation);
    }
    CancelData cancelData = iamportService.getCancelData(cancelDTO, findReservation);
    return iamportService.getIamportClient().cancelPaymentByImpUid(cancelData);
  }

  /**
   * hermez 서버에서 발급할 주문번호를 생성하는 메서드입니다.
   *
   * @return hermez 서버에서 발급하는 주문번호
   */
  private static String createMerchantUid() {
    String uuid = UUID.randomUUID().toString().replace("-", "");
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDay = now.format(dateTimeFormatter).replace("-", "");
    return formattedDay + "-" + uuid;
  }


}