package net.codeJava.caveAVin.classes;

import java.util.ArrayList;

public class ListOfUsers {
	private static final ListOfUsers listOfUsers = new ListOfUsers();
	private ArrayList<User> list = new ArrayList<User>();
	
	/*
	 * Utilisateurs créés par défaut
	 */
	private ListOfUsers() {
		this.list.add(new User("Pierre", "Martin", "pmartin", "azerty", "pm@mail.com"));
		this.list.add(new User("Emma", "Dupond", "edupond", "123456", "ed@mail.com"));
	}

	public static final ListOfUsers getInstance() {
		return listOfUsers;
	}
	
	public ArrayList<User> getList() {
		return list;
	}
	
	public void ajoutUtilisateur(String _nom, String _prenom, String _login, String _password, String _email) {
		this.list.add(new User(_nom, _prenom, _login, _password, _email));
	}
}
