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

function bookadd() {
  var book = $(".bar select").val();
  var cnt = $(".count").val();
  var test = book.split("/");

  console.log(test[0]);
  console.log(test[1]);
  console.log(test[2]);
  
  var tdbookname = "<td>" + test[0] + "</td>";
  var tdbookperson = "<td>" + test[1] + "</td>";
  var tdbookprice = "<td>" + test[2] + "</td>";
  var tdbookcnt = "<td>" + cnt + "</td>";
  var tddelbtn = "<td><button>삭제</button></td>";
  
 var tr = "<tr>" + tdbookname + tdbookperson + tdbookprice + tdbookcnt + tddelbtn + "</tr>";
 
 var table = $(".buyList");
 table.append(tr);
}

