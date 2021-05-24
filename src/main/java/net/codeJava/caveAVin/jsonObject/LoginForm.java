package net.codeJava.caveAVin.jsonObject;

public class LoginForm {
	private String nom;
	private String prenom;
	 private String login;
	 private String password;
	 	 
	 public LoginForm(String _nom, String _prenom, String _login, String _password) {
		this.login = _login;
		this.password = _password;
	}
	 	 
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
}
