var IMP = window.IMP;
IMP.init("imp81023706");
function requestPay(merchantUid,title,coursePrice,memberEmail,memberName,memberPhone,courseId) {
  IMP.request_pay({
    pg: "html5_inicis",
    pay_method: "card",
    merchant_uid: `${merchantUid}`,
    name: `${title}`,
    amount: `${coursePrice}`,
    buyer_email: `${memberEmail}`,
    buyer_name: `${memberName}`,
    buyer_tel: `${memberPhone}`
  }, function (rsp) {
    if (rsp.success) {
      var msg = '결제가 완료되었습니다.';
      alert(msg);
      let data = {
        imp_uid: rsp.imp_uid,
        courseId: `${courseId}`,
        amount: `${coursePrice}`,
        merchant_uid: rsp.merchant_uid
      };
      $.ajax({
        type: 'POST',
        url: '/flone/reservation/verify-iamport.hm',
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: 'json',
        success: (result) => {
          alert('결제검증 완료')
          location.href = `success-page.hm?courseId=${courseId}`;
        },
        error: (result) => {
          alert(result.responseText);
          cancelPayments(rsp,courseId,memberName);
        }
      });
    } else {
      var msg = '결제에 실패하였습니다.';
      msg += '에러내용 : ' + rsp.error_msg;
      alert(msg);
    }
  });
}
function cancelPayments(rsp,merchantUid,courseId,memberName) {
  let data = null;
  if (rsp != null) {
    data = {
      imp_uid: rsp.imp_uid,
      refundHolder: `${memberName}`,
      merchant_uid: rsp.merchant_uid
    };
  }else {
    data={
      merchant_uid: `${merchantUid}`
    }
  }
  $.ajax({
    type: 'POST',
    url: '/flone/reservation/cancel-payment.hm',
    data: JSON.stringify(data),
    contentType: 'application/json;charset=utf-8',
    success: (result) => {
      alert("결제금액 환불완료")
      location.href = `/flone/reservation/cancel-payment.hm?courseId=${courseId}`;
      //결제취소 화면 이동
    },
    error: (result) => {
      location.href = `/flone/reservation/cancel-payment.hm?courseId=${courseId}`;
      alert("환불 실패: "+result.message)
    }
  });
}
function selfCancelPayments(merchantUid) {
  let data={merchant_uid: `${merchantUid}`}
  $.ajax({
    type: 'POST',
    url: '/flone/reservation/cancel-payment.hm',
    data: JSON.stringify(data),
    contentType: 'application/json;charset=utf-8',
    success: (result) => {
      alert("환불 완료")
      location.href = 'list.hm';
      //결제취소 화면 이동
    },
    error: (result) => {
      location.href = 'list.hm';
      alert("환불 실패: "+result.message)
    }
  });
}