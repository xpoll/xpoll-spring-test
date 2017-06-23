package cn.blmdz.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HiAction implements Controller {

	private String hi;
	private String page;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, String> model = new HashMap<String, String>();

		String name = request.getParameter("name");
		if (name == null || name == "") {
			System.out.println("HiAction:" + hi);
			model.put("sb", hi);
		} else {
			System.out.println("HiAction:" + name);
			model.put("sb", name);
		}
		
		return new ModelAndView(getPage(), model);
	}

	public String getHi() {
		return hi;
	}

	public void setHi(String hi) {
		this.hi = hi;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
