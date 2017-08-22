package cn.blmdz.aop.test;

public class ForumServiceImpl3 implements ForumService {

	@Override
	public void removeTopic(int topicId) {
		System.out.println("3模拟删除Topic记录：" + topicId);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeForum(int forumId) {
		System.out.println("3模拟删除Forum记录：" + forumId);
		try {
			Thread.sleep(22);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
