package cn.blmdz.aop.test;

public class ForumServiceImpl1 implements ForumService {

	@Override
	public void removeTopic(int topicId) {
		PerformanceMonitor.start("cn.blmdz.aop.ForumServiceImpl#removeTopic");
		System.out.println("1模拟删除Topic记录：" + topicId);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PerformanceMonitor.end();
	}

	@Override
	public void removeForum(int forumId) {
		PerformanceMonitor.start("cn.blmdz.aop.ForumServiceImpl#createForum");
		System.out.println("1模拟删除Forum记录：" + forumId);
		try {
			Thread.sleep(22);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PerformanceMonitor.end();
	}

}
