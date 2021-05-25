package net.codeJava.caveAVin.classes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.codeJava.caveAVin.jsonObject.BouteilleForm;

public class Cave {
	/*
	 *  Chaque cave personnelle stock l'ensemble des bouteilles dans une map 
	 */
	private HashMap<Bouteille, Integer> caveAVin = new HashMap<Bouteille, Integer>();

	public Cave() {

	}

	/*
	 * Fonction qui retourne un booléen vrai si l'id fournit en paramètre correspond à une bouteille présente dans la map
	 */
	public Bouteille findBouteille(int _id) {

		Iterator<Map.Entry<Bouteille, Integer>> iterator = this.caveAVin.entrySet().iterator();
		Boolean trouve = false;
		Bouteille b = null;

		while(iterator.hasNext() && !trouve) {
			Map.Entry<Bouteille, Integer> entry = iterator.next();
			if(entry.getKey().getId() == _id) {
				b = entry.getKey();
				trouve = true;
			}
		}
		return b;
	}

	/*
	 * Fonction qui permet de chercher si un ID fournit correspond bien à une bouteille existante
	 */
	public Boolean bouteilleExiste(int _id) {

		Iterator<Map.Entry<Bouteille, Integer>> iterator = this.caveAVin.entrySet().iterator();
		Boolean trouve = false;

		while(iterator.hasNext() && !trouve) {
			Map.Entry<Bouteille, Integer> entry = iterator.next();
			if(entry.getKey().getId() == _id) {
				trouve = true;
			}
		}
		return trouve;
	}
	
	public Bouteille recupBouteille(int _id) {

		Iterator<Map.Entry<Bouteille, Integer>> iterator = this.caveAVin.entrySet().iterator();
		Bouteille b = null;

		while(iterator.hasNext()) {
			Map.Entry<Bouteille, Integer> entry = iterator.next();
			if(entry.getKey().getId() == _id) {
				b = entry.getKey();
			}
		}
		return b;
	}
	
	public int recupQuantiteBouteille(Bouteille b) {
		return this.caveAVin.get(b);
	}

	public void ajoutNouvelleBouteille(Bouteille bouteille, int quantite) {
		this.caveAVin.put(bouteille, quantite);
	}

	public void supprimerBouteille(int _id) {
		Bouteille b = this.findBouteille(_id);
		this.caveAVin.remove(b);
	}

	/*
	 * Change la quantité d'un bouteille pour un id donné 
	 */
	public void changerQuantite(int _id, int quantite) {
		Bouteille b = this.findBouteille(_id);
		this.caveAVin.replace(b, (int) this.caveAVin.get(b) + quantite);
	}

	/*
	 * Modifie les informations passées en paramètre, pour l'id d'un bouteille donnée
	 */
	public void modifierBouteille(BouteilleForm modifForm) {
		Bouteille b = this.findBouteille(modifForm.getId());
		int quantite = this.caveAVin.get(b);
		
		if(modifForm.getId() != 0) {
			b.setId(modifForm.getId());
		}
		if(modifForm.getNom() != null) {
			b.setNom(modifForm.getNom());
		}
		if(modifForm.getCepage() != null) {
			b.setCepage(modifForm.getCepage());
		}
		if(modifForm.getAnnee() != null) {
			b.setAnnee(modifForm.getAnnee());
		}
		if(modifForm.getDescription() != null) {
			b.setDescription(modifForm.getDescription());
		}
		if(modifForm.getImage() != null) {
			b.setImage(modifForm.getImage());
		}
		
		this.caveAVin.remove(this.findBouteille(modifForm.getId()));
		this.caveAVin.put(b, quantite);
		
		if(modifForm.getQuantite() != 0) {
			this.caveAVin.replace(b, (int) this.caveAVin.get(b) + modifForm.getQuantite());
		}
	}

	public HashMap<Bouteille, Integer> getCave() {
		return this.caveAVin;
	}
}
