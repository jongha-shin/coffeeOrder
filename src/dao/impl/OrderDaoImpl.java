package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;
import db.DBClose;
import db.DBConnection;

import dto.CoffeeDto;
import dto.OrderDto;
/*
CREATE TABLE COFFEE(
		ESPRESSO VARCHAR2(50) PRIMARY KEY,
		SIZE_SHORT NUMBER(4) NOT NULL,
		SIZE_TALL NUMBER(4) NOT NULL,
		SIZE_GRANDE NUMBER(4) NOT NULL
	)
	*/
public class OrderDaoImpl implements OrderDao {

	@Override
	// TODO 메뉴보기
	public List<CoffeeDto> showMenu() {
		String sql = " SELECT ESPRESSO, SIZE_SHORT, SIZE_TALL, SIZE_GRANDE "
				+ " FROM COFFEE ";
				
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<CoffeeDto> list = new ArrayList<>();
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();	//select문만 executeQuery
			
			while(rs.next()) {
				
				CoffeeDto dto = new CoffeeDto(rs.getString(1), // coffee name
										rs.getInt(2),	// short, 
										rs.getInt(3),	// tall, 
										rs.getInt(4)	// grande
										);
				
				list.add(dto);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// conn.rollback();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	@Override
	public int coffee_cost(String name, String size) {
		String sql = " FROM COFFEE "
				   + " WHERE ESPRESSO = ? ";
				
		if(size.equals("Short")) {
			sql =  " SELECT SIZE_SHORT "+ sql;
		}else if(size.equals("Tall")) {
			sql =  " SELECT SIZE_TALL "+ sql;
		}else {
			sql =  " SELECT SIZE_GRANDE "+ sql;
		}
				
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int coffee_cost = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			
			rs = psmt.executeQuery();	//select문만 executeQuery
			
			if(rs.next()) {
				
				coffee_cost = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// conn.rollback();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return coffee_cost;
	}

	@Override
	// TODO 주문 추가
	public boolean addOrder(OrderDto dto) {
		//public OrderDto(int orderID, String coffee, String id, Date orderDate, String size, int count, int totalCost)
		String sql = " INSERT INTO COFFEE_HISTORY(SEQ, COFFEE_NAME, ID, ORDER_DATE, COFFEE_SIZE, COUNT, TOTALCOST, DELNUM) "
				+ " VALUES( SEQ_COFFEE.NEXTVAL, ?, ?, SYSDATE, ?, ?, ?, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet  rs = null;
//		System.out.println("sql:" + sql);
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getCoffee());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getSize());
			psmt.setInt(4, dto.getCount());
			psmt.setInt(5, dto.getTotalCost());
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {		
			DBClose.close(psmt, conn, rs);			
		}		
		
		return count>0?true:false;
	}

	@Override
	// TODO 주문내역 보기
	public List<OrderDto> showHistory(String id) {
		String sql = " SELECT COFFEE_NAME, ORDER_DATE, COFFEE_SIZE, COUNT, TOTALCOST, DELNUM "
				+ " FROM COFFEE_HISTORY "
				+ " WHERE ID = ? ";
				
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<OrderDto> list = new ArrayList<>();
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();	//select문만 executeQuery
			
			while(rs.next()) {
				
				OrderDto dto = new OrderDto(rs.getString(1), 
										rs.getString(2),	 
										rs.getString(3),	 
										rs.getInt(4),	
										rs.getInt(5)
										);
				if(rs.getInt(6) == 0) {
					list.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// conn.rollback();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	@Override
	// TODO 삭제
	public boolean deleteHistory(String id) {
		//public OrderDto(int orderID, String coffee, String id, Date orderDate, String size, int count, int totalCost)
				String sql = " UPDATE COFFEE_HISTORY "
						+ " SET DELNUM = 1"
						+ " WHERE ID = ? ";
				
				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet  rs = null;
//				System.out.println("sql:" + sql);
				
				int count = 0;
				
				try {
					conn = DBConnection.getConnection();
					
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, id);
					count = psmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {		
					DBClose.close(psmt, conn, rs);			
				}		
				return count>0?true:false;
	}

}
