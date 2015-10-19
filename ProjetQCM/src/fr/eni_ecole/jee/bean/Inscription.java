package fr.eni_ecole.jee.bean;

import java.util.Date;

public class Inscription 
{
	// Attributs
	private int numInscription;
	private int idUtilisateur;
	private int idTest;
	private Date dateDebut;
	private Date dateFin;
	
	// Constructeurs
	public Inscription()
	{
		
	}	
	
	public Inscription(int numInscription, int idUtilisateur, int idTest, Date dateDebut, Date dateFin) 
	{
		super();
		setNumInscription(numInscription);
		setIdUtilisateur(idUtilisateur);
		setIdTest(idTest);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
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
	
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}	
}
