package cn.blmdz.aop.spring;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import cn.blmdz.aop.test.PerformanceMonitor;

public class ControllablePerformanceMonitor extends DelegatingIntroductionInterceptor implements Monitorable {

	private static final long serialVersionUID = 1L;
	
	private ThreadLocal<Boolean> monitorStatusMap = new ThreadLocal<>();
	
	@Override
	public void setMonitorActive(boolean active) {
		monitorStatusMap.set(active);
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		Object obj = null;
		if (monitorStatusMap.get() != null && monitorStatusMap.get()) {
			PerformanceMonitor.start(mi.getClass().getName() + "#" + mi.getMethod().getName());
			obj = super.invoke(mi);
			PerformanceMonitor.end();
		} else {
			obj = super.invoke(mi);
		}
		return obj;
	}
	
}
