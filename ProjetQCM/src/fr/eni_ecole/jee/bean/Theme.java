package fr.eni_ecole.jee.bean;

public class Theme 
{
	// Attributs
	private int id;
	private String libelle;
	
	// Constructeurs
	public Theme()
	{
		
	}
	
	public Theme(int id, String libelle) 
	{
		super();
		setId(id);
		setLibelle(libelle);
	}

	// Accesseurs
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}	
}
