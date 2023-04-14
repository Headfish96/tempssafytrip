<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- 헤더 시작 - 로고이미지, 검색창, 로그인, 회원가입 -->
    <header>
      <div class="top-header">
        <div class="logo">
          <a href="${root}/home"><img src="${IMG_PATH}/ssafy_logo.png" alt="" /></a>
        </div>
        <div class="title">SSAFY TRIP</div>
        <div class="top-right-header">
        <c:if test="${not empty user}">
          <div class="info-box">
            <div><a href="${root}/member?action=logout">로그아웃</a></div>
            <div><a href="${root}/member?action=modifyForm">회원정보수정</a></div>
          </div>   
        </c:if>
        <c:if test="${empty user}">
          <div class="info-box">
            <div><a href="${root}/member?action=loginForm">로그인</a></div>
            <div><a href="${root}/member?action=signupForm">회원가입</a></div>
          </div>        	
        </c:if>
          <div class="search-box">
            <input class="search-input" type="text" placeholder="여행지를 검색하세요" />
          </div>
        </div>
      </div>
      <nav class="nav">
        <!-- 네비게이션 바 - 지역 선택 -->
        <ul class="nav-list">
          <li><a href="${root}/list?type=popular">인기여행지</a></li>
          <li><a href="${root}/list?type=festival">축제</a></li>
          <li><a href="${root}/list?type=culture">문화시설</a></li>
          <li><a href="${root}/list?type=leisureSports">레포츠</a></li>
          <li><a href="${root}/search">관광지조회</a></li>
          <li><a href="${root}/board?action=list">정보공유게시판</a></li>
        </ul>
      </nav>
    </header>
    <!-- 헤더 끝 -->