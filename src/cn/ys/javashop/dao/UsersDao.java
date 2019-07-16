package cn.ys.javashop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.ys.javashop.entity.Users;
import cn.ys.javashop.util.DBUtils;

public class UsersDao {
	
	
	public Users login(String username, String password) {
		String sql = "select * from users where loginId = ? and loginPwd = ? ";
		ResultSet rs = DBUtils.getResultSet(sql, username, password);
		Users users = null;
		try {
			if(rs.next()){
				users = new Users();
				users.setId(rs.getInt("id"));
				users.setLoginId(rs.getString("loginId"));
				users.setLoginPwd(rs.getString("loginPwd"));
				users.setRealName(rs.getString("realName"));
				users.setScore(rs.getBigDecimal("score"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean updatePassword(int id, String newPassword ) {
		String sql = "update Users set loginPwd = ? where id = ?";
		return DBUtils.update(sql,newPassword , id);
	}
}
