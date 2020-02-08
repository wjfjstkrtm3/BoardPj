<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
</head>
<body>
	<div id="root">
		<head>
			<h1>게시판</h1>
		<style type="text/css">
			li {list-style: none; float: left; padding: 6px;}
		</style>
		</head>
		<hr/>
	<div>
		<%@include file="nav.jsp"%>
	</div>
	<hr/>
	
	<section id="container">
		<form method="post" action="/board/write"></form>
			<table>
				<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th></tr>
			<c:forEach items="${list}" var="list">
				<tr>
					<td>
					<a href="/board/readView?bno=${list.bno}">${list.bno}</a>
					</td>
					<td>${list.title}</td>
					<td>${list.writer}</td>
					<td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
			</table>
			<div>
  <ul>
    <c:if test="${pageMaker.prev}">
    	<li><a href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    	<li><a href="list${pageMaker.makeQuery(idx)}">${idx}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li><a href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
    </c:if> 
  </ul>
</div>
			
	
			
	</section>
	</div>
</body>
</html>