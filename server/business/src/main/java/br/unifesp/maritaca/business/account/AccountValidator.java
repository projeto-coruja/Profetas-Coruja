package br.unifesp.maritaca.business.account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.unifesp.maritaca.business.util.ConstantsBusiness;


@Component
public class AccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AccountDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("oi val");
		AccountDTO accountDTO = (AccountDTO) target;
		
		String firstname	= accountDTO.getFirstName();
		String lastname 	= accountDTO.getLastName();
		String email 		= accountDTO.getEmail();
		String password 	= accountDTO.getPassword();
		String passwordConf	= accountDTO.getPasswordConfirmation();
		
		if(firstname.length() <= 3 || firstname.length() >= 20) {
			errors.rejectValue("firstName", "error_user_firstname_size");
		}
		
		if(lastname.length() <= 3 || lastname.length() >= 20) {
			errors.rejectValue("lastName", "error_user_lastname_size");
		}
		
		Pattern pattern = Pattern.compile(ConstantsBusiness.EMAIL_REG_EXP);
		Matcher matcher = pattern.matcher(email);
		boolean matches = matcher.matches();
		if(!matches) {
			errors.rejectValue("email", "error_user_email_invalid");
		}
		
		if(!password.equals(passwordConf)) {
			errors.rejectValue("passwordConfirmation", "error_user_password_dont_match");
		}
	}
}