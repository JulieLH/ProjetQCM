package fr.eni_ecole.jee.bean;

public class Reponse 
{
	// Attributs
	private int id;
	private int idQuestion;
	private String libelle;
	private Boolean bonneReponse;
	
	// Constructeurs
	public Reponse()
	{
		
	}	
	
	public Reponse(int id, int idQuestion, String libelle, Boolean bonneReponse)
	{
		super();
		setId(id);
		setIdQuestion(idQuestion);
		setLibelle(libelle);
		setBonneReponse(bonneReponse);
	}
	
	// Accesseurs
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public Boolean getBonneReponse() {
		return bonneReponse;
	}
	public void setBonneReponse(Boolean bonneReponse) {
		this.bonneReponse = bonneReponse;
	}	
}
