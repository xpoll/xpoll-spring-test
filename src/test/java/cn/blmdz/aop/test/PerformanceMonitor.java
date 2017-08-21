package cn.blmdz.aop.test;

public class PerformanceMonitor {

	private static ThreadLocal<MethodPerformance> performanceRecord = new ThreadLocal<>();
	
	public static void start(String method) {
		System.out.println("begin monitor ...");
		performanceRecord.set(new MethodPerformance(method));
	}
	
	public static void end() {
		System.out.println("end monitor ...");
		performanceRecord.get().printPerformance();
	}
}
