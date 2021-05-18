package net.codeJava.caveAVin.classes;

import java.util.ArrayList;

public class ListOfUsers {
	private static final ListOfUsers listOfUsers = new ListOfUsers();
	private ArrayList<User> list = new ArrayList<User>();
	
	private ListOfUsers() {
		this.list.add(new User("Pierre", "Martin", "pmartin", "azerty"));
		this.list.add(new User("Emma", "Dupond", "edupond", "123456"));
	}

	public static final ListOfUsers getInstance() {
		return listOfUsers;
	}
	
	public ArrayList<User> getList() {
		return list;
	}
}
