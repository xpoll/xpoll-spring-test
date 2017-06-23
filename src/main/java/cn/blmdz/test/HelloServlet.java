package cn.blmdz.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		if (name == null || name == "") {
			System.out.println("HelloServlet:" + "hello servlet!");
			request.setAttribute("sb", "HelloServlet:" + "hello servlet!");
		} else {
			System.out.println("HelloServlet:" + name);
			request.setAttribute("sb", name);
		}
		request.getRequestDispatcher("hello.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() {
		System.out.println("HttpServlet.init...");
	}

	public void destory() {
		System.out.println("HttpServlet.destory...");
	}

}
