<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="/include/head.jsp" %>

    <link rel="stylesheet" href="${CSS_PATH}/signup.css" />
    <title>회원가입</title>
  </head>
  <body>
    <%@ include file="/include/header.jsp" %>

    <c:if test="${empty user}">
      <script>
        alert("로그인이 필요합니다.");
      </script>
    </c:if>

    <!-- 메인 시작 -  -->
    <main>
      <form id="form" action="${root}/member" method="post">
        <input type="hidden" name="action" value="modify" />
        <h2>SignUp</h2>
        <div class="input-box">
          <input id="email" type="email" name="email" placeholder="이메일" value="${user.email}"  disabled />
          <label for="email">이메일</label>
        </div>

        <div class="input-box">
          <input id="nickname" type="text" name="nickname" placeholder="변경할 닉네임" value="${user.nickname}" required />
          <label for="nickname">변경할 닉네임</label>
        </div>

        <div class="input-box">
          <input id="password" type="password" name="password" placeholder="변경할 비밀번호" required />
          <label for="password">변경할 비밀번호</label>
        </div>

        <div class="input-box">
          <input id="confirm-password" type="password" placeholder="변경할 비밀번호 확인" required />
          <label for="password">비밀번호 확인</label>
        </div>

        <input class="submit-btn" type="submit" value="회원정보 수정" />
      </form>
    </main>
    <!-- 메인 끝 -->

    <%@ include file="/include/footer.jsp" %>

    <script>
      let form = document.getElementById("form");
      form.addEventListener("submit", function (e) {
        let passwordEl1 = document.getElementById("password");
        let passwordEl2 = document.getElementById("confirm-password");

        if (passwordEl1.value != passwordEl2.value) {
          alert("비밀번호가 다릅니다.");
          passwordEl1.value = "";
          passwordEl2.value = "";
          e.preventDefault(); // 여기서 이벤트 중단
        }
      });
    </script>
  </body>
</html>
