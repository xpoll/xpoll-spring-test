package cn.blmdz.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.blmdz.test.domain.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int getMatchCount(String username, String password) {
		String sql = "select count(*) from t_user where user_name=? and password=?";
		return jdbcTemplate.queryForInt(sql, new Object[]{username, password});
	}
	
	public User findUserByUserName(String username) {
		
		String sql = "select * from t_user where user_name=?";
		final User user = new User();
		
		jdbcTemplate.query(sql, new Object[]{username}, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setCredits(rs.getInt("credits"));
				user.setLastVisit(rs.getDate("last_visit"));
				user.setLastIp(rs.getString("last_ip"));
			}
		});
		return user;
	}
	
	public void updateLoginLog(User user) {
		String sql = "update t_user set last_visit=?, last_ip=? where user_id=?";
		jdbcTemplate.update(sql, new Object[]{user.getLastVisit(), user.getLastIp(), user.getUserId()});
	}
}
