package cn.blmdz.aop.spring;

public interface Waiter {

	void greetTo(String name);
	
	void serveTo(String name);
	
	void create(String name);
	
	void doit(String name) throws Exception;
}
