package cn.ys.javashop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.ys.javashop.entity.Orders;





public class DBUtils {
	private final static String URL = "jdbc:mysql://localhost:3306/javashop";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	// ��ȡ���Ӷ���
	public static Connection getConnection() {
		
		 // ע������
		
		Connection connection =null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch ( SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		 	
		return connection;
	}
	
	// ��ɾ����
	// ��ѯ
	public static ResultSet getResultSet(String sql, Object... objects ) {
		try {
			ps = getConnection().prepareStatement(sql);
			for (int i =0; i<objects.length; i++ ){
				ps.setObject(i+1, objects[i]);
			}
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
			
	}

	// �޸�
	public static boolean update(String sql, Object...objects ) {
		try {
			ps = getConnection().prepareStatement(sql);
			for( int i=0; i<objects.length; i++){
				ps.setObject(i+1, objects[i]);
			}
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public static ResultSet selectOrders(String sql, Object...objects ) {
		try {
			ps = getConnection().prepareStatement(sql);
			for (int i =0; i<objects.length; i++ ){
				ps.setObject(i+1, objects[i]);
			}
			return ps.executeQuery();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	
	
	
}
