package cn.blmdz.aop.spring;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class ControllablePerformanceMonitor extends DelegatingIntroductionInterceptor implements Monitorable {

	private static final long serialVersionUID = 1L;
	
	private ThreadLocal<Boolean> monitorStatusMap = new ThreadLocal<>();
	
	@Override
	public void setMonitorActive(boolean active) {
		monitorStatusMap.set(active);
	}
}
