package cn.blmdz.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.blmdz.test.domain.LoginLog;

@Repository
public class LoginLogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertLoginLog(LoginLog loginLog) {
		String sql = "insert into t_login_log(user_id, ip, login_datetime) values(?, ?, ?)";
		jdbcTemplate.update(sql, new Object[]{loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate()});
	}
}
