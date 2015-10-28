package fr.eni_ecole.jee.bean;

public class ReponseCandidat 
{
	// Attributs
	private int numReponse;
	private int numInscription;
	private int idQuestion;
	private int idReponse;
	
	// Constructeurs
	public ReponseCandidat()
	{
		
	}	
	
	public ReponseCandidat(int numReponse, int numInscription, int idQuestion, int idReponse) 
	{
		super();
		setNumReponse(numReponse);
		setNumInscription(numInscription);
		setIdQuestion(idQuestion);
		setIdReponse(idReponse);
	}

	// Accesseurs
	public int getNumReponse() {
		return numReponse;
	}
	public void setNumReponse(int numReponse) {
		this.numReponse = numReponse;
	}
	
	public int getNumInscription() {
		return numInscription;
	}
	public void setNumInscription(int numInscription) {
		this.numInscription = numInscription;
	}
	
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	
	public int getIdReponse() {
		return idReponse;
	}
	public void setIdReponse(int idReponse) {
		this.idReponse = idReponse;
	}	
}
