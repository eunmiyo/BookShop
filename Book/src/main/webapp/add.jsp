<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="top.jsp"%>
	
		<div class="container">
			<form name = "frm" method="post" action="insert">
				<table>
					<caption>도 서 등 록</caption>	
					<tr>
						<th>도서명</th>
						<td><input type="text" name="title"></td>
					</tr>
					<tr>
						<th>저자명</th>
						<td><input type="text" name="writer"></td>
					</tr>
					<tr>
						<th>금 액</th>
						<td><input type="text" name="price"></td>
					</tr>
					<tr>
						<th>재고수량</th>
						<td><input type="text" name="stock"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit"
								onclick="fn_submit(); return false;">도서등록</button>
							<button class="btn" type="reset">취소</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	
	<%@ include file="footer.jsp"%>
<script type="text/javascript" src="./script.js"></script>
</body>
</html>