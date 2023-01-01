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
		<table class="wap">
			<caption>도 서 주 문</caption>
			<tr class="bar">
				<td>
				<select name="select">
					<option></option>
				<c:forEach var="book" items="${bookList}" varStatus="status">
					<option value="${book.title} / ${book.writer} / ${book.price}">${book.title} / ${book.writer} / ${book.price}원</option>
				</c:forEach>
				</select>
				<b>수량 : </b>
				<input type="text" class="count" name="count">
				<button class="btn" type="button" onclick="bookadd();">도서구매</button>
				</td>
			</tr>
			
			<form method="get" action="order">
			<input type="hidden" name="plus" value="cartList">
				<table class="buyList">
					<tr>
						<th>도서명</th>
						<th>저자명</th>
						<th>금 액</th>
						<th>구매수량</th>
						<th>삭제</th>
					</tr>
					<button class="btn" type="submit" >주문완료</button>	
				</table>
			</form>	
		</table>		
	<%@ include file="footer.jsp"%>
	<script type="text/javascript" src="./jquery-3.6.3.js"></script>
	<script type="text/javascript" src="./script.js"></script>
</body>
</html>