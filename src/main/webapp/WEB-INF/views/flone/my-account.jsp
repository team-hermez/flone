<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp" %>
    <style>
        .single-my-account .myaccount-info-wrapper .account-info-wrapper {
            padding-bottom: 0 !important;
        }
    </style>
</head>

<body>
<%@ include file="header.jsp" %>
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="index.jsp">Home</a>
                </li>
                <li class="active">마이페이지</li>
            </ul>
        </div>
    </div>
</div>
<div class="checkout-area pb-80 pt-100">
    <div class="container">
        <div class="row">
            <div class="ms-auto me-auto col-lg-9">
                <div class="checkout-wrapper">
                    <div id="faq" class="panel-group">
                        <div class="panel panel-default single-my-account">
                            <div class="panel-heading my-account-title">
                                <h3 class="panel-title"><span>1 .</span> <a data-bs-toggle="collapse"
                                                                            href="#my-account-1">나의 프로필</a></h3>
                            </div>
                            <div id="my-account-1" class="panel-collapse collapse show" data-bs-parent="#faq">
                                <div class="panel-body">
                                    <div class="myaccount-info-wrapper">
                                        <div class="account-info-wrapper">
                                            <h3>내 정보</h3>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-9 col-md-9">
                                                <div class="billing-info">
                                                    <h4>이름</h4>
                                                    <p>${myAccount.name}</p>
                                                </div>
                                                <div class="billing-info">
                                                    <h4>이메일 주소</h4>
                                                    <p>${myAccount.email}</p>
                                                </div>
                                                <div class="billing-info">
                                                    <h4>전화</h4>
                                                    <p>${myAccount.phone}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default single-my-account">
                            <div class="panel-heading my-account-title">
                                <h3 class="panel-title"><span>2 .</span> <a data-bs-toggle="collapse"
                                                                            href="#my-account-2">내 정보 변경</a></h3>
                            </div>
                            <div id="my-account-2" class="panel-collapse collapse" data-bs-parent="#faq">
                                <form action="/flone/member/my-account-edit.hm?memberId=${sessionScope.MEMBER.memberId}"
                                      method="post" onsubmit="return validatePhoneNumber()">
                                    <div class="panel-body">
                                        <div class="myaccount-info-wrapper">
                                            <div class="account-info-wrapper">
                                                <h3>내 정보</h3>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-12 col-md-12">
                                                    <div class="billing-info"
                                                         style="position: relative; display: flex; align-items: center;">
                                                        <label style="margin-right: 10px">비밀번호</label>
                                                        <input id="passwordConfirm" name="passwordConfirm"
                                                               type="password" placeholder="변경하실 비밀번호를 입력해주세요." required
                                                               style="flex: 1; padding-right: 40px; border: 1px solid #ced4da; border-radius: 4px;">
                                                        <button type="button"
                                                                onclick="toggleVisibilityPassword('passwordConfirm', 'iconConfirm')"
                                                                style="position: absolute; right: 10px; top: 50%; transform: translateY(-50%); background: none; border: none; cursor: pointer; padding: 0; font-size: 16px;">
                                                            <i id="iconConfirm" class="fa fa-eye-slash"
                                                               style="font-size: 18px; color: #6c757d;"></i>
                                                        </button>
                                                    </div>
                                                </div>

                                                <div class="col-lg-12 col-md-12">
                                                    <div class="billing-info"
                                                         style="position: relative; display: flex; align-items: center;">
                                                        <label style="margin-right: 10px">현재 전화번호</label>
                                                        <input name="phone" id="phone" type="text"
                                                               placeholder="${myAccount.phone}" readonly
                                                               style="flex: 1; padding-right: 40px; border: 1px solid #ced4da; border-radius: 4px;">
                                                    </div>
                                                </div>

                                                <div class="col-lg-12 col-md-12">
                                                    <div class="billing-info"
                                                         style="position: relative; display: flex; align-items: center;">
                                                        <label style="margin-right: 10px;">전화번호 확인</label>
                                                        <input name="phoneConfirm" id="phoneConfirm" type="text"
                                                               value="010"
                                                               placeholder="변경하실 핸드폰 번호를 입력해주세요."
                                                               style="flex: 1; padding-right: 40px; border: 1px solid #ced4da; border-radius: 4px;"
                                                               oninput="formatPhoneNumber(this)">
                                                    </div>
                                                </div>

                                                <div class="col-lg-12 col-md-12" style="margin-top: -26px;">
                                                    <div class="billing-back-btn">
                                                        <div class="billing-back">
                                                        </div>
                                                        <div class="billing-btn">
                                                            <button type="submit">변경하기</button>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default single-my-account">
                    <div class="panel-heading my-account-title">
                        <h3 class="panel-title"><span>3 .</span> <a data-bs-toggle="collapse" href="#my-account-3">회원
                            탈퇴</a></h3>
                    </div>
                    <div id="my-account-3" class="panel-collapse collapse" data-bs-parent="#faq">
                        <div class="panel-body">
                            <div class="myaccount-info-wrapper">
                                <div class="entries-wrapper">
                                    <div class="row">
                                        <div class="col-lg-6 col-md-6 d-flex align-items-center justify-content-center">
                                            <div class="entries-info text-center">
                                                <p>회원 탈퇴 기능입니다.</p>
                                                <p>버튼을 누르면 비회원으로 전환되며</p>
                                                <p>철회는 불가능하니 신중하게 부탁드립니다.</p>
                                            </div>
                                        </div>
                                        <form action="/flone/member/delete.hm?memberId=${sessionScope.MEMBER.memberId}" method="post">
                                            <div class="col-lg-6 col-md-6 d-flex align-items-center justify-content-center">
                                                <div class="entries-edit-delete text-center">
                                                    <a class="edit" href="/flone/index.hm">홈으로</a>
                                                    <button type="submit">회원탈퇴</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-5 col-sm-12 col-xs-12">
                        <div class="tab-content quickview-big-img">
                            <div id="pro-1" class="tab-pane fade show active">
                                <img src="assets/img/product/quickview-l1.jpg" alt="">
                            </div>
                            <div id="pro-2" class="tab-pane fade">
                                <img src="assets/img/product/quickview-l2.jpg" alt="">
                            </div>
                            <div id="pro-3" class="tab-pane fade">
                                <img src="assets/img/product/quickview-l3.jpg" alt="">
                            </div>
                            <div id="pro-4" class="tab-pane fade">
                                <img src="assets/img/product/quickview-l2.jpg" alt="">
                            </div>
                        </div>
                        <!-- Thumbnail Large Image End -->
                        <!-- Thumbnail Image End -->
                        <div class="quickview-wrap mt-15">
                            <div class="quickview-slide-active owl-carousel nav nav-style-1" role="tablist">
                                <a class="active" data-bs-toggle="tab" href="#pro-1"><img
                                        src="assets/img/product/quickview-s1.jpg" alt=""></a>
                                <a data-bs-toggle="tab" href="#pro-2"><img src="assets/img/product/quickview-s2.jpg"
                                                                           alt=""></a>
                                <a data-bs-toggle="tab" href="#pro-3"><img src="assets/img/product/quickview-s3.jpg"
                                                                           alt=""></a>
                                <a data-bs-toggle="tab" href="#pro-4"><img src="assets/img/product/quickview-s2.jpg"
                                                                           alt=""></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7 col-sm-12 col-xs-12">
                        <div class="product-details-content quickview-content">
                            <h2>Products Name Here</h2>
                            <div class="product-details-price">
                                <span>$18.00 </span>
                                <span class="old">$20.00 </span>
                            </div>
                            <div class="pro-details-rating-wrap">
                                <div class="pro-details-rating">
                                    <i class="fa fa-star-o yellow"></i>
                                    <i class="fa fa-star-o yellow"></i>
                                    <i class="fa fa-star-o yellow"></i>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                </div>
                                <span>3 Reviews</span>
                            </div>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisic elit eiusm tempor incidid ut labore et
                                dolore magna aliqua. Ut enim ad minim venialo quis nostrud exercitation ullamco</p>
                            <div class="pro-details-list">
                                <ul>
                                    <li>- 0.5 mm Dail</li>
                                    <li>- Inspired vector icons</li>
                                    <li>- Very modern style</li>
                                </ul>
                            </div>
                            <div class="pro-details-size-color">
                                <div class="pro-details-color-wrap">
                                    <span>Color</span>
                                    <div class="pro-details-color-content">
                                        <ul>
                                            <li class="blue"></li>
                                            <li class="maroon active"></li>
                                            <li class="gray"></li>
                                            <li class="green"></li>
                                            <li class="yellow"></li>
                                            <li class="white"></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="pro-details-size">
                                    <span>Size</span>
                                    <div class="pro-details-size-content">
                                        <ul>
                                            <li><a href="#">s</a></li>
                                            <li><a href="#">m</a></li>
                                            <li><a href="#">l</a></li>
                                            <li><a href="#">xl</a></li>
                                            <li><a href="#">xxl</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="pro-details-quality">
                                <div class="cart-plus-minus">
                                    <input class="cart-plus-minus-box" type="text" name="qtybutton" value="2">
                                </div>
                                <div class="pro-details-cart btn-hover">
                                    <a href="#">Add To Cart</a>
                                </div>
                                <div class="pro-details-wishlist">
                                    <a href="#"><i class="fa fa-heart-o"></i></a>
                                </div>
                                <div class="pro-details-compare">
                                    <a href="#"><i class="pe-7s-shuffle"></i></a>
                                </div>
                            </div>
                            <div class="pro-details-meta">
                                <span>Categories :</span>
                                <ul>
                                    <li><a href="#">Minimal,</a></li>
                                    <li><a href="#">Furniture,</a></li>
                                    <li><a href="#">Electronic</a></li>
                                </ul>
                            </div>
                            <div class="pro-details-meta">
                                <span>Tag :</span>
                                <ul>
                                    <li><a href="#">Fashion, </a></li>
                                    <li><a href="#">Furniture,</a></li>
                                    <li><a href="#">Electronic</a></li>
                                </ul>
                            </div>
                            <div class="pro-details-social">
                                <ul>
                                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                                    <li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
                                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<%@include file="script.jsp" %>
<script src="/resources/assets/js/toggleVisibility.js"></script>
</body>

</html>