package cn.blmdz.aop.create;

public class Waiter {

	public void greetTo(String name) {
		System.out.println(this.getClass().getName() + "#" + "greetTo: " + name);
	}
	
	public void serveTo(String name) {
		System.out.println(this.getClass().getName() + "#" + "serveTo: " + name);
	}
}
