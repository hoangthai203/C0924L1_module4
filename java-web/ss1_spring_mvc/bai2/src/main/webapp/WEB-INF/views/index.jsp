<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Từ điển Anh - Việt</title>
</head>
<body>
<h2>Tra từ Anh - Việt</h2>
<form action="translate" method="post">
  <label>Nhập từ tiếng Anh:</label>
  <input type="text" name="word" required />
  <button type="submit">Dịch</button>
</form>

<c:if test="${not empty word}">
  <h3>
    Kết quả:
    <c:choose>
      <c:when test="${not empty result}">
        ${word} → ${result}
      </c:when>
      <c:otherwise>
        Không tìm thấy từ "${word}" trong từ điển.
      </c:otherwise>
    </c:choose>
  </h3>
</c:if>
</body>
</html>
