package com.customer.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.dto.Customer;

// DB와 연결하기 위한 클래스 (== dao)
// @Repository는 자동 스캔이 가능하다.
@Repository
public class CustomerRepository {

	// bean으로 설정한 dataSource에 달라붙는다.
	// 예전의 SuperDao와 같은 기능을 한다고 보면 됨
	@Autowired
	private DataSource dataSource;

	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet resultSet = null;

	// insert SQL 문장
	private static final String CUSTOMER_INSERT = "insert into customer( id, name, address, email ) values( id1.nextval, ?, ?, ? )";

	// selectAll SQL 문장
	private static final String CUSTOMER_SELECT_ALL = "select * from customer";

	// update SQL 문장
	private static final String CUSTOMER_UPDATE = "update customer set name=?, address=?, email=? where id=?";

	// selectById SQL 문장
	private static final String CUSTOMER_SELECT_BY_ID = "select * from customer where id=?";
	
	// delete SQL 문장
	private static final String CUSTOMER_DELETE = "delete customer where name = ?";

	// Customer객체가 service에서 오는걸로 생각하고 만듬.

	// controller <---> service <---> repository <---> DB 이렇게 연결되어있음
	public int insert(Customer customer) {

		int count = 0;

		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(CUSTOMER_INSERT);

			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getEmail());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return count;
	}

	public int update(Customer customer) {

		int count = 0;
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(CUSTOMER_UPDATE);

			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getEmail());
			pstmt.setInt(4, customer.getId());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return count;
	}
	
	public int delete(String name) {

		int count = 0;

		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(CUSTOMER_DELETE);
			pstmt.setString(1, name);

			count = pstmt.executeUpdate();
			
			System.out.println("지워진 갯수 : " + count);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return count;
	}
	
	public Customer selectById(int id) {

		Customer bean = new Customer();

		try {

			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(CUSTOMER_SELECT_BY_ID);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				bean.setId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setAddress(resultSet.getString("address"));
				bean.setEmail(resultSet.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return bean;
	}

	public List<Customer> selectAll() {
		System.out.println("repo selectall");
		List<Customer> lists = new ArrayList<Customer>();

		try {

			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(CUSTOMER_SELECT_ALL);
			resultSet = pstmt.executeQuery();
			
			
			while (resultSet.next()) {
				Customer bean = new Customer();
				bean.setId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setAddress(resultSet.getString("address"));
				bean.setEmail(resultSet.getString("email"));
				
				lists.add(bean);
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return lists;
	}
}
