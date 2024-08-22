<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx" xmlns:th="http://www.thymeleaf.org">
<head>
    <%@ include file="css.jsp" %>
</head>

<body>
<!--<%@ include file="header.jsp"%>-->
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="index.jsp">Home</a>
                </li>
                <li class="active">Cart Page</li>
            </ul>
        </div>
    </div>
</div>
<div class="cart-main-area pt-90 pb-100">
    <div class="container">
        <h3 class="cart-page-title">Your cart items</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>예약 번호</th>
                                <th>강의 명</th>
                                <th>강의 시작일</th>
                                <th>가격</th>
                                <th>결제상태</th>
                                <th>
                                    <button type="button" id="selectAllBtn">모두선택</button>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="reservation" items="${reservationList}"
                                       varStatus="status">
                                <tr>
                                    <td class="product-thumbnail">
                                        <a href="#">${reservation.merchantUid}</a>
                                    </td>
                                    <td class="product-name"><a href="#">${reservation.title}</a>
                                    </td>
                                    <td class="product-price-cart">${reservation.startDate}</td>
                                    <td class="product-subtotal">${reservation.paymentAmount}</td>
                                    <td class="product-remove">
                                        <c:if test="${reservation.reservationStatusId eq 1}">
                                            <a href="#"><i class="fa fa-pencil">예약완료</i></a>
                                        </c:if>
                                        <c:if test="${reservation.reservationStatusId eq 2}">
                                            <a href="#"><i class="fa fa-pencil">예약취소</i></a>
                                        </c:if>
                                    </td>
                                    <td><input type="checkbox" class="checkList"
                                               th:value="${reservation.merchantUid}"></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="cart-shiping-update-wrapper">
                                <div class="cart-shiping-update">
                                </div>
                                <div class="cart-clear">
                                    <a href="#" id="printBtn">취소</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <script>
                  const allSelectBtn = document.getElementById("selectAllBtn");
                  allSelectBtn.addEventListener('click', () => {
                    checkLists.forEach((checkList) => {
                      checkList.checked = true;
                    })
                  });
                  const checkLists = document.querySelectorAll('.checkList');
                  const printBtn = document.getElementById('printBtn');
                  printBtn.addEventListener('click', () => {
                    checkLists.forEach((checkList) => {
                      if (checkList.checked) {
                        let selectData = {
                          merchant_uid: checkList.value,
                          reason: "관리자 삭제",
                          refundHolder: 2
                        }
                        console.log(checkList.value);
                        // cancelPayments(selectData)
                      }
                    })
                  })

                  function cancelPayments() {
                    let data = {
                      merchant_uid: '[[${reservationDTO.merchantUid}]]'
                    };
                    $.ajax({
                      type: 'POST',
                      url: "cancelPayments",
                      data: JSON.stringify(data),
                      contentType: 'application/json;charset=utf-8',
                      success: (result) => {
                        alert("결제금액 환불완료")
                        location.href = '/cancelpage.hm';
                        //결제취소 화면 이동
                      },
                      error: (result) => {
                        location.href = '/cancelpage.hm';
                        alert("환불 실패: " + result.responseText)
                      }
                    });
                  }
                </script>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<%@ include file="script.jsp" %>
</body>

</html>