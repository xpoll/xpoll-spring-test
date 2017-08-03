package cn.blmdz.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.blmdz.test.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-bean.xml")
public class JunitSpringTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void test() {
		System.out.println(userService.hasMatchUser("admin", "123456"));
		System.out.println(userService.hasMatchUser("admin", "121212"));
		assertTrue(userService.hasMatchUser("admin", "123456"));
		assertTrue(userService.hasMatchUser("admin", "121212"));
	}

}
