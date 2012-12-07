package web.beans;

/**
 * A POJO (plain old Java object) for storing the register form values.
 * 
 * @author Daniel Gracia
 * @since Milestone 1
 *
 */
public class RegisterFormBean {

	private String nickname;
	
	private String username;
	
	private String password;
	
	private String repeatPassword;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	
	
}
