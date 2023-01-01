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
		
			<table class="booklist">
				<caption>도 서 목 록</caption>
				<tr class="bar">
					<th>번 호</th>
					<th>도서명</th>
					<th>저자명</th>
					<th>금 액</th>
					<th>재고수량</th>
					<th>수정/삭제</th>
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
				</tr>								
				</c:forEach>
			</table>
	
	<%@ include file="footer.jsp"%>
	
<script type="text/javascript" src="./script.js"></script>
</body>
</html>