<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="top.jsp"%>
		
		<div class="container">
			<table class="booklist">
				<caption>도 서 목 록</caption>
				<tr class="bar">
					<th>번 호</th>
					<th>도서명</th>
					<th>저자명</th>
					<th>금 액</th>
					<th>재고수량</th>
					<th>수정/삭제</th>
					<th></th>
				</tr>		
				<c:forEach var="book" items="${bookList}" varStatus="status">
				<tr>
					<td class="list">${book.no }</td>
					<td class="list">${book.title }</td>
					<td class="list">${book.writer }</td>
					<td class="list">${book.price }</td>
					<td class="list">${book.stock }</td>
					<td class="list">
						<a href="modify?no=${book.no }">수정/</a>
						<a onclick="chkDelete(${book.no }); return false;">삭제</a>
					</td>
					<td class="list">
						<!-- likebtn(this) : this = 자기 자신(태그)객체를 매개변수로 사용 -->
						<img class="like" onclick="likebtn(this)" src="./img/heart.png"/>
					</td>			
				</tr>								
				</c:forEach>
			</table>
		</div>
	<%@ include file="footer.jsp"%>
	
<script type="text/javascript" src="./script.js"></script>
</body>
</html>