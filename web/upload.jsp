<%--
  Created by IntelliJ IDEA.
  User: CK
  Date: 2019/8/12
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<html>
<head>
    <title>上传图片</title>
</head>
<body>
<form action="uploadServlet" method="post" enctype="multipart/form-data">
    上传图片:
    <input type="file" name="upload" />
    <input type="submit" value="上传">
</form>
</body>
</html>
