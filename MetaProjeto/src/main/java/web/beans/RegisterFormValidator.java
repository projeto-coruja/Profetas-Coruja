package web.beans;

import general.UtilityClass;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegisterFormValidator implements Validator {

	private static final int MINIMUM_PASSWORD_LENGTH = 8;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterFormBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatPassword", "field.required");
		
		RegisterFormBean newUser = (RegisterFormBean) target;
		if(newUser.getNickname() != null && UtilityClass.notAlphaNum(newUser.getNickname())) {
			errors.rejectValue("nickname", "field.chars", "Caracteres especiais não são aceitos.");
		}
		if(newUser.getUsername() != null && !UtilityClass.validateEmail(newUser.getUsername())) {
			errors.rejectValue("username", "field.email", "Não é endereço de email válido.");
		}
		if(newUser.getPassword() != null && newUser.getPassword().length() < MINIMUM_PASSWORD_LENGTH) {
			errors.rejectValue("password", "field.min.length", "Tamanho da senha é inválido.");
		}
		if(newUser.getPassword() != null && newUser.getRepeatPassword() != null && newUser.getPassword().equals(newUser.getRepeatPassword())) {
			errors.rejectValue("repeatPassword", "field.equal", "Confirmação da senha inválida.");
		}

	}

}
