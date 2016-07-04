package com.manish.javadev.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.manish.javadev.model.User;

@SuppressWarnings("rawtypes")
public class UserValidator implements Validator {

	public boolean supports(Class cls) {
		return User.class.equals(cls);
	}

	public void validate(Object commond, Errors errs) {
		User user = (User) commond;
		if (user.getUsername() == null || user.getUsername().length() == 0) {
			errs.rejectValue("username", "err.username.required", null,
					"Username Required");
		}
		if (user.getPassword() == null || user.getPassword().length() == 0) {
			errs.rejectValue("password", "err.password.required", null,
					"Password Required");
		}
	}
}
