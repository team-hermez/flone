package org.hermez.paymenthistory.service.payapi;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.hermez.paymenthistory.dto.CancelDTO;
import org.hermez.reservation.model.Reservation;
import org.hermez.reservation.model.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 결제 API( 아임포트 ) 사용과 관련된 클래스입니다.
 * 이 클래스는 결제 API( 아임포트 ) 를 사용하여 결제와 취소 기능을 제공합니다.
 *
 * @author 허상범
 */
@Slf4j
@PropertySource("classpath:apikey.properties")
@Component
public class IamportService {

  @Value("${IAM_PORT_APIKEY}")
  private String IAM_PORT_APIKEY;

  @Value("${IAM_PORT_APISECRET}")
  private String IAM_PORT_APISECRET;

  private IamportClient iamportClient;

  private final ReservationRepository reservationRepository;

  @Autowired
  public IamportService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  public IamportClient getIamportClient() {return iamportClient;}

  /**
   * 결제 API( 아임포트 ) 서버에 요청한 응답입니다.
   *
   * @param payResponse 결제 API( 아임포트 ) 서버에 요청한 응답
   * @param amount 클라이언트가 보낸 강의 가격
   * @param merchantUid 클라이언트가 예약한 강의 키값
   * @throws IllegalArgumentException 클라이언트가 요청한 강의 가격이 결제 API( 아임포트 ) 서버와 hermez 서버에 저장된 가격과 다를 경우 발생
   */
  public void verifyPayment(IamportResponse<Payment> payResponse, int amount, String merchantUid) {
    log.info("[결제 검증 시작]");
    int iamportAmount = payResponse.getResponse().getAmount().intValue();
    //결제금액과 아임포트 서버 금액 대조
    if (iamportAmount != amount) {
      log.info("[아임포트 서버(실결제 금액)과 강의 가격이 다릅니다] 아임포트 서버 금액 = {} , 클라이언트가 보낸 강의 가격 = {} ",iamportAmount ,amount);
      throw new IllegalStateException("클라이언트가 보낸 강의 가격이 아임포트 서버 결제 금액과 다릅니다.");
    }
    int payAmount = reservationRepository.findPayAmount(merchantUid);//강의에서 가져와야함

    //db에서 강의 가격과 결제금액 같은지 확인

    if (amount != payAmount) {
      log.info("[결제시 hermez 서버에 저장된 금액과 클라이언트가 보낸 강의 가격이 다릅니다] hermez 서버 금액 = {} , 클라이언트가 보낸 강의 가격 = {}",payAmount,amount);
      throw new IllegalStateException("결제시 hermez 서버에 저장된 금액과 클라이언트가 보낸 강의 가격이 다릅니다.");
    }
  }

  /**
   * 취소/환불 요청시 결제 API( 아임포트 ) 서버 응답입니다.
   *
   * @param cancelDTO 취소/환불 요청시 요청 데이터
   * @param findReservation 고객의 환불 요청한 예약 정보
   * @return 결제 API( 아임포트 ) 서버 응답
   * @throws IamportResponseException 결제 API( 아임포트 ) 서버 응답 요류시 발생
   * @throws IOException
   */
  public IamportResponse<Payment> cancelMethod(CancelDTO cancelDTO, Reservation findReservation) throws IamportResponseException, IOException {
    IamportResponse<Payment> cancelResponse = null;
    if(findReservation == null){
      cancelResponse = iamportClient.paymentByImpUid(cancelDTO.getImp_uid());
    }else {
      cancelResponse = iamportClient.paymentByImpUid(findReservation.getImp_uid());
    }
    return cancelResponse;
  }

  /**
   * 결제시 잘못된 요청으로 인한 환불인지 고객 요청으로 인한 환불인지 구분하여 취소/환불 데이터를 만듭니다.
   *
   * @param cancelDTO 취소 요청 데이터
   * @param findReservation 고객의 환불 요청한 예약 정보
   * @return 취소/환불 데이터
   * @throws IamportResponseException 결제 API( 아임포트 ) 서버 응답 요류시 발생
   * @throws IOException
   */
  public CancelData getCancelData(CancelDTO cancelDTO , Reservation findReservation)
      throws IamportResponseException, IOException {
    IamportResponse<Payment> cancelResponse = cancelMethod(cancelDTO, findReservation);
    CancelData cancelData = null;
    if(cancelDTO.getImp_uid()!=null){
      cancelData = new CancelData(cancelResponse.getResponse().getImpUid(),true);
    }else {
      cancelData = new CancelData(cancelDTO.getMerchant_uid(),false);
    }
    return cancelData;
  }

  @PostConstruct
  public void init() {
    this.iamportClient = new IamportClient(IAM_PORT_APIKEY,IAM_PORT_APISECRET);
  }
}
