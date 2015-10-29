package fr.eni_ecole.jee.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionReponses implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Attributs
	private Question laQuestion;
	private ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();
	
	// Constructeurs
	public QuestionReponses()
	{
		
	}	
	
	public QuestionReponses(Question laQuestion, ArrayList<Reponse> lesReponses) 
	{
		setLaQuestion(laQuestion);
		setLesReponses(lesReponses);
	}

	// Accesseurs
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
