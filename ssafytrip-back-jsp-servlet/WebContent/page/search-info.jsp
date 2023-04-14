<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	
	<%@ include file="/include/head.jsp" %>
	
    <link rel="stylesheet" href="${CSS_PATH}/search-info.css" />
    <title>관광지 조회</title>
  </head>
  <body>

	<%@ include file="/include/header.jsp" %>

    <!-- main 시작 -->
    <main>
      <div class="h2-container">
        <h2>관광 정보 검색</h2>
      </div>

      <div class="option-container">
        <select id="op1" name="op1" onchange="changeOp1()">
          <option value="">시/도를 선택하세요</option>
        </select>
        <select id="op2" name="op2">
          <option value="">군/구를 선택하세요</option>
        </select>
        <select id="op3" name="op3">
          <option value="">컨텐츠 선택</option>
          <option value="12">관광지</option>
          <option value="14">문화시설</option>
          <option value="15">행사/공연/축제</option>
          <option value="25">여행코스</option>
          <option value="28">레포츠</option>
          <option value="32">숙박</option>
          <option value="38">쇼핑</option>
          <option value="39">음식점</option>
        </select>
        <input id="search" type="text" placeholder="검색어 입력" />
        <button id="searchBtn" onclick="searchInfo()">조회</button>
      </div>
      <div class="map-info-container">
        <div class="map" id="map"></div>
        <div class="info-list">
          <ul id="infoList">
            <li>관광정보를 조회하세요.</li>
          </ul>
        </div>
      </div>
    </main>
    <!-- main 끝 -->

	<%@ include file="/include/footer.jsp" %>

	<script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ab247539589d9974c85afb1f6a94ef26"
    ></script>
    <script src="${JS_PATH}/search-info.js"></script>
  </body>
</html>
