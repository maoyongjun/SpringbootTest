package org.foxconn.springbootStart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test1Controller {
	@RequestMapping("/file")
	public String test1(){
		return "test";
		
	}
}
