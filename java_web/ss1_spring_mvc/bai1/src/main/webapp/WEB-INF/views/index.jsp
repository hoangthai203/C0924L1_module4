<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Chuyển Đổi Tiền Tệ</title>
</head>
<body>
<h2>Chuyển đổi tiền tệ (USD → VND)</h2>
<form action="convert" method="post">
  <label>Tỉ giá (VND):</label>
  <input type="text" name="rate" required/><br/><br/>

  <label>Số tiền (USD):</label>
  <input type="text" name="usd" required/><br/><br/>

  <button type="submit">Chuyển đổi</button>
</form>

<c:if test="${not empty result}">
  <h3>${usd} USD = ${result} VND (Tỉ giá: ${rate})</h3>
</c:if>
</body>
</html>
