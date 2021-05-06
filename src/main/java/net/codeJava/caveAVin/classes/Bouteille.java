package net.codeJava.caveAVin.classes;

import java.awt.Image;

public class Bouteille {
	private int id;
	private String nom;
	private String description;
	private Image image;
	
	public Bouteille(int _id, String _nom, String _description, Image _image) {
		this.id = _id;
		this.nom = _nom;
		this.description = _description;
		this.image = _image;
	}
	
	public int getID() {
		return this.id;
	}
}
