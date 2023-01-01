package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Book;

public class BookDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager
				.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test", "test1234");
		return con;
	}
	
	//상품등록
	public int insert(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int result = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO BOOK_LIST VALUES(BOOK_SEQ.NEXTVAL, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, writer);
			ps.setInt(3, price);
			ps.setInt(4, stock);
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			if (ps != null) {
				try { ps.close(); } catch (Exception e) { }
				ps = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (Exception e) { }
				conn = null;
			}
		}
		
		return result;
	}
	
	//상품조회
	public String listAll(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		 try {
			 conn = getConnection();
			 String sql = "SELECT * FROM BOOK_LIST ORDER BY BOOK_NO ASC";
			 
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setNo(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setWriter(rs.getString(3));
				book.setPrice(rs.getInt(4));
				book.setStock(rs.getInt(5));
				
				bookList.add(book);
			}		
			request.setAttribute("bookList", bookList);
	 	} catch (Exception e) {
			e.printStackTrace();
	 	} finally {
			if (rs != null) {
				try { rs.close(); } catch (Exception e) { }
				rs = null;
			}
			if (ps != null) {
				try { ps.close(); } catch (Exception e) { }
				ps = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (Exception e) { }
				conn = null;
			}
		}
		 
		 return "list.jsp";
	}
	
	//상품삭제하기
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		
		try {
			conn = getConnection();
			int no = Integer.parseInt(request.getParameter("no"));
			String sql = "DELETE FROM BOOK_LIST WHERE BOOK_NO = " + no;
			
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(); //쿼리문 실행	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close(); } catch (Exception e) { }
				ps = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (Exception e) { }
				conn = null;
			}
		}
		
		return result;
	}
	
	//상품수정 전 화면 보여주기
	public String modify(HttpServletRequest request, HttpServletResponse response) {
		try {
			conn = getConnection();
			int no = Integer.parseInt(request.getParameter("no"));
			
			String sql = "SELECT TITLE, WRITER, PRICE, STOCK FROM BOOK_LIST WHERE BOOK_NO = " + no;
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			Book book = new Book();
			
			if(rs.next()) {
				book.setTitle(rs.getString(1));
				book.setWriter(rs.getString(2));
				book.setPrice(rs.getInt(3));
				book.setStock(rs.getInt(4));
			}
			
			request.setAttribute("book", book);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (Exception e) { }
				rs = null;
			}
			if (ps != null) {
				try { ps.close(); } catch (Exception e) { }
				ps = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (Exception e) { }
				conn = null;
			}
		}
		
		return "modify.jsp";
	}
	
	//상품수정
	public int update(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "UPDATE BOOK_LIST SET WRITER = ?, "
					+ "PRICE = ?, STOCK = ? "
					+ "WHERE TITLE = ? ";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1,writer);
			ps.setInt(2, price);
			ps.setInt(3, stock);
			ps.setString(4, title);
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close(); } catch (Exception e) { }
				ps = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (Exception e) { }
				conn = null;
			}
		}
		
		return result;
	}
	
	//구매 도서 리스트
	public String buy(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		 try {
			 conn = getConnection();
			 String sql = "SELECT * FROM BOOK_LIST";
			 
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setNo(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setWriter(rs.getString(3));
				book.setPrice(rs.getInt(4));

				bookList.add(book);
			}
			
			request.setAttribute("bookList", bookList);
	 	} catch (Exception e) {
			e.printStackTrace();
	 	} finally {
			if (rs != null) {
				try { rs.close(); } catch (Exception e) { }
				rs = null;
			}
			if (ps != null) {
				try { ps.close(); } catch (Exception e) { }
				ps = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (Exception e) { }
				conn = null;
			}
		}
		 
		 return "buy.jsp";
	}
	
	public String cart(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		int price = Integer.parseInt(request.getParameter("price"));
		int count = Integer.parseInt(request.getParameter("count"));
		
		String resultMsg = "OK";
		
		try {
			conn = getConnection();

			String sql = "SELECT  BOOK_NO, STOCK FROM BOOK_LIST WHERE BOOK_NO=" + no;
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			Book book = new Book();
			while(rs.next()) {
				book.setStock(rs.getInt(2));	
			}
			System.out.println("재고=" + book.getStock());
			System.out.println("주문=" +count);
			System.out.println("확인=" + sql);
			
			int cnt = book.getStock() - count;
			
			if(cnt < 0 ) {
				//재고수량이 주문수량보다 작으면 구매불가
				System.out.println("주문구매불가!");
				resultMsg = "재고수량이 주문수량보다 적습니다";
			} else {
				System.out.println("주문가능!");
				String sql2 = "INSERT INTO BOOK_ORDER VALUES(ORDER_SEQ.NEXTVAL,?,?, ?, ?, ?)";
				ps = conn.prepareStatement(sql2);
				
				
				int idx = 1;
				
				ps.setInt(idx++, no);
				ps.setString(idx++, title);
				ps.setString(idx++, writer);
				ps.setInt(idx++, price);
				ps.setInt(idx++, count);
				
				int result = ps.executeUpdate();
				System.out.println("구매개수=" + result);
				
				
				//재고수량 수정
				if(result > 0) {
					String sql3 = "update book_list set stock = ? where book_no = ? ";
					
					ps = conn.prepareStatement(sql3);
					idx = 1;
					
					ps.setInt(idx++, cnt);
					ps.setInt(idx++, no);
					
					result = ps.executeUpdate();
				} else {
					//구매가 없기때문에 재고수량을 수정하지 않는다.
					resultMsg = "구매 저장에 실패했습니다";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg = "구매 처리 중 오류가 발생했습니다";
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (Exception e) { }
				rs = null;
			}
			if (ps != null) {
				try { ps.close(); } catch (Exception e) { }
				ps = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (Exception e) { }
				conn = null;
			}
		}
		
		return resultMsg;
	}
	
	public String orderList(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Book> orderList = new ArrayList<Book>();
		
		 try {
			 conn = getConnection();
			 String sql = "SELECT T1.BOOK_NO, T1.TITLE, T1.WRITER, T1.PRICE, SUM(T1.COUNT) AS TOT_COUNT, T1.PRICE * SUM(T1.COUNT) AS TOT_PRICE "
			 		+ "FROM BOOK_ORDER T1 "
			 		+ "GROUP BY T1.BOOK_NO, T1.TITLE, T1.WRITER, T1.PRICE "
			 		+ "ORDER BY BOOK_NO ASC";
			 
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setNo(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setWriter(rs.getString(3));
				book.setPrice(rs.getInt(4));
				book.setTot_count(rs.getInt(5));
				book.setTot_price(rs.getInt(6));

				orderList.add(book);
			}
			
			request.setAttribute("orderList", orderList);
	 	} catch (Exception e) {
			e.printStackTrace();
	 	} finally {
			if (rs != null) {
				try { rs.close(); } catch (Exception e) { }
				rs = null;
			}
			if (ps != null) {
				try { ps.close(); } catch (Exception e) { }
				ps = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (Exception e) { }
				conn = null;
			}
		}
		 
		 return "order.jsp";
	}
}