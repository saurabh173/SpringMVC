package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService ls ;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		System.out.println("Returning login");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleUserLogin(ModelMap model, @RequestParam String name,
			@RequestParam String password) {
		
		System.out.println("Inside Controller");
//		 ApplicationContext ac = new ClassPathXmlApplicationContext("src/main/webapp/WEB-INF/spring-mvc-servlet");
//		LoginService ls = (LoginService) ac.getBean("loginService");
		

		if (!ls.validateUser(name, password)) {
			model.put("errorMessage", "Invalid Credentials");
			System.out.println("Returning Login");
			return "login";
		}
		model.put("name", name);
		System.out.println("Returning Welcome");
		return "welcome";
	}
}
