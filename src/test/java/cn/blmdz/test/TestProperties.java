package cn.blmdz.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class TestProperties {
	public static void main(String args[]) throws IOException {
//		testProperties();
		test1();
	}

	public static void testProperties() throws IOException {
		System.out.println("------------testProperties-------------");
		// 将properties文件加载到输入字节流中
		InputStream is = new FileInputStream("E:\\ttt.properties");
		// 创建一个Properties容器
		Properties prop = new Properties();
		// 从流中加载properties文件信息
		prop.load(is);
		// 循环输出配置信息
		for (Object key : prop.keySet()) {
			System.out.println(key + "=" + prop.get(key));
		}

		// 定义一个输出流
		OutputStream os1 = new FileOutputStream("E:\\ttt.xml");
		OutputStream os2 = new FileOutputStream("E:\\ttt.properties");

		// 从Properties对象导出导出到xml
		prop.storeToXML(os1, "我从properties导出的XML配置文件");
		// 从Properties对象导出properties文件
		prop.store(os2, "我从properties导出的XML配置文件");

		is.close();
		os1.close();
		os2.close();

		// 从xml加载配置信息，填充Properties容器
		prop.loadFromXML(new FileInputStream("E:\\ttt.xml"));
		// 循环输出配置信息
		System.out.println("我从导出的xml加载配置文件信息！");
		for (Object key : prop.keySet()) {
			System.out.println(key + "=" + prop.get(key));
		}

		// 修改Properties对象，并持久化到一个文件
		prop.put("呵呵呵", "嘎嘎嘎");
		OutputStream os3 = new FileOutputStream("E:\\ttt1.xml");
		prop.storeToXML(os3, "我从properties导出的XML配置文件");
		os3.close();
	}

	/**
	 * 以相对路径方式加载properties文件
	 * 
	 * @throws IOException
	 */
	public static void test1() throws IOException {
		System.out.println("------------test1-------------");
		Properties p = new Properties();
		p.load(TestProperties.class.getResourceAsStream("/ttt.properties"));
		for (Object key : p.keySet()) {
			System.out.println(key + "=" + p.get(key));
		}
	}
}