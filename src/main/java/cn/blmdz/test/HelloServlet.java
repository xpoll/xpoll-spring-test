package cn.blmdz.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *  <servlet>
 *	  <servlet-name>hello</servlet-name>
 *	  <servlet-class>cn.blmdz.s.HelloServlet</servlet-class>
 *  </servlet>
 *  <servlet-mapping>
 *  	<servlet-name>hello</servlet-name>
 *  	<url-pattern>/hello</url-pattern>
 *  </servlet-mapping>
 * <==>
 * @WebServlet("/hello")
 * 
 * @author yongzongyang
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HttpServlet.doGet...");
		Cookie cookie = new Cookie("key", "value");
		response.addCookie(cookie);
		request.setAttribute("sb", request.getParameter("name"));
		request.getRequestDispatcher("hello.jsp").forward(request, response);
		
//		String site = "http://www.baidu.com" ;
//		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
//		response.setHeader("Location", site); 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HttpServlet.doPost...");
		doGet(request, response);
	}
	
	public void init() {
		System.out.println("HttpServlet.init...");
	}
	
	public void destory() {
		System.out.println("HttpServlet.destory...");
	}

}
