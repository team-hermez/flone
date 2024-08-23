package org.hermez.reservation.controller;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.model.CourseTime;
import org.hermez.course.service.CourseService;
import org.hermez.member.model.Member;
import org.hermez.paymenthistory.dto.CancelDTO;
import org.hermez.paymenthistory.service.payapi.IamportService;
import org.hermez.reservation.dto.ReservationFormRequest;
import org.hermez.reservation.dto.ReservationFormResponse;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.dto.VerificationRequest;
import org.hermez.reservation.model.Reservation;
import org.hermez.reservation.model.ReservationRepository;
import org.hermez.reservation.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
  public String getReservationForm(@RequestParam int courseId, Model model) {
    CourseDetailResponse courseDetailList = courseService.courseDetailService(courseId);
    List<CourseTime> courseTimeList = courseService.courseDetailTime(courseId);
    Member member = new Member();
    member.setName("홍길동");
    member.setEmail("me@gmail.com");
    member.setPhone("123456789");
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
        .memberEmail(member.getEmail())
        .memberName(member.getName())
        .memberPhone(member.getPhone()).build();

    model.addAttribute("reserveForm", reserveForm);
    return "/flone/payment-detail";
  }

  @GetMapping("/cancel-payment.hm")
  public String getCancelPage() {
    return "/flone/cancel-payment";
  }

  @GetMapping("/success-page.hm")
  public String getCompletePage() {
    return "/flone/complete-payment";
  }

  @GetMapping("/list.hm")
  public String getReservationListForm(Model model) {
    List<ReservationListResponse> reservationList = reservationRepository.reservationList(1);
    model.addAttribute("reservationList", reservationList);
    return "/flone/reservation-list";
  }


  @ResponseBody
  @PostMapping("/{courseId}/verify-iamport")
  public  IamportResponse<Payment> postVerifyPayment(@RequestBody VerificationRequest verificationRequest,@PathVariable int courseId)
      throws IamportResponseException, IOException {
    Integer myCourseOne = reservationRepository.findMyCourseOne(1, courseId);
    if (myCourseOne != null) {
      throw new IllegalStateException("이미 수강 중인 강의입니다.");
    }
    reservationService.verifyCourseSchedule(courseId);
    log.info("reservation validation start");
    String impUid = verificationRequest.getImp_uid();//실 결제 금액 확인을 위한 아임포트 쪽에서 주는 아이디
    int amount = Integer.parseInt(verificationRequest.getAmount());// 실제 유저가 결제한 금액
    String merchantUid = verificationRequest.getMerchant_uid(); //내가 만든 주문번호
    IamportResponse<Payment> iamportResponse = iamportService.getIamportClient().paymentByImpUid(impUid);
    iamportService.verifyPayment(iamportResponse, amount, merchantUid);
    log.info("reservation validation start end");
    reservationService.save(1, courseId, (double) amount, impUid, merchantUid);
    log.info("reservation success");
    return iamportResponse;
  }

  @ResponseBody
  @PostMapping("/cancel-payment.hm")
  public IamportResponse<Payment> postCancelPayment(@RequestBody CancelDTO cancelDTO)
      throws IamportResponseException, IOException {
    log.info("cancel payment start");
    log.info("CancelDTO: {}", cancelDTO);
    Reservation findReservation = null;
    if (cancelDTO.getImp_uid()==null) {
      findReservation = reservationRepository.findById(cancelDTO.getMerchant_uid());
      log.info("find reservation: {}", findReservation.getMerchantUid());
      reservationService.cancel(findReservation);
    }
    CancelData cancelData = iamportService.getCancelData(cancelDTO, findReservation);
    log.info("cancel success");
    return iamportService.getIamportClient().cancelPaymentByImpUid(cancelData);
  }

  private static String createMerchantUid() {
    String uuid = UUID.randomUUID().toString().replace("-", "");
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDay = now.format(dateTimeFormatter).replace("-", "");
    return formattedDay + uuid;
  }


}