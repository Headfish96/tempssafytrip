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
        <div class="board_list_wrap">
            <div class="board_list">
                <div class="top">
                    <div class="num">번호</div>
                    <div class="title">제목</div>
                    <div class="writer">글쓴이</div>
                    <div class="date">작성일</div>
                    <div class="count">조회</div>
                </div>
                <%-- 게시글의 개수만큼 반복 출력 --%>
				<c:forEach var="item" items="${boards}">
				<div>
                    <div class="num"><c:out value="${item.article_no}"></c:out></div>
                    <div class="title"><a href="${root}/board?action=detail&article_no=${item.article_no}"><c:out value="${item.subject}"></c:out></a></div>
                    <div class="writer"><c:out value="${item.user_id}"></c:out></div>
                    <div class="date"><c:out value="${item.register_time}"></c:out></div>
                    <div class="count"><c:out value="${item.hit}"></c:out></div>
                </div>
                </c:forEach>
                <c:if test="${empty list}">
				<tr>
					<td colspan='4'>입력된 게시물이 없습니다.</td>
				</tr>
				</c:if>
            </div>
            <!-- 
            <div class="board_page">
                <a href="#" class="bt first"><<</a>
                <a href="#" class="bt prev"><</a>
                <a href="#" class="num on">1</a>
                <a href="#" class="num">2</a>
                <a href="#" class="num">3</a>
                <a href="#" class="num">4</a>
                <a href="#" class="num">5</a>
                <a href="#" class="bt next">></a>
                <a href="#" class="bt last">>></a>
            </div>
            -->
            
            
            <c:if test="${pageResult.count != 0}">
			<nav>
			  <ul class="pagination">
			    <li class="<c:if test="${pageResult.prev eq false}">disabled</c:if>">
			      <a href="<c:if test="${pageResult.prev eq true}">${root}/board?action=list&pageNo=${pageResult.beginPage - 1}&listSize=${pageResult.listSize}</c:if>" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>

				<c:forEach var="i" begin="${pageResult.beginPage}" end="${pageResult.endPage}">
				    <c:choose>
				    	<c:when test="${i eq pageResult.pageNo}">
						    <li class="active"><a href="#1">${i}</a></li>
				    	</c:when>
				    	<c:otherwise>
						    <li><a href="${root}/board?action=list&pageNo=${i}&listSize=${pageResult.listSize}">${i}</a></li>
				    	</c:otherwise>
				    </c:choose>
				</c:forEach>
				
			    <li class="<c:if test="${pageResult.next eq false}">disabled</c:if>">
			      <a href="<c:if test="${pageResult.next eq true}">${root}/board?action=list&pageNo=${pageResult.endPage + 1}&listSize=${pageResult.listSize}</c:if>" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>	
			  </ul>
			</nav>
			</c:if>
          
            
            <div class="bt_wrap">
                <a href="${root}/board?action=registForm" class="on">등록</a>
                <!--<a href="#">수정</a>-->
            </div>
        </div>
    </div>
    </main>
    <!-- 메인 끝 -->

<%@ include file="/include/footer.jsp" %>