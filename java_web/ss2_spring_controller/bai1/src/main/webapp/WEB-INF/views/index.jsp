<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<head>
    <title>Sandwich Condiments</title>
</head>
<body>
<h2>Chọn các gia vị ăn kèm với Sandwich:</h2>
<form action="save" method="post">
    <input type="checkbox" name="condiment" value="Lettuce" /> Xà lách<br/>
    <input type="checkbox" name="condiment" value="Tomato" /> Cà chua<br/>
    <input type="checkbox" name="condiment" value="Mustard" /> Mù tạt<br/>
    <input type="checkbox" name="condiment" value="Sprouts" /> Giá đỗ<br/>
    <input type="checkbox" name="condiment" value="Cheese" /> Phô mai<br/>
    <br/>
    <button type="submit">Lưu</button>
</form>
</body>
</html>
