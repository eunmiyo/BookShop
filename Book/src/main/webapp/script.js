//장바구니 객체
var Cart = function () {
	this.no = 0; //책 번호
	this.title = "제목"; //제목
	this.writer = "작가"; //작가
	this.price = 0; //가격
	this.count = 0; //권수
};

var cart_list = []; //장바구니리스트
var buy_index_list = []; //구매인덱스리스트

function fn_submit() {
	var f = document.frm;
	
	if(f.title.value == "") {
		alert("도서명이 입력되지 않았습니다!")
		f.title.focus();
		return false;
	}
	
	if(f.writer.value == "") {
		alert("저자명이 입력되지 않았습니다!")
		f.writer.focus();
		return false;
	}
	
	if(f.price.value == "") {
		alert("금액이 입력되지 않았습니다!")
		f.price.focus();
		return false;
	}
	
	if(f.stock.value == "") {
		alert("재고수량이 입력되지 않았습니다!")
		f.stock.focus();
		return false;
	}
	
	f.submit();
}

function chkDelete(no) {
	const result = confirm("삭제하시겠습니까?");
	
	if(result){
			const url = location.origin; //'http://localhost:8090'
			location.href = url + "/Book/delete?no=" + no; //페이지이동
			
		} else {
			return false;
		}
}

//도서등록

function bookadd() {
	var book = $(".bar select").val();
	var cnt = $(".count").val();
	var test = book.split("/");
	
	var cart = new Cart();
	
	cart.no = test[0];
	cart.title = test[1];
	cart.writer = test[2];
	cart.price = test[3];
	cart.count = cnt;
	
	cart_list.push(cart); //장바구니 리스트 추가
	
	bookdisplay(); //장바구니 리스트 화면 표시
}

//도서주문

function bookdel(i) {
	cart_list.splice(i,1); //장바구니 리스트에서 해당 값 삭제
	
	bookdisplay(); //장바구니 리스트 화면 표시
}

function bookdisplay() {	
	var table = $(".buyList");
	table.empty(); //기존 표시된 장바구니 리스트 삭제
	
	//헤더 추가
	var header = "<tr>";
	header += "<th>도서번호</th>";
	header += "<th>도서명</th>";
	header += "<th>저자명</th>";
	header += "<th>금 액</th>";
	header += "<th>구매수량</th>";
	header += "<th>삭제</th>";
	header += "</tr>";
	
	table.append(header); //장바구니 리스트 화면 표시
	
	for(var i=0;i<cart_list.length; i++) {
		var cart = cart_list[i];
		
		var tdbookno = "<td class='list'>" + cart.no + "</td>";
		var tdbookname = "<td class='list'>" + cart.title + "</td>";
		var tdbookperson = "<td class='list'>" + cart.writer + "</td>";
		var tdbookprice = "<td class='list'>" + cart.price + "</td>";
		var tdbookcnt = "<td class='list'>" + cart.count + "</td>";
		var tddelbtn = "<td class='list'><button onclick='bookdel("+i+");'>삭제</button></td>";
		
		var tr = "<tr>" + tdbookno + tdbookname + tdbookperson + tdbookprice + tdbookcnt + tddelbtn + "</tr>";
		
		table.append(tr); //장바구니 리스트 화면 표시
	}
	
	//푸터 추가
	var footer = "<tr>";
	footer += "<td colspan='5'>";
	footer += "<button class='orderbtn' type='button' onclick='order();' >주문완료</button>";
	footer += "</td>";
	footer += "</tr>";
	
	table.append(footer); //장바구니 리스트 화면 표시
}

function init_buy() {
	bookdisplay();
}

function order() {
	try {
		buy_index_list = []; //구매인덱스리스트 초기화
		
		//주문내역 리스트 가져오기
		for(var i=0;i<cart_list.length; i++) {
			
			var cart = cart_list[i];
			//cart_list.splice(0,1); //(인덱스시작번호, 개수)만큼 지우기
			//cart_list.shift(); //첫번재 값 지우기
			//cart_list.pop(); //마지막 값 지우기
	
			//console.log("출력="+ i + "/" + cart_list.length);
			
			$.ajax({
		        type:"POST",
		        url:"/Book/cart",
		        async: false, //동기화
		        data : cart,
		        success: function(htmlStr){
		            if("OK" == htmlStr) {
						//console.log("DB 저장 성공");
		            	buy_index_list.push(i);
		            } else {
						//console.log("DB 저장 실패 1");
						console.log(htmlStr);
					}
		        },
		        error: function(xhr, status, error) {
		            //console.log("DB 저장 실패 2");
		            console.log(error);
		        }
		    });
		}
		
		alert("주문 총 "+cart_list.length+" 건 중 "+buy_index_list.length+" 건 완료되었습니다!");
		cartdel(); //장바구니지우기
	} catch (e) {
		console.log(e);
		alert("주문에 실패하였습니다");
		cartdel(); //장바구니지우기
	}
}

function cartdel() {
	//장바구니 삭제 시 splice 를 사용한다
	//중간에서 삭제할 경우 그 이후 인덱스 모두에 영향이 있다
	//따라서, 제일 마지막 인덱스 부터 거꾸로 삭제를 한다
	for (var i=buy_index_list.length;0<i; i--) {
		var index = i-1; //인덱스는 0부터 시작이라 length 값보다 1이 작다
		var del_i = buy_index_list[index]; //장바구니에서 삭제할 인덱스 번호 가져온다
		cart_list.splice(del_i, 1); //장바구니삭제
	}
	
	buy_index_list = []; //구매인덱스리스트 초기화
	bookdisplay();
}