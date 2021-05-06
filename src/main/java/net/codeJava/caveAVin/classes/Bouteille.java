package net.codeJava.caveAVin.classes;

import java.awt.Image;

public class Bouteille {
	private static int globalID = 0;
	private int id;
	private String nom;
	private String description;
	private Image image;
	
	public Bouteille(int _id, String _nom, String _description, Image _image) {
		this.id = globalID++;
		this.nom = _nom;
		this.description = _description;
		this.image = _image;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
