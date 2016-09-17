package com.tasxxz.myapp.jdbc.demo;

import com.tasxxz.myapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDemo {

	public static void insert() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into student(name,age,address) values(?,?,?)");
			final int batchSize = 1000;// 考虑批量大小为1000，每1000个查询语句为一批插入提交
			int count = 0;
			for (int i = 0; i < 150000; i++) {
				ps.setString(1, "name"+i);
				ps.setInt(2, 20+i);
				ps.setString(3, "地址"+i);
				
				ps.addBatch();
				
				if(++count % batchSize == 0) {
					ps.executeBatch();
					conn.commit();
			    }
				
			}
			ps.executeBatch();
			conn.commit();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			JdbcUtil.close(conn, ps);
		}
	}
	public static void query() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement("select * from student where address = ?");
			ps.setString(1, "地址23");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				if (null!=rs) {
					rs.close();
					rs=null;
				}
				if (null!=ps) {
					ps.close();
					ps=null;
				}
				if (null!=conn) {
					conn.close();
					conn=null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
//		insert();
		query();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
