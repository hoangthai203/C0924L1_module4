<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<html>
<head>
  <title>Kết quả</title>
</head>
<body>
<h2>Các gia vị bạn đã chọn:</h2>
<c:choose>
  <c:when test="${not empty condiments}">
    <ul>
      <c:forEach var="item" items="${condiments}">
        <li>${item}</li>
      </c:forEach>
    </ul>
  </c:when>
  <c:otherwise>
    <p>Bạn chưa chọn gia vị nào.</p>
  </c:otherwise>
</c:choose>
<a href="/">Quay lại</a>
</body>
</html>
