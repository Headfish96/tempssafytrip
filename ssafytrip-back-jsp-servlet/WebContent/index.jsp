<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="/include/head.jsp" %>

    <link rel="stylesheet" href="${CSS_PATH}/index.css" />
    <title>여행!</title>
  </head>
  <body>
    <%@ include file="/include/header.jsp" %>

    <!-- 메인 시작 -  -->
    <main>
      <!-- Swiper 배너 -->
      <div class="swiper-container">
        <div id="swiper-wrapper" class="swiper-wrapper">
          <%-- 배너 데이터 삽입 start --%>
          <c:forEach var="item" items="${banner}">
            <div class="swiper-slide">
              <div
                id="${item.contentId}"
                class="div-img-temp"
                style="background-image: url(${item.firstImage})"
                onclick="locateDetail(this)"
              >
                <div class="swiper-description">
                  <h2><c:out value="${item.title}" /></h2>
                  <p>
                    <c:out value="${item.overview}" />
                  </p>
                </div>
              </div>
            </div>
          </c:forEach>
          <%-- 배너 데이터 삽입 end --%>
        </div>
      </div>

      <div class="popular-container">
        <div class="main-text">인기 여행지</div>
        <div>
          <div id="popularList" class="popular-card-list">
            <!-- 인기여행지 리스트 -->

            <c:forEach var="item" items="${popular}">
              <div class="popular-card" id="${item.contentId}" onclick="locateDetail(this)">
                <div class="popular-img">
                  <img src="${item.firstImage}" alt="" />
                </div>
                <h3 class="popular-title"><c:out value="${item.title}" /></h3>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="see-more-btn-div">
          <button class="see-more-btn" onclick="location.href='${root}/list?type=popular'">See More</button>
        </div>
      </div>
      <div class="recommend-container">
        <div class="main-text">축제</div>
        <div class="recommend-list">
          <div id="festivalList" class="recommend-row recommend-row-1">
            <!-- 축제 리스트 -->

            <c:forEach var="item" items="${festival}">
              <div class="hover-item" id="${item.contentId}" onclick="locateDetail(this)">
                <div class="recommend-img"><img src="${item.firstImage}" alt="" /></div>
                <div class="recommend-title"><c:out value="${item.title}" /></div>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="see-more-btn-div">
          <button class="see-more-btn" onclick="location.href='${root}/list?type=festival'">See More</button>
        </div>
      </div>

      <div class="recommend-container">
        <div class="main-text">문화시설</div>
        <div class="recommend-list">
          <div id="recommendList" class="recommend-row recommend-row-1">
            <!-- 문화시설 리스트 -->

            <c:forEach var="item" items="${curture}">
              <div class="hover-item" id="${item.contentId}" onclick="locateDetail(this)">
                <div class="recommend-img"><img src="${item.firstImage}" alt="" /></div>
                <div class="recommend-title"><c:out value="${item.title}" /></div>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="see-more-btn-div">
          <button class="see-more-btn" onclick="location.href='${root}/list?type=culture'">See More</button>
        </div>
      </div>

      <div class="recommend-container">
        <div class="main-text">레포츠</div>
        <div class="recommend-list">
          <div id="recommendList" class="recommend-row recommend-row-1">
            <!-- 레포츠 리스트 -->

            <c:forEach var="item" items="${leisureSports}">
              <div class="hover-item" id="${item.contentId}" onclick="locateDetail(this)">
                <div class="recommend-img"><img src="${item.firstImage}" alt="" /></div>
                <div class="recommend-title"><c:out value="${item.title}" /></div>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="see-more-btn-div">
          <button class="see-more-btn" onclick="location.href='${root}/list?type=leisureSports'">See More</button>
        </div>
      </div>
    </main>
    <!-- 메인 끝 -->

    <%@ include file="/include/footer.jsp" %>

    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script src="${JS_PATH}/index.js"></script>
  </body>
</html>
