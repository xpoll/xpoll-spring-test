package cn.blmdz.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HowController {

	@RequestMapping(value="/how")
	public String how(String name) {
		
		return "how " + name;
	}
}
