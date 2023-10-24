<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <title>Hello, world!</title>
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

        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">백인하.</h5>
                        <form action="/todo/list" method="get">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <div class="mb-3">
                                <input type="checkbox" name="finished"${pageRequestDTO.finished ? "checked" : ""}>완료여부
                            </div>
                            <div class="mb-3">
                                <input type="checkbox" name="types" value="t"${pageRequestDTO.checkType("t") ? " checked " : ""}>제목
                                <input type="checkbox" name="types" value="w"${pageRequestDTO.checkType("w") ? " checked " : ""}>작성자
                                <input type="text" name="keyword" class="form-control" value="${pageRequestDTO.keyword}">
                            </div>
                            <div class="input-group mb-3 dueDateDiv">
                                <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">
                                <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}">
                            </div>
                            <div class="input-group mb-3">
                                <div class="float-end">
                                    <button type="submit" class="btn btn-primary">Search</button>
                                    <button type="reset" class="btn btn-info clearBtn">Clear</button>
                                </div>
                            </div>
                            <script>
                                // clear(reset) 버튼으로 검색 초기화(1페이지로 이동) - 안하면 검색조건 다지우고 1페이지가야함?!
                                document.querySelector(".clearBtn").addEventListener("click", function (e) {
                                    self.location = '/todo/list';
                                });
                            </script>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
<%--                        여기에작성--%>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Tno</th>
                                <th scope="col">Title</th>
                                <th scope="col">Writer</th>
                                <th scope="col">DueDate</th>
                                <th scope="col">Finished</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="dto" items="${responseDTO.dtoList}">
                                <tr>
                                    <th scope="row">${dto.tno}</th>
                                    <td><a href="/todo/read?tno=${dto.tno}&${pageRequestDTO.link}" class="text-decoration-none" data-tno="${dto.tno}">
                                            <c:out value="${dto.title}"/> </a></td>
                                    <td>${dto.writer}</td>
                                    <td>${dto.dueDate}</td>
                                    <td>${dto.finished}</td>
                                </tr>

                            </c:forEach>


                            </tbody>
                        </table>
                        <div class="float-end">
                            <ul class="pagination flex-wrap">
                                <c:if test="${responseDTO.prev}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.start-1}">Previous</a>
                                    </li>
                                </c:if>
                                <c:forEach var="num" begin="${responseDTO.start}" end="${responseDTO.end}">
                                    <li class="page-item ${responseDTO.page == num ? "active" : ""}"><a class="page-link" data-num="${num}" >${num}</a> </li>
                                </c:forEach>
                                <c:if test="${responseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.end+1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        <script>
                            document.querySelector('.pagination').addEventListener('click', function (e) {
                                e.preventDefault();
                                e.stopPropagation();

                                const target = e.target;
                                if (target.tagName !== 'A') { // A태그일경우
                                    return;
                                }

                                const num = target.getAttribute('data-num');
                                const frmPage = document.querySelector('form');
                                frmPage.innerHTML += `<input type="hidden" name="page" value="\${num}">`;
                                frmPage.submit();

                                // self.location=`/todo/list?page=\${num}`; // 백틱을 이용하여 탬플릿 처리(문자열 병합에 '+' 이용의 불편함 줄일 수 있음 jsp의 EL이 아니라는 것 표시 위해  로 처리
                            });
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