package cn.blmdz.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.blmdz.test.dao.LoginLogDao;
import cn.blmdz.test.dao.UserDao;
import cn.blmdz.test.domain.LoginLog;
import cn.blmdz.test.domain.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LoginLogDao loginLogDao;
	
	public boolean hasMatchUser(String username, String password) {
		int m = userDao.getMatchCount(username, password);
		return m > 0;
	}
	
	public User findUserByUserName(String username) {
		return userDao.findUserByUserName(username);
	}
	
	public void loginSuccess(User user) {
		user.setCredits(5 + user.getCredits());
		LoginLog log = new LoginLog();
		log.setIp(user.getLastIp());
		log.setUserId(user.getUserId());
		log.setLoginDate(user.getLastVisit());
		userDao.updateLoginLog(user);
		loginLogDao.insertLoginLog(log);
	}
	
}
