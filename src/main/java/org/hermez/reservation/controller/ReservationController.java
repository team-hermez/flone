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
import org.hermez.common.page.Page;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.model.CourseTime;
import org.hermez.course.service.CourseService;
import org.hermez.member.model.Member;
import org.hermez.paymenthistory.dto.CancelDTO;
import org.hermez.paymenthistory.service.payapi.IamportService;
import org.hermez.reservation.dto.MyPaymentDetailResponse;
import org.hermez.reservation.dto.MyReservationDTO;
import org.hermez.reservation.dto.MyReservedReservationDTO;
import org.hermez.reservation.dto.ReservationFormResponse;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.dto.VerificationRequest;
import org.hermez.reservation.exception.NoSuchUniqueCourseTimeException;
import org.hermez.reservation.exception.NotActiveClassException;
import org.hermez.reservation.model.Reservation;
import org.hermez.reservation.model.ReservationRepository;
import org.hermez.reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.HttpException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/flone/reservation")
public class ReservationController {


  private final ReservationRepository reservationRepository;
  private final ReservationService reservationService;
  private final IamportService iamportService;
  private final CourseService courseService;


  @GetMapping("/detail.hm")
  public String getReservationForm(
      @RequestParam int courseId,
      HttpSession session
      , Model model) {
    Member member = (Member) session.getAttribute("MEMBER");
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
        .memberName(member.getName())
        .memberEmail(member.getEmail())
        .memberPhone(member.getPhone()).build();
    model.addAttribute("courseTime", courseTimeList);
    model.addAttribute("reserveForm", reserveForm);
    return "/flone/payment-detail";
  }

  @GetMapping("/cancel-payment.hm")
  public String getCancelPage() {
    return "/flone/cancel-payment";
  }

  @GetMapping("/success-page.hm")
  public String getCompletePage(@RequestParam int courseId, Model model) {
    model.addAttribute("courseId", courseId);
    return "/flone/complete-payment";
  }

  @GetMapping("/list.hm")
  public String getReservationListForm(
      @RequestParam(value = "page", defaultValue = "1") int page,
      HttpSession session,
      Model model) {
    Member member = (Member) session.getAttribute("MEMBER");
    int memberId = member.getMemberId();
    Page<ReservationListResponse> reservationPage = reservationRepository.getReservationList(
        memberId,
        page);
    log.info("reservationPage = {}", reservationPage.getContents());
    for (ReservationListResponse content : reservationPage.getContents()) {
      if(content.getIsBefore()){
        System.out.println("content.getTitle() = " + content.getTitle());
      }
    }
    model.addAttribute("memberId", memberId);
    model.addAttribute("reservationPage", reservationPage);
    return "/flone/reservation-list";
  }

  @GetMapping("/reservation-detail.hm")
  public String getReservationDetailPage(@RequestParam int courseId, Model model) {
    CourseDetailResponse courseDetailList = courseService.courseDetailService(courseId);
    List<CourseTime> courseTimeList = courseService.courseDetailTime(courseId);
    model.addAttribute("courseDetailList", courseDetailList);
    model.addAttribute("courseTimeList", courseTimeList);
    return "flone/reservation-detail";
  }

  @GetMapping("/reserved-course-list.hm")
  public String getReservationList(@RequestParam(value = "page", defaultValue = "1") int page,
      HttpSession session, Model model) {
    Member member = (Member) session.getAttribute("MEMBER");
    int memberId = member.getMemberId();
    Page<MyReservedReservationDTO> myReservationPage = reservationRepository.findMyReservedReservationList(
        memberId, page);
    model.addAttribute("myReservationPage", myReservationPage);
    return "/flone/my-reservation-list";
  }

  @GetMapping("/my-payment-detail.hm")
  public String getMyPaymentDetailPage(
      @RequestParam String merchantUid,
      HttpSession session,
      Model model) {
    Member member = (Member)session.getAttribute("MEMBER");
    int memberId = member.getMemberId();
    MyPaymentDetailResponse myPaymentDetail = reservationRepository.findMyPaymentDetail(merchantUid, memberId);
    int courseId = myPaymentDetail.getCourseId();
    CourseDetailResponse courseDetailList = courseService.courseDetailService(courseId);
    List<CourseTime> courseTimeList = courseService.courseDetailTime(courseId);
    model.addAttribute("courseDetailList", courseDetailList);
    model.addAttribute("courseTimeList", courseTimeList);
    model.addAttribute("myPaymentDetail", myPaymentDetail);
    return "/flone/my-payment-detail";
  }

  @ResponseBody
  @PostMapping("/verify-iamport.hm")
  public IamportResponse<Payment> postVerifyPayment(
      @RequestBody VerificationRequest verificationRequest,
      HttpSession session
  )
      throws IamportResponseException, IOException {
    int courseId = verificationRequest.getCourseId();
    Member member = (Member) session.getAttribute("MEMBER");
    Integer myCourseOne = reservationRepository.findMyCourseOne(member.getMemberId(), courseId);
    CourseDetailResponse courseDetailResponse = courseService.courseDetailService(courseId);
    LocalDate courseStartDate = LocalDate.parse(courseDetailResponse.getStartDate());
    if (LocalDate.now().isAfter(courseStartDate)) {
      throw new NotActiveClassException("수강 시작일이 지난 강의입니다.");
    }
    if (myCourseOne != null) {
      throw new NoSuchUniqueCourseTimeException("이미 수강 중인 강의입니다.");
    }
    reservationService.verifyCourseSchedule(courseId);
    String impUid = verificationRequest.getImp_uid();//실 결제 금액 확인을 위한 아임포트 쪽에서 주는 아이디
    int amount = verificationRequest.getAmount();// 실제 유저가 결제한 금액
    String merchantUid = verificationRequest.getMerchant_uid(); //내가 만든 주문번호
    IamportResponse<Payment> iamportResponse = iamportService.getIamportClient()
        .paymentByImpUid(impUid);
    reservationService.save(member.getMemberId(), courseId, amount, impUid, merchantUid);
    iamportService.verifyPayment(iamportResponse, amount, merchantUid);
    return iamportResponse;
  }

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

  private static String createMerchantUid() {
    String uuid = UUID.randomUUID().toString().replace("-", "");
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDay = now.format(dateTimeFormatter).replace("-", "");
    return formattedDay + "-" + uuid;
  }


}