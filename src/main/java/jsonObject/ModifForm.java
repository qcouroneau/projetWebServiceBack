package jsonObject;

public class ModifForm {
	private int id;
	private int quantite;
	
	public ModifForm(int id, int quantite) {
		super();
		this.id = id;
		this.quantite = quantite;
	}

	public int getId() {
		return id;
	}

	public int getQuantite() {
		return quantite;
	}
}
