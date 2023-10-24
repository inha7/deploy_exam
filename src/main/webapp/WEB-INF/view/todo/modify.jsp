<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <title>modify</title>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!-- 기존의 <h1>Header</h1> -->
    <div class="row">
      <div class="col">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="#">Home</a>
                <a class="nav-link" href="#">Features</a>
                <a class="nav-link" href="#">Pricing</a>
                <a class="nav-link disabled">Disabled</a>
              </div>
            </div>
          </div>
        </nav>
      </div>
    </div>
    <!-- header end -->
    <!-- 기존의 <h1>Header</h1>끝 -->
    <div class="row content">
      <div class="col">
        <div class="card">
          <div class="card-header">
            Featured
          </div>
          <div class="card-body">
            <%-- 여기에작성--%>
              <form action="/todo/modify" method="post">
<%--                <input type="hidden" name="page" value="${pageRequestDTO.page}">--%>
<%--                <input type="hidden" name="size" value="${pageRequestDTO.size}">--%>
                <div class="input-group mb-3">
                  <span class="input-group-text">TNO</span>
                  <input type="text" name="tno" class="form-control" value=${dto.tno} readonly>
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text">Title</span>
                  <input type="text" name="title" class="form-control" value=${dto.title}>
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text">DueDate</span>
                  <input type="date" name="dueDate" class="form-control" value=${dto.dueDate}>
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text">Writer</span>
                  <input type="text" name="writer" class="form-control" value=${dto.writer} readonly>
                </div>

                <div class="form-check">
                  <label class="form-check-label">
                    Finished &nbsp;
                  </label>
                  <input class="form-check-input" type="checkbox" name="finished" ${dto.finished?"checked":""}>
                </div>

              <div class="my-4">
                <div class="float-end">
                  <button type="button" class="btn btn-danger">Remove</button>
                  <button type="button" class="btn btn-primary">Modify</button>
                  <button type="button" class="btn btn-secondary">List</button>
                </div>
              </div>
              </form>
              <script> // 유효성검사
                const serverValidResult = {};
                <c:forEach items="${errors}" var="error">
                serverValidResult['${error.getField()}'] = '${error.defaultMessage}';
                </c:forEach>
                console.log(serverValidResult);
              </script>
              <script>
                const frmView = document.querySelector("form");
                document.querySelector(".btn-danger").addEventListener("click", function () {
                  frmView.action = "/todo/remove?${pageRequestDTO.link}";
                  frmView.method = "post";
                  frmView.submit();
                });
                document.querySelector(".btn-primary").addEventListener("click", function () {
                  frmView.action = "/todo/modify";
                  frmView.method = "post";
                  frmView.submit();
                });
                // document.querySelector(".btn-secondary").addEventListener("click", function () {
                //   self.location="/todo/list";
                // });
                document.querySelector(".btn-secondary").addEventListener("click", function () { // 수정(현재)에서 목록
                  self.location="/todo/list?${pageRequestDTO.link}";
                }, false); // , false : 기본값이라네
              </script>


          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="row content">
    <h1>Content</h1>
  </div>

  <div class="row footer">
    <!--    <h1>Footer</h1>-->
    <div class="row fixed-bottom" style="z-index: -100">
      <footer class="py-1 my-1">
        <p class="text-center text-muted">Footer</p>
      </footer>
    </div>
  </div>

</div>

<script src="/resources/js/bootstrap.bundle.min.js"></script>

</body>
</html>