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
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<%@ include file="top.jsp"%>
		<form name = "frm" method="post" action="update">
			<table>
				<caption>도 서 수 정</caption>
				<tr>
					<th>도서명</th>
					<td><input type="text" name="title" value="${book.title }"></td>
				</tr>
				<tr>
					<th>저자명</th>
					<td><input type="text" name="writer" value="${book.writer }"></td>
				</tr>
				<tr>
					<th>금 액</th>
					<td><input type="text" name="price" value="${book.price }"></td>
				</tr>
				<tr>
					<th>재고수량</th>
					<td><input type="text" name="stock" value="${book.stock }"></td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="btn" type="submit"
							onclick="fn_submit(); return false;">도서등록</button>
						<button class="btn" type="button" onclick="location='list'">도서목록</button>
					</td>
				</tr>
			</table>
		</form>
	<%@ include file="footer.jsp"%>
<script type="text/javascript" src="./script.js"></script>
</body>
</html>