package net.codeJava.caveAVin.classes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cave {
	private static final Cave cave = new Cave();
	private Map caveAVin = new HashMap<Bouteille, Integer>();
	
	public static final Cave getInstance() {
		return cave;
	}
	
	public void ajoutNouvelleBouteille(Bouteille bouteille, int quantite) {
		this.caveAVin.put(bouteille, quantite);
	}
	
	public Bouteille findBouteille(int _id) {
		
		Iterator<Map.Entry<Bouteille, Integer>> iterator = this.caveAVin.entrySet().iterator();
		Boolean trouve = false;
		Bouteille b = null;
		
		while(iterator.hasNext() && !trouve) {
			Map.Entry<Bouteille, Integer> entry = iterator.next();
			if(entry.getKey().getID() == _id) {
				b = entry.getKey();
				trouve = true;
			}
		}
		return b;
	}
	
	public void changerQuantite(int _id, int quantite) {
		Bouteille b = this.findBouteille(_id);
		
		if(b != null) {
			this.caveAVin.replace(b, (int) this.caveAVin.get(b) + quantite);
		}
	}
}
