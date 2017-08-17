package cn.blmdz.local;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessTest {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:bean-servlet.xml");
		System.out.println(ac.getMessage("a", null, Locale.getDefault()));
		System.out.println(ac.getMessage("a", null, Locale.US));
		MessageSource ms1 = (MessageSource) ac.getBean("myResources1");
		System.out.println(ms1.getMessage("a", null, Locale.getDefault()));
		System.out.println(ms1.getMessage("a", null, Locale.US));
		MessageSource ms2 = (MessageSource) ac.getBean("myResources2");
		System.out.println(ms2.getMessage("a", null, Locale.getDefault()));
		System.out.println(ms2.getMessage("a", null, Locale.US));
//		Thread.sleep(10000);
//		System.out.println(ms2.getMessage("a", null, Locale.getDefault()));
//		System.out.println(ms2.getMessage("a", null, Locale.US));
	}
	
	public void test(){
		
		NumberFormat nfmt = NumberFormat.getCurrencyInstance(Locale.SIMPLIFIED_CHINESE);
		System.out.println(nfmt.format(1.23324));
		
		DateFormat dfmt = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH);
		System.out.println(dfmt.format(new Date()));
		
		String p1 = "{0}，你好！你于{1}在工商银行存入{2}元。";
		String p2 = "At {1,time,short} On {1,date,long}, {0} paid {2,number,currency}.";
		Object[] params = {"John", new GregorianCalendar().getTime(), 1.0E3};
		
		String msg1 = MessageFormat.format(p1, params);
		System.out.println(msg1);
		
		MessageFormat mf = new MessageFormat(p2, Locale.US);
		System.out.println(mf.format(params));

		ResourceBundle rb1 = ResourceBundle.getBundle("resource");
		ResourceBundle rb2 = ResourceBundle.getBundle("resource", Locale.CHINA);
		ResourceBundle rb3 = ResourceBundle.getBundle("resource", Locale.US);
		ResourceBundle rb4 = ResourceBundle.getBundle("resource", Locale.CANADA);
		System.out.println(rb1.getString("a"));
		System.out.println(rb2.getString("a"));
		System.out.println(rb3.getString("a"));
		System.out.println(rb4.getString("a"));
		
//		HierarchicalMessageSource
//		ReloadableResourceBundleMessageSource
//		ResourceBundleMessageSource
	}

}
