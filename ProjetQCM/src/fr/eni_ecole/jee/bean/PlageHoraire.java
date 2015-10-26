package fr.eni_ecole.jee.bean;

public class PlageHoraire 
{
	// Attributs
	private int id;
	private String dateDebut;
	private String dateFin;
	
	// Constructeurs
	public PlageHoraire()
	{
		
	}	
	
	public PlageHoraire(int id, String dateDebut, String dateFin) 
	{
		super();
		setId(id);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
	}

	// Accesseurs
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
