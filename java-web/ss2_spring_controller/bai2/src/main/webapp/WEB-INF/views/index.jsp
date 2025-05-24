<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
<h1>Caculator</h1>

<form action="calculate" method="post">
    <input type="text" name="num1" placeholder="Number 1"/>
    <input type="text" name="num2" placeholder="Number 2"/><br/><br/>

    <button type="submit" name="operator" value="+">Addition(+)</button>
    <button type="submit" name="operator" value="-">Subtraction(-)</button>
    <button type="submit" name="operator" value="*">Multiplication(X)</button>
    <button type="submit" name="operator" value="/">Division(/)</button>
</form>

<c:if test="${not empty message}">
    <h3>${message} : ${result}</h3>
</c:if>

</body>
</html>
