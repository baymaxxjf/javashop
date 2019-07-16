package cn.ys.javashop.util;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class DBUtils {
	private final static String URL = "jdbc:mysql://localhost:3306/javashop";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	
	// 获取连接对象
	public Connection getConnection() {
		
		 // 注册驱动
		 Class.forName(DRIVER);
		 
		 Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		 
		
		
		
		return ;
	}
	
	
}
