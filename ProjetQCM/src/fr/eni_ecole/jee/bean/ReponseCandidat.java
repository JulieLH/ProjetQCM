package fr.eni_ecole.jee.bean;

import java.util.ArrayList;

public class ReponseCandidat 
{
	// Attributs
	private int numReponse;
	private int numInscription;
	private Question laQuestion;
	private ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();
	
	// Constructeurs
	public ReponseCandidat()
	{
		
	}	
	
	public ReponseCandidat(int numReponse, int numInscription, Question laQuestion, ArrayList<Reponse> lesReponses) 
	{
		super();
		setNumReponse(numReponse);
		setNumInscription(numInscription);
		setLaQuestion(laQuestion);
		setLesReponses(lesReponses);
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

	public Question getLaQuestion() {
		return laQuestion;
	}

	public void setLaQuestion(Question laQuestion) {
		this.laQuestion = laQuestion;
	}

	public ArrayList<Reponse> getLesReponses() {
		return lesReponses;
	}

	public void setLesReponses(ArrayList<Reponse> lesReponses) {
		this.lesReponses = lesReponses;
	}	
}
