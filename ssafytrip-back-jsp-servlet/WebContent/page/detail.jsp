<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	
	<%@ include file="/include/head.jsp" %>
	
    <link rel="stylesheet" href="${CSS_PATH}/detail.css" />
    <title>여행지 상세보기</title>
  </head>
  <body>

	<%@ include file="/include/header.jsp" %>

    <!-- 메인 시작 -->
    <main>
      <div class="banner-img">
        <img id="bannerImg" class="image" alt="" />
      </div>
      <div class="map-container">
        <div class="map" id="map"></div>
        <div class="description">
          <h2 id="description-title">description-title</h2>
          <p id="description-subtext">description-subtext</p>
        </div>
      </div>

      <div class="detail-section">
        <div class="deatil-head">
          <h3 id="detailTitle">detail title</h3>
        </div>
        <div class="addr">
          <img style="height: 20px; margin-right: 10px" src="./asset/img/marker.png" alt="" /><span
            id="addr"
          ></span>
        </div>
        <div id="imgContainer" class="detail-img-container">
          <!-- 이미지가 들어가는 곳 -->

        </div>
      </div>
    </main>
    <!-- 메인 끝 -->

	<%@ include file="/include/footer.jsp" %>

    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ab247539589d9974c85afb1f6a94ef26"
    ></script>
    <script src="${JS_PATH}/detail.js"></script>
  </body>
</html>
