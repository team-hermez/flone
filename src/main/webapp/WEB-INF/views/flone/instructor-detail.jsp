<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp" %>
</head>
<body>
<%@ include file="header.jsp" %>

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
    <img src="../../../../images/${instructorDetail.saveImage}" width="300px" height="300px"
         onerror="this.onerror=null; this.src='../../../../images/image.png';">

                <h5>${instructorDetail.subjectName}</h5>
                <h5>${instructorDetail.name}</h5>
                <h5>${instructorDetail.instructorTitle}</h5>
                <h5>${instructorDetail.instructorDescription}</h5>

</div>
<%@ include file="footer.jsp" %>
<%@ include file="script.jsp" %>
</body>

</html>