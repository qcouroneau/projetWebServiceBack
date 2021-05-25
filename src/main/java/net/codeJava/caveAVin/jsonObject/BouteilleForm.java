package net.codeJava.caveAVin.jsonObject;

import java.awt.Image;

/*
 * Classe servant � caster un JSON fournit par une requ�te venant du Front (requ�te POST)
 * Les donn�es du body des requ�tes concernant une bouteille (modification, ajout de nouvelle bouteille, ...) vont �tres soctk�es ici
 */
public class BouteilleForm {
	private int id;
	private String nom;
	private String cepage;
	private String annee;
	private String description;
	private Image image;
	private int quantite;
	
	public BouteilleForm(int _id, String _nom, String _cepage, String _annee, String _description, Image _image, int _quantite) {
		super();
		this.id = _id;
		this.nom = _nom;
		this.cepage = _cepage;
		this.annee = _annee;
		this.description = _description;
		this.image = _image;
		this.quantite = _quantite;
	}

	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}

	public int getQuantite() {
		return quantite;
	}

	public String getCepage() {
		return cepage;
	}

	public String getAnnee() {
		return annee;
	}

	public String getDescription() {
		return description;
	}
	
	public Image getImage() {
		return image;
	}
}
