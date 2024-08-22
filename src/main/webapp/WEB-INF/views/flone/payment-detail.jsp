<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp"%>
    <style>
        #reservation-course-img{
          width: 100%;
          height: 100%;
        }
    </style>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
   <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    <script>
      var IMP = window.IMP;
      IMP.init("imp81023706");
      function requestPay() {
        IMP.request_pay({
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: "${reserveForm.merchantUid}",
          name: '자바의 정석',
          amount: ${reserveForm.coursePrice},
          buyer_email: "${reserveForm.memberEmail}",
          buyer_name: "${reserveForm.memberName}",
          buyer_tel: "${reserveForm.memberPhone}"
        }, function (rsp) {
          if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            alert(msg);
            let data = {
              imp_uid: rsp.imp_uid,
              amount: rsp.amount,
              merchant_uid: rsp.merchant_uid
            };
            $.ajax({
              type:'POST',
              url: '/flone/reservation/'+'${reserveFome.courseId}'+'verify-iamport.hm',
              data:JSON.stringify(data),
              contentType:"application/json;charset=utf-8",
              dataType:'json',
              success:(result)=>{
                alert('결제검증 완료')
                location.href='/flone/reservation/success-page.hm';
              },
              error:(result)=>{
                alert(result.responseText);
                cancelPayments(rsp);
              }
            });
          } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            alert(msg);
          }
        });
      }
      function cancelPayments(temp){
        let data = null;
        if(temp!=null){//결제금액이 달라졌을때 결제취소
          data = {
            imp_uid: temp.imp_uid,
            reason:"결제 금액 위/변조, 결제 금액이 다름",
            refundHolder:temp.buyer_name,
            merchant_uid:temp.merchant_uid
          };
        }else{//유저가 환불을 요청했을때 데이터

        }
        $.ajax({
          type: 'POST',
          url: "/flone/reservation/cancel-payment.hm",
          data:JSON.stringify(data),
          contentType: 'application/json;charset=utf-8',
          success:(result)=>{
            alert("결제금액 환불완료")
            location.href='/flone/reservation/cancel-payment.hm';
            //결제취소 화면 이동
          },
          error:(result)=>{
            location.href='/flone/reservation/cancel-payment.hm';
            alert("환불 실패: " + result.responseText)
          }
        });
      }
    </script>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="index.jsp">Home</a>
                </li>
                <li class="active">Checkout </li>
            </ul>
        </div>
    </div>
</div>
<div class="checkout-area pt-95 pb-100">
    <div class="container">
        <div class="row">
            <div class="col-lg-7">
                <div class="billing-info-wrap">
                    <h3>Billing Details</h3>
                    <div class="row">
                        <div class="your-order-wrap gray-bg-4">
                        <div id="img-div">
                            <img id="reservation-course-img" src="https://www.sputnik.kr/article_img/202203/article_1648201686.jpg" alt="">
                        </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-5">
                <div class="your-order-area">
                    <h3>Your order</h3>
                    <div class="your-order-wrap gray-bg-4">
                        <div class="your-order-product-info">
                            <div class="your-order-top">
                                <ul>
                                    <li>강의명</li>
                                    <li>가격</li>
                                </ul>
                            </div>
                            <div class="your-order-middle">
                                <ul>
                                    <li><span class="order-middle-left"></span> <span class="order-price"></span></li>
                                    <li><span class="order-middle-left">${reserveForm.title}</span> <span class="order-price">${reserveForm.coursePrice} 원 </span></li>
                                </ul>
                            </div>
                            <div class="your-order-bottom">
                                <ul>
                                    <li class="your-order-shipping">강의일</li>
                                    <li>${reserveForm.startDate} ~ ${reserveForm.endDate}</li>
                                </ul>
                            </div>
                            <div class="your-order-bottom">
                                <ul>
                                    <li class="your-order-shipping">강의시간</li>
                                    <li>강의 시간</li>
                                </ul>
                            </div>
                        </div>
                        <div class="payment-method">
                            <div class="payment-accordion element-mrg">
                                <div class="panel-group" id="accordion">
                                    <div class="panel payment-accordion">
                                        <div class="panel-heading" id="method-one">
                                            <h4 class="panel-title">
                                                <a data-bs-toggle="collapse" href="#method1">
                                                    강의내용
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="method1" class="panel-collapse collapse show" data-bs-parent="#accordion">
                                            <div class="panel-body">
                                                <p>${reserveForm.description}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel payment-accordion">
                                        <div class="panel-heading" id="method-two">
                                            <h4 class="panel-title">
                                                <a class="collapsed" data-bs-toggle="collapse" href="#method2">
                                                    Check payments
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="method2" class="panel-collapse collapse" data-bs-parent="#accordion">
                                            <div class="panel-body">
                                                <p>Please send a check to Store Name, Store Street, Store Town, Store State / County, Store Postcode.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel payment-accordion">
                                        <div class="panel-heading" id="method-three">
                                            <h4 class="panel-title">
                                                <a class="collapsed" data-bs-toggle="collapse" href="#method3">
                                                    Cash on delivery
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="method3" class="panel-collapse collapse" data-bs-parent="#accordion">
                                            <div class="panel-body">
                                                <p><div></div></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="Place-order mt-25">
                        <a class="btn-hover" href="#" onclick="requestPay()">결제</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
<%@ include file="script.jsp"%>
</body>

</html>