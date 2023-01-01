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
		<table>
				<caption>주 문 내 역</caption>
				<tr class="bar">
					<th>도서명</th>
					<th>저자명</th>
					<th>금 액</th>
					<th>주문수량</th>
				</tr>
				<c:forEach var="book" items="${orderList}" varStatus="status">
					<tr>
						<td>${book.title }</td>
						<td>${book.writer }</td>
						<td>${book.price }</td>
						<td>${book.count }</td>
					</tr>
				</c:forEach>
				<tr>
					<th>총계</th>
					<th>총 주문수량</th>
					<th>총 주문금액</th>
				</tr>
					
		</table>
	<%@ include file="footer.jsp"%>
</body>
</html>