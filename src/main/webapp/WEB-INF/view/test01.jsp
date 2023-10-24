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
    <title>test01</title>
</head>
<body>
<form method="post" action="/test02">
  <div>
    Title : <input type="text" name="title">
  </div>
  <div>
    DueDate : <input type="date" name="dueDate" value="2022-12-25">
  </div>
  <div>
    Writer : <input type="text" name="writer">
  </div>
  <div>
    finished : <input type="checkbox" name="finished">
  </div>
  <div>
    <button type="submit">Register</button>
  </div>

</form>
</body>
</html>
