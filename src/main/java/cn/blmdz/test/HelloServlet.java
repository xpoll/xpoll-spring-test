package cn.blmdz.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		request.setAttribute("sb", request.getParameter("name"));
		System.out.println("doGet..");
		System.out.println(request.getParameter("name"));
		request.getRequestDispatcher("hello.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost..");
		doGet(request, response);
	}
	
	public void init() {
		System.out.println("init..");
	}
	
	public void destory() {
		System.out.println("destory..");
	}

}
