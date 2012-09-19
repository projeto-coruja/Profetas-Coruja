package br.unifesp.maritaca.business.account;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class AccountDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String key;
	
	private String group;
	
	//@Pattern(regexp = ConstantsBusiness.EMAIL_REG_EXP)
	private String email;
	
	@Size(min = 2, max = 20)
	private String firstName;
	
	@Size(max = 20)
	private String lastName;
	
	private String password;
	
	private String passwordConfirmation;
	
	private String captchaCode;
	
	private String currentPassword;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	@Override
	public String toString() {
		return "UserDTO [key=" + key + ", group=" + group + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", captchaCode=" + captchaCode
				+ ", currentPassword=" + currentPassword + "]";
	}
}