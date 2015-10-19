package fr.eni_ecole.jee.bean;

public class Test 
{
	// Attributs
	private int id;
	private String libelle;
	private int duree;
	private int seuilMin;
	private int seuilMax;
	
	// Constructeurs
	public Test()
	{
		
	}
		
	public Test(int id, String libelle, int duree, int seuilMin, int seuilMax) 
	{
		super();
		setId(id);
		setLibelle(libelle);
		setDuree(duree);
		setSeuilMin(seuilMin);
		setSeuilMax(seuilMax);
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
	
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	public int getSeuilMin() {
		return seuilMin;
	}
	public void setSeuilMin(int seuilMin) {
		this.seuilMin = seuilMin;
	}
	
	public int getSeuilMax() {
		return seuilMax;
	}
	public void setSeuilMax(int seuilMax) {
		this.seuilMax = seuilMax;
	}	
}
