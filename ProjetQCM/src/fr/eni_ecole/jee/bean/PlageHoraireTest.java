package fr.eni_ecole.jee.bean;

public class PlageHoraireTest 
{
	// Attributs
	private int libelleTest;
	private String dateDebut;
	private String dateFin;
	
	// Constructeurs
	public PlageHoraireTest()
	{
		
	}	
	
	public PlageHoraireTest(int libelleTest, String dateDebut, String dateFin) 
	{
		super();
		setLibelleTest(libelleTest);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
	}
	
	// Accesseurs
	public int getLibelleTest() {
		return libelleTest;
	}
	public void setLibelleTest(int libelleTest) {
		this.libelleTest = libelleTest;
	}
	
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}	
}
