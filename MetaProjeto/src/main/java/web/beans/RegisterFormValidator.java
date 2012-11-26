package web.beans;

import general.UtilityClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import web.services.UserManagementService;

public class RegisterFormValidator implements Validator {

	@Autowired
	private UserManagementService serv;
	
	private static final int MINIMUM_PASSWORD_LENGTH = 8;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterFormBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "field.required", "Apelido em branco.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "Email em branco.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required","Senha em branco.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatPassword", "field.required", "Repita a senha.");
		
		RegisterFormBean newUser = (RegisterFormBean) target;
		if(newUser.getNickname() != null && UtilityClass.notAlphaNum(newUser.getNickname())) {
			errors.rejectValue("nickname", "field.chars", "Caracteres especiais não são aceitos.");
		}
		if(newUser.getUsername() != null && !UtilityClass.validateEmail(newUser.getUsername())) {
			if(UtilityClass.validateEmail(newUser.getUsername()))
				errors.rejectValue("username", "field.email", "Não é endereço de email válido.");
			else if(serv.existingUser(newUser.getUsername()))
				errors.rejectValue("nickname", "field.chars", "Endereço já é utilizado.");
		}
		if(newUser.getPassword() != null && newUser.getPassword().length() < MINIMUM_PASSWORD_LENGTH) {
			errors.rejectValue("password", "field.min.length", "Tamanho da senha é inválido.");
		}
		if(newUser.getPassword() != null && newUser.getRepeatPassword() != null && newUser.getPassword().equals(newUser.getRepeatPassword())) {
			errors.rejectValue("repeatPassword", "field.equal", "Confirmação da senha inválida.");
		}

	}

}
