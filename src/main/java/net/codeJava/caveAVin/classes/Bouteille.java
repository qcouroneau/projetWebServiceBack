package net.codeJava.caveAVin.classes;

import java.awt.Image;

public class Bouteille {
	private static int globalID = 0;
	private int id;
	private String nom;
	private String cepage;
	private String annee;
	private String description;
	private Image image;
	
	public Bouteille(String _nom, String _cepage, String _annee, String _description, Image _image) {
		this.id = globalID++;
		this.nom = _nom;
		this.cepage = _cepage;
		this.annee = _annee;
		this.description = _description;
		this.image = _image;
	}
	
	public Bouteille(String _nom, String _cepage, String _annee, String _description) {
		this.id = globalID++;
		this.nom = _nom;
		this.cepage = _cepage;
		this.annee = _annee;
		this.description = _description;
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
	
	public String getCepage() {
		return cepage;
	}

	public void setCepage(String cepage) {
		this.cepage = cepage;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
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
