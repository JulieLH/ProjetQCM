package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.jee.bean.Inscription;
import fr.eni_ecole.jee.bean.Question;
import fr.eni_ecole.jee.bean.QuestionReponses;
import fr.eni_ecole.jee.bean.Reponse;
import fr.eni_ecole.jee.bean.ReponseCandidat;
import fr.eni_ecole.jee.bean.Resultat;
import fr.eni_ecole.jee.bean.Section;
import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.dal.InscriptionDAO;
import fr.eni_ecole.jee.dal.QuestionReponsesDAO;
import fr.eni_ecole.jee.dal.ReponseCandidatDAO;
import fr.eni_ecole.jee.dal.ResultatDAO;
import fr.eni_ecole.jee.dal.SectionDAO;
import fr.eni_ecole.jee.dal.TestDAO;

@WebServlet("/GestionResultats")
public class GestionResultats extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String paramInscription = request.getParameter("inscription");
		String paramTempsRestant = request.getParameter("temps");
		int inscription = Integer.parseInt(paramInscription);
		int tempsRestant = Integer.parseInt(paramTempsRestant);
		
		try 
		{
			// Récupération de l'inscription concernée
			Inscription lInscription = InscriptionDAO.getByID(inscription);
			// Récupération des réponses du candidat données lors du test concerné par l'inscription
			ArrayList<ReponseCandidat> lesReponsesCandidat = ReponseCandidatDAO.getReponsesByInscription(inscription);
			// Récupération du test
			Test leTest = TestDAO.getTestByInscription(lInscription.getNumInscription());
			
			// Données nécessaires au calcul du résultat			
			int nbQuestions = TestDAO.getNbQuestions(leTest.getId());
			int cptBonnesReponses = 0;
			
			// Pour chaque réponse(s) donnée(s) à une question
			for (ReponseCandidat rc : lesReponsesCandidat)
			{
				// On récupère la question
				Question laQuestion = rc.getLaQuestion();
				// On récupère les réponses à la question
				ArrayList<Reponse> lesReponses = QuestionReponsesDAO.getReponsesByQuestion(laQuestion.getId()).getLesReponses();
				// On récupère les réponses données
				ArrayList<Reponse> lesReponsesDonnées = rc.getLesReponses();

				// Initialisation des compteurs de bonnes réponses
				int OK = 0;
				int nbBonneReponses = 0;
				for (Reponse r : lesReponses)
				{
					if (r.getBonneReponse())
						nbBonneReponses++;
				}
				
				// Calcul des bonnes réponses
				// Pour chacune des réponses données à la question
				for (Reponse reponseDonnée : lesReponsesDonnées)
				{
					int id = reponseDonnée.getId();
					// On parcourt les réponses
					for (Reponse r : lesReponses)
					{
						// Lorsqu'on retrouve l'ID de la réponse donnée
						if (r.getId() == id)
						{
							// Si c'est une bonne réponse, on incrémente le compteur de réponses OK
							if (r.getBonneReponse())
								OK++;
						}
					}
				}
				
				// Si il y a autant de réponses OK que de bonnes réponses, le point est acquis
				if (OK == nbBonneReponses)
					cptBonnesReponses++;
			}
			
			// Calcul du pourcentage
			int pourcentage = (100*cptBonnesReponses)/nbQuestions;
		
			// Calcul du seuil obtenu (via la note sur 20)
			int noteSurVingt;
			String seuil = "";
			noteSurVingt = (cptBonnesReponses*20)/nbQuestions;
			if((noteSurVingt > 0) && (noteSurVingt <= leTest.getSeuilMin()))
				seuil = "Non Acquis";
			else if((noteSurVingt > leTest.getSeuilMin()) && (noteSurVingt <= leTest.getSeuilMax()))
				seuil = "En Cours d'Acquisition";
			else if((noteSurVingt > leTest.getSeuilMax()) && (noteSurVingt <= 20))
				seuil = "Acquis";
			
			// Calcul du temps passé
			int tempsPasse;
			tempsPasse = leTest.getDuree() - tempsRestant;
			
			// Insertion dans la table des résultat et passage en paramètre de session avec le test pour la JSP
			Resultat leResultat = new Resultat(lInscription.getNumInscription(), cptBonnesReponses, pourcentage, noteSurVingt, tempsPasse);
			
			ResultatDAO.add(leResultat);
			
			ArrayList<QuestionReponses> lesQuestionsReponses = QuestionReponsesDAO.getQuestionsReponsesByTest(leTest.getId());
						
			request.getSession().setAttribute("test", leTest);
			request.getSession().setAttribute("resultat", leResultat);	
			request.getSession().setAttribute("questionReponses", lesQuestionsReponses);	
			request.getSession().setAttribute("tempsPasse", tempsPasse);
			request.getSession().setAttribute("seuil", seuil);
			request.getRequestDispatcher("/candidat/consultationResultats.jsp").forward(request, response);
		} 
		catch (Exception e) 
		{
			// Redirection vers la page d'erreur
			request.setAttribute("erreur", e);		
			request.getRequestDispatcher("/erreur.jsp").forward(request, response);
		}
	}
}