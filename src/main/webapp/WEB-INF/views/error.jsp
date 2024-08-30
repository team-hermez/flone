<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script>
        window.onload = function() {
            alert('${errorMessage}');
            window.history.back();
        }
    </script>
</head>
<body>
</body>
</html>