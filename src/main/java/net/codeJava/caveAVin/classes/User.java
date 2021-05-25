package net.codeJava.caveAVin.classes;

import net.codeJava.caveAVin.jsonObject.UserForm;

public class User {
	private String nom;
	private String prenom;
	private String login;
	private String password;
	private String email;
	private Cave cave;
	
	public User(String _nom, String _prenom, String _login, String _password, String _email) {
		this.nom = _nom;
		this.prenom = _prenom;
		this.login = _login;
		this.password = _password;
		this.email = _email;
		this.cave = new Cave();
	}
	
	/*
	 * Modifie les informations passées en paramètre
	 */
	public void modifierUtilisateur(UserForm loginForm) {
				
		if(loginForm.getNom() != null) {
			this.nom = loginForm.getNom();
		}
		if(loginForm.getLogin() != null) {
			this.login = loginForm.getLogin();
		}
		if(loginForm.getPassword() != null) {
			this.password = loginForm.getPassword();
		}
		if(loginForm.getPrenom() != null) {
			this.prenom = loginForm.getPrenom();
		}
		if(loginForm.getEmail() != null) {
			this.email = loginForm.getEmail();
		}
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
