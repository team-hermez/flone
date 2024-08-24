<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp"%>
</head>
<body>



    <!-- PAGE LOADING
    ================================================== -->
    <div id="preloader"></div>

    <!-- MAIN WRAPPER
    ================================================== -->

        <%@ include file="header.jsp" %>
        <!-- INSTRUCTORS DETAILS
        ================================================== -->
    <div class="main-wrapper">
        <div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
            <div class="container">
                <div class="breadcrumb-content text-center">
                    <ul>
                        <li>
                            <a href="../index.hm">Home</a>
                        </li>
                        <li class="active">instructor-detail</li>
                    </ul>
                </div>
            </div>
        </div>
        <section>
            <div class="container">
            <c:forEach var ="instructorDetail" items="${instructorDetail}">
                <div style="margin-top: 50px" class="row mb-1-9 mb-xl-2-9">
                    <div class="col-lg-5 mb-1-9 mb-md-2-5 mb-lg-0">
                        <div class="instructor-img-wrapper mb-50 md-mb-0">
                            <div class="mb-1-6 mb-xl-1-9">
                                <img class="border-radius-5" src="/assets/img/team/team-02.jpg" alt="...">
                            </div>
                            <div class="text-center">
                                <h3 class="font-weight-800 display-26 display-md-25 display-xl-24 text-primary">${instructorDetail.name}</h3>
                                <p class="alt-font text-secondary font-weight-700 mb-2">${instructorDetail.subjectName}</p>
                                <ul class="personal-info">
                                    <li>
                                        <i class="ti-email"></i>
                                        <a href="mailto:info@yourwebsite.com" class="text-primary">info@yourwebsite.com</a>
                                    </li>
                                    <li>
                                        <i class="ti-mobile"></i>
                                        <a href="tel:+442056581823" class="text-primary">+44 205-658-1823</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div class="ps-lg-1-9 ps-xl-6">
                            <div class="row mb-1-9 mb-xl-2-9">
                                <div class="col-lg-12">
                                    <p class="lead text-secondary font-weight-700">${instructorDetail.instructorTitle}</p>
                                    <h4 class="text-dark">About Me:</h4>
                                    <p class="alt-font text-color font-weight-500">${instructorDetail.instructorDescription}</p>
                                    </p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-1-6 mb-md-0 mb-lg-1-9 mb-lg-0">
                                    <h4 class="text-dark">Education:</h4>
                                    <ul class="instructor-details-info">
                                        <li>
                                            <i class="ti-book"></i>
                                            <p class="mb-0 text-primary font-weight-800 display-29">Harvard University</p>
                                            <span class="text-color alt-font text-capitalize display-30">Bachelor in Mathematics</span>
                                        </li>
                                        <li>
                                            <i class="ti-book"></i>
                                            <p class="mb-0 text-primary font-weight-800 display-29">University of Toronto</p>
                                            <span class="text-color alt-font text-capitalize display-30">Bachelor in English</span>
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-md-6">
                                    <h4 class="text-dark">Experience:</h4>
                                    <ul class="instructor-details-info">
                                        <li>
                                            <i class="ti-briefcase"></i>
                                            <p class="mb-0 text-primary font-weight-800 display-29">Harvard University</p>
                                            <span class="text-color alt-font text-capitalize display-31">2021 - 2022</span>
                                        </li>
                                        <li>
                                            <i class="ti-briefcase"></i>
                                            <p class="mb-0 text-primary font-weight-800 display-29">University of Toronto</p>
                                            <span class="text-color alt-font text-capitalize display-30">2022 - 2023</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mb-1-9 mb-xl-2-9">
                <div class="related-product-area pb-95">
                    <div class="container">
                        <div style="margin-top: 50px" class="section-title text-center mb-50">
                            <h2>교사테스트님의 다른 강의</h2>
                        </div>
                        <div class="related-product-active owl-carousel owl-dot-none owl-loaded owl-drag">
                            <div class="owl-stage-outer"><div class="owl-stage" style="transform: translate3d(-961px, 0px, 0px); transition: all; width: 3525px;"><div class="owl-item cloned" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-3.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-3-1.jpg" alt="">
                                    </a>
                                    <span class="pink">-10%</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                        <span class="old">$ 60.00</span>
                                    </div>
                                </div>
                            </div></div><div class="owl-item cloned" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-4.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-4-1.jpg" alt="">
                                    </a>
                                    <span class="purple">New</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                    </div>
                                </div>
                            </div></div><div class="owl-item cloned" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-5.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-5-1.jpg" alt="">
                                    </a>
                                    <span class="pink">-10%</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                        <span class="old">$ 60.00</span>
                                    </div>
                                </div>
                            </div></div><div class="owl-item active" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-1.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-1-1.jpg" alt="">
                                    </a>
                                    <span class="pink">-10%</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                        <span class="old">$ 60.00</span>
                                    </div>
                                </div>
                            </div></div><div class="owl-item active" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-2.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-2-1.jpg" alt="">
                                    </a>
                                    <span class="purple">New</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                    </div>
                                </div>
                            </div></div><div class="owl-item active" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-3.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-3-1.jpg" alt="">
                                    </a>
                                    <span class="pink">-10%</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                        <span class="old">$ 60.00</span>
                                    </div>
                                </div>
                            </div></div><div class="owl-item" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-4.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-4-1.jpg" alt="">
                                    </a>
                                    <span class="purple">New</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                    </div>
                                </div>
                            </div></div><div class="owl-item" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-5.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-5-1.jpg" alt="">
                                    </a>
                                    <span class="pink">-10%</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                        <span class="old">$ 60.00</span>
                                    </div>
                                </div>
                            </div></div><div class="owl-item cloned" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-1.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-1-1.jpg" alt="">
                                    </a>
                                    <span class="pink">-10%</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                        <span class="old">$ 60.00</span>
                                    </div>
                                </div>
                            </div></div><div class="owl-item cloned" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-2.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-2-1.jpg" alt="">
                                    </a>
                                    <span class="purple">New</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                    </div>
                                </div>
                            </div></div><div class="owl-item cloned" style="width: 290.406px; margin-right: 30px;"><div class="product-wrap">
                                <div class="product-img">
                                    <a href="product-details.html">
                                        <img class="default-img" src="/assets/img/product/pro-3.jpg" alt="">
                                        <img class="hover-img" src="/assets/img/product/pro-3-1.jpg" alt="">
                                    </a>
                                    <span class="pink">-10%</span>
                                    <div class="product-action">
                                        <div class="pro-same-action pro-wishlist">
                                            <a title="Wishlist" href="#"><i class="pe-7s-like"></i></a>
                                        </div>
                                        <div class="pro-same-action pro-cart">
                                            <a title="Add To Cart" href="#"><i class="pe-7s-cart"></i> Add to cart</a>
                                        </div>
                                        <div class="pro-same-action pro-quickview">
                                            <a title="Quick View" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content text-center">
                                    <h3><a href="product-details.html">T- Shirt And Jeans</a></h3>
                                    <div class="product-rating">
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o yellow"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <div class="product-price">
                                        <span>$ 60.00</span>
                                        <span class="old">$ 60.00</span>
                                    </div>
                                </div>
                            </div></div></div></div><div class="owl-nav disabled">
                            <button type="button" role="presentation" class="owl-prev"><span aria-label="Previous">‹</span></button><button type="button" role="presentation" class="owl-next"><span aria-label="Next">›</span></button></div><div class="owl-dots"><button role="button" class="owl-dot active"><span></span></button><button role="button" class="owl-dot"><span></span></button></div></div>
                    </div>
                </div>
            </div>
            </div>
           </c:forEach>
        </section>

        <!-- FOOTER
        ================================================== -->

    <%@ include file="footer.jsp" %>
    <%@ include file="script.jsp"%>
</body>

</html>