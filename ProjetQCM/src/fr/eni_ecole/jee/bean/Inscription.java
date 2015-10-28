package fr.eni_ecole.jee.bean;

import java.util.Date;

public class Inscription 
{
	// Attributs
	private int numInscription;
	private int idUtilisateur;
	private int idTest;
	private int idPlage;
	
	// Constructeurs
	public Inscription()
	{
		
	}	
	
	public Inscription(int numInscription, int idUtilisateur, int idTest, int idPlage) 
	{
		super();
		setNumInscription(numInscription);
		setIdUtilisateur(idUtilisateur);
		setIdTest(idTest);
		setIdPlage(idPlage);
	}

	// Accesseurs
	public int getNumInscription() {
		return numInscription;
	}
	public void setNumInscription(int numInscription) {
		this.numInscription = numInscription;
	}
	
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	public int getIdTest() {
		return idTest;
	}
	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}
	
	public int getIdPlage() {
		return idPlage;
	}
	public void setIdPlage(int idPlage) {
		this.idPlage = idPlage;
	}	
}
