<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
	<link rel="stylesheet" href="${CSS_PATH}/listBoard.css" />
</head>
<body>
	<%@ include file="/include/header.jsp" %>
	<!-- 메인 시작 -  -->
    <main>
	    <div class="board_wrap">
	      <div class="board_title">
	        <strong>게시판</strong>
	        <p>여행 정보를 공유해보세요.</p>
	      </div>
	      <form method ="post" action ="${root}/board?action=regist">
	        <div class="board_write_wrap">
	          <div class="board_write">
	            <div class="title">
	              <dl>
	                <dt>제목</dt>
	                <dd><input type="text" name="subject" placeholder="제목 입력" value = "abc"/></dd>
	              </dl>
	            </div>
	            <div class="info">
	              <dl>
	                <dt>글쓴이</dt>
	                <dd><input type="text" name="user_id" placeholder="글쓴이 입력" /></dd>
	              </dl>
	              <dl>
	                <dt>비밀번호</dt>
	                <dd><input type="password" placeholder="비밀번호 입력" /></dd>
	              </dl>
	            </div>
	            <div class="cont">
	              <textarea name="content" placeholder="내용 입력"></textarea>
	            </div>
	          </div>
	          <div class="bt_wrap">
	          <button class="bt_regist">등록</button>
	            <a href="${root}/board?action=list">취소</a>
	          </div>
	        </div>
	      </form>
	    </div>
    </main>
    <!-- 메인 끝 -->
<%@ include file="/include/footer.jsp" %>