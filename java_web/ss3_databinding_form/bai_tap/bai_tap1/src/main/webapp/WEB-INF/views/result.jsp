<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Result</title></head>
<body>
<h3>Settings Updated:</h3>
<p>Language: ${emailConfig.language}</p>
<p>Page Size: ${emailConfig.pageSize}</p>
<p>Spams Filter: ${emailConfig.spamsFilter ? "Enabled" : "Disabled"}</p>
<p>Signature: <pre>${emailConfig.signature}</pre></p>

<a href="/">Back to form</a>
</body>
</html>
