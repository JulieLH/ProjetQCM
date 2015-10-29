package fr.eni_ecole.jee.bean;

public class Resultat 
{
	// Attributs
	private int numInscription;
	private int nbBonnesReponses;
	private int pourcentage;
	private int seuilAtteint;
	private int tempsPasse;
	
	// Constructeurs
	public Resultat()
	{
		
	}	
	
	public Resultat(int numInscription, int nbBonnesReponses, int pourcentage, int seuilAtteint, int tempsPasse) 
	{
		super();
		setNumInscription(numInscription);
		setNbBonnesReponses(nbBonnesReponses);
		setPourcentage(pourcentage);
		setSeuilAtteint(seuilAtteint);
		setTempsPasse(tempsPasse);
	}

	// Accesseurs
	public int getNumInscription() {
		return numInscription;
	}
	public void setNumInscription(int numInscription) {
		this.numInscription = numInscription;
	}
	
	public int getNbBonnesReponses() {
		return nbBonnesReponses;
	}
	public void setNbBonnesReponses(int nbBonnesReponses) {
		this.nbBonnesReponses = nbBonnesReponses;
	}
	
	public int getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}
	
	public int getSeuilAtteint() {
		return seuilAtteint;
	}
	public void setSeuilAtteint(int seuilAtteint) {
		this.seuilAtteint = seuilAtteint;
	}
	
	public int getTempsPasse() {
		return tempsPasse;
	}
	public void setTempsPasse(int tempsPasse) {
		this.tempsPasse = tempsPasse;
	}
}
