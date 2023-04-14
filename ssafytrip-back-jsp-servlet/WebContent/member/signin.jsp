<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/path.jsp" %>
<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${CSS_PATH}/login.css" />
    <title>로그인</title>
  </head>
  <body>
    <!-- 로그인 폼 -->
    <div class="center">
      <div class="container">
        <div class="text">LOG IN</div>
        <div class="form-container">
          <div class="data">
            <label>이메일</label>
            <input type="text" id="myroLoginId" required />
          </div>
          <div class="data">
            <label>비밀번호</label>
            <input
              type="password"
              id="myroLoginPwd"
              onKeypress="javascript:if(event.keyCode==13) {myroLogin()}"
              required
            />
          </div>
          <div class="btn">
            <div class="inner"></div>
            <button type="submit" id="myroLoginBtn" onclick="myroLogin()">로그인</button>
          </div>
        </div>
        <div class="divider-container">
          <div class="divider"></div>
          <span>or</span>
        </div>
        <div class="sns-text">SNS 간편 로그인</div>
        <div class="socialBtn-container">
          <div class="socialBtn">
            <div
              class="socialBtn-image-container"
              onclick="location.href =`auth/kakao?params=${fromMobile},${savedRouteTokenKey}`"
            >
              <img src="${IMG_PATH}/카톡로고.png" alt="logo" />
            </div>
          </div>
          <div class="socialBtn">
            <div
              class="socialBtn-image-container"
              onclick="location.href=`auth/naver?params=${fromMobile},${savedRouteTokenKey}`"
            >
              <img src="${IMG_PATH}/애플로고.png" alt="logo" />
            </div>
          </div>
          <div class="socialBtn google-mobile-login">
            <div
              class="socialBtn-image-container"
              onclick="location.href=`/auth/google?params=${fromMobile},${savedRouteTokenKey}`"
            >
              <img src="${IMG_PATH}/구글로고.png" alt="logo" />
              <!-- <span class="small-text">구글로 로그인하기</span> -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
