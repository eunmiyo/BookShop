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
				<caption>주 문 내 역</caption>
				<tr class="bar">
					<th>도서명</th>
					<th>저자명</th>
					<th>금 액</th>
					<th>주문수량</th>
					<th>주문금액</th>
				</tr>
				<c:forEach var="book" items="${orderList}" varStatus="status">
					<tr>
						<td class="list">${book.title }</td>
						<td class="list">${book.writer }</td>
						<td class="list">${book.price }</td>
						<td class="list">${book.tot_count }</td>
						<td class="list">${book.tot_price }</td>
					</tr>
					<c:set var="tot_count" value="${tot_count + book.tot_count}"/>
					<c:set var="tot_price" value="${tot_price + book.tot_price}"/>
				</c:forEach>
				<tr class="total">
					<td class="list"></td>
					<td class="list">총계</td>
					<td class="list"></td>
					<td class="list">${tot_count }</td>
					<td class="list">${tot_price }</td>
				</tr>
		</table>
	</div>	
		
	<%@ include file="footer.jsp"%>
</body>
</html>