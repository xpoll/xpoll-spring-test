package cn.blmdz.aop.test;

public class MethodPerformance {

	private String method;
	
	private long begin;
	private long end;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public MethodPerformance(String method) {
		this.method = method;
		this.begin = System.currentTimeMillis();
	}
	

	public void printPerformance() {
		this.end = System.currentTimeMillis();
		System.out.println(this.method + "花费" + (end - begin) + "毫秒。");
	}
	
}
