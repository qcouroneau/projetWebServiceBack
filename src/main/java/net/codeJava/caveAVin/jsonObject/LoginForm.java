package net.codeJava.caveAVin.jsonObject;

public class LoginForm {
	private String nom;
	private String prenom;
	private String login;
	private String password;
	private String email;
	 	 
	 public LoginForm(String _nom, String _prenom, String _login, String _password, String _email) {
		this.login = _login;
		this.nom = _nom;
		this.prenom = _prenom;
		this.password = _password;
		this.email = _email;
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

	public String getEmail() {
		return email;
	}
}
