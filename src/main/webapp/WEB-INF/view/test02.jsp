<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-10-12
  Time: 오전 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test02</title>
</head>
<body>
<div>제목 : ${todoDTO.title}</div>
<div>기간 : ${todoDTO.dueDate}</div>
<div>완료여부 : ${todoDTO.finished}</div>
<div>작성자 : ${todoDTO.writer}</div>

</body>
</html>
