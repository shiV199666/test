package com.manish.javadev.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.manish.javadev.model.User;
import com.manish.javadev.validator.UserValidator;

@Controller
public class LoginController {

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/doLogin.htm", method = RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute("user") User user,
			BindingResult result) {
		ModelAndView model = null;
		String viewResult = "login";
		System.out.println("doLogin Called");
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			System.out.println("Error Count= " + result.getErrorCount());
			model = new ModelAndView(viewResult, "user", user);
			return model;
		}
		
		String un = user.getUsername();
		String pw = user.getPassword();

		// Note:
		// If user name and password are same the assumption for login done 
		// You can implement DAO and use in your project
		if (un.equals(pw)) {
			viewResult = "home";
		}
		
		// Creating a ModelAndView Object and adding jsp page and model data
		model = new ModelAndView(viewResult, "user", user);
		return model;
	}

	@RequestMapping("/login.htm")
	public String showLogin(Map model) {
		User user = new User();
		user.setUsername("Divya");
		model.put("user", user);
		return "login";

	}
}

