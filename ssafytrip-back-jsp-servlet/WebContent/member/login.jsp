<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="/include/head.jsp" %>

    <link rel="stylesheet" href="${CSS_PATH}/signin.css" />
    <title>로그인</title>
  </head>
  <body>
    <%@ include file="/include/header.jsp" %>

    <c:if test="${not empty msg}">
      <script>
        alert("${msg}");
      </script>
    </c:if>

    <!-- 메인 시작 -  -->
    <main>
      <form action="${root}/member" method="post">
        <input type="hidden" name="action" value="login" />
        <h2>Login</h2>
        <div class="input-box">
          <input
            id="email"
            type="email"
            name="email"
            placeholder="이메일"
            value="${cookie.email.value}"
            required
          />
          <label for="email">이메일</label>
        </div>

        <div class="input-box">
          <input id="password" type="password" name="password" placeholder="비밀번호" required />
          <label for="password">비밀번호</label>
        </div>
        <div class="input-check">
          <input type="checkbox" name="remember" id="remember" />
          <label for="remember">이메일 기억하기</label>
        </div>
        <input class="submit-btn" type="submit" value="로그인" />
      </form>
    </main>
    <!-- 메인 끝 -->

    <%@ include file="/include/footer.jsp" %>

    <script>
      window.onload = () => {
        let input = document.getElementById("email").value;
        if (input != "") {
          document.getElementById("remember").checked = true;
        }
      };
    </script>
  </body>
</html>
