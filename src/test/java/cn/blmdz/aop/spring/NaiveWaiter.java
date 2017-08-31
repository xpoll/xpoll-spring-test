package cn.blmdz.aop.spring;

import java.sql.SQLException;

public class NaiveWaiter implements Waiter {

	@Override
	public void greetTo(String name) {
		System.out.println("greet to " + name + " ...");
	}

	@Override
	public void serveTo(String name) {
		System.out.println("serveing " + name + " ...");
	}

	@Override
	public void create(String name) {
		throw new RuntimeException("运行异常.");
	}

	@Override
	public void doit(String name) throws Exception {
		throw new SQLException("sql异常.");
	}

}
