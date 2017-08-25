package cn.blmdz.aop.spring;

public class NaiveWaiter implements Waiter {

	@Override
	public void greetTo(String name) {
		System.out.println("greet to " + name + " ...");
	}

	@Override
	public void serveTo(String name) {
		System.out.println("serveing " + name + " ...");
	}

}
