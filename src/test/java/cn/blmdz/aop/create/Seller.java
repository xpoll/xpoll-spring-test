package cn.blmdz.aop.create;

public class Seller {

	public void greetTo(String name) {
		System.out.println(this.getClass().getName() + "#" + "greetTo: " + name);
	}
}
