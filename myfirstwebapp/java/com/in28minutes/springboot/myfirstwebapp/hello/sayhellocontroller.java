package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController
@Controller
public class sayhellocontroller {

	@RequestMapping("say-hello")
	@ResponseBody
	public String sayhello() {

		return "Hello! what are you learning?";

	}

	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayhellohtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>my first html page-changed</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("my first html page with body");
		sb.append("</body>");
		sb.append("</html>");

		return sb.toString();

	}

	@RequestMapping("say-hello-jsp")

	public String sayhellojsp() {

		return "sayHello";

	}

}
