package cn.blmdz.test;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 通过控制filter的文件名 来控制加载顺序
 * 
 * @author yongzongyang
 */
@WebFilter("/*")
public class EnFilter implements Filter {

	private int count = 0;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter.init...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获取客户端的区域设置
		Locale locale = request.getLocale();
		String language = locale.getLanguage();
		String country = locale.getCountry();

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("Filter.doFilter..." + language + ":" + country + ":" + count);
		count++;
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("Filter.destroy...");
	}

}
