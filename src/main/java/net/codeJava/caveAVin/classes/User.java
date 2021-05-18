package net.codeJava.caveAVin.classes;

public class User {
	private String nom;
	private String prenom;
	private String login;
	private String password;
	private Cave cave;
	
	public User(String _nom, String _prenom, String _login, String _password) {
		this.nom = _nom;
		this.prenom = _prenom;
		this.login = _login;
		this.password = _password;
		this.cave = new Cave();
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCave(Cave cave) {
		this.cave = cave;
	}

	public Cave getCave() {
		return this.cave;
	}
}
