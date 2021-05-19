package net.codeJava.caveAVin.jsonObject;

public class LoginForm {
	 private String login;
	 private String password;
	 	 
	 public LoginForm(String _login, String _password) {
		this.login = _login;
		this.password = _password;
	}
	 
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
}
