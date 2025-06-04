<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Email Settings</title>
</head>
<body>
<h2>[Bài tập] Chương trình lưu giữ cấu hình hộp thư điện tử</h2>

<h4>Settings</h4>

<%--@elvariable id="emailConfig" type="com.codegym.bai_tap1.model.EmailConfig"--%>
<form:form modelAttribute="emailConfig" action="/update" method="post">
    <p>Languages:
        <form:select path="language">
            <form:options items="${languages}"/>
        </form:select>
    </p>

    <p>Page Size:
        <form:select path="pageSize">
            <form:options items="${pageSizes}"/>
        </form:select>
        emails per page
    </p>

    <p>Spams filter:
        <form:checkbox path="spamsFilter"/> Enable spams filter
    </p>

    <p>Signature:<br/>
        <form:textarea path="signature" rows="4" cols="40"/>
    </p>

    <input type="submit" value="Update"/>
    <input type="reset" value="Cancel"/>
</form:form>

</body>
</html>
