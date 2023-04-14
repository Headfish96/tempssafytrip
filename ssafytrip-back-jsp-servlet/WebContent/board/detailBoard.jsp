<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
	<link rel="stylesheet" href="${CSS_PATH}/detailBoard.css" />
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
        <div class="board_view_wrap">
            <div class="board_view">
                <div class="title">${board.subject}</div>
                <div class="info">
                    <dl>
                        <dt>번호</dt>
                        <dd>${board.article_no}</dd>
                    </dl>
                    <dl>
                        <dt>글쓴이</dt>
                        <dd>${board.user_id}</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd>${board.register_time}</dd>
                    </dl>
                    <dl>
                        <dt>조회</dt>
                        <dd>${board.hit}</dd>
                    </dl>
                </div>
                <div class="cont">${board.content}</div>
            </div>
            <div class="bt_wrap">
                <a href="${root}/board?action=list">목록</a>
                <a href="${root}/board?action=delete&article_no=${board.article_no}">삭제</a>
                <a href="${root}/board?action=editForm&article_no=${board.article_no}">수정</a>
            </div>
        </div>
    </div>
    </div>
    </main>
    <!-- 메인 끝 -->
<%@ include file="/include/footer.jsp" %>