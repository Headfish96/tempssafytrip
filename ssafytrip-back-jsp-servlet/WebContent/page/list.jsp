<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="/include/head.jsp" %>

    <link rel="stylesheet" href="${CSS_PATH}/list.css" />
    <title id="headTitle">인기 여행지</title>
  </head>
  <body>
    <%@ include file="/include/header.jsp" %>

    <!-- 메인 시작 -  -->
    <main>
      <div>
        <h2 id="pageTitle" class="page-title">인기 여행지</h2>
        <!-- <h2 class="page-title">추천 여행지</h2> -->
      </div>

      <div id="list-section">
        <!-- <div class="list-container">
          <div class="list-img"><img src="" alt="" /></div>
          <div class="list-discription-container">
            <h2 class="list-title">테스트 타이틀</h2>
            <div class="list-text">설명</div>
            <button class="like-btn">나만의 여행 등록</button>
          </div>
        </div> -->
      </div>
    </main>
    <!-- 메인 끝 -->

    <%@ include file="/include/footer.jsp" %>

    <script src="${JS_PATH}/list.js"></script>
  </body>
</html>
