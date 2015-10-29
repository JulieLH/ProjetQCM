package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.jee.bean.Inscription;
import fr.eni_ecole.jee.bean.Question;
import fr.eni_ecole.jee.bean.Reponse;
import fr.eni_ecole.jee.bean.ReponseCandidat;
import fr.eni_ecole.jee.bean.Section;
import fr.eni_ecole.jee.dal.InscriptionDAO;
import fr.eni_ecole.jee.dal.ReponseCandidatDAO;
import fr.eni_ecole.jee.dal.SectionDAO;

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
		int inscription = Integer.parseInt(paramInscription);		
		
		try 
		{
			// Récupération de l'inscription concernée
			Inscription lInscription = InscriptionDAO.getByID(inscription);
			// Récupération des réponses du candidat données lors du test concerné par l'inscription
			ArrayList<ReponseCandidat> lesReponsesCandidat = ReponseCandidatDAO.getReponsesByInscription(inscription);
			// Liste des questions posées en fonction de chaque réponse
			ArrayList<Question> lesQuestionPosees = new ArrayList<Question>();
			
			// Pour chaque réponse donnée, on récupère la question
			for (ReponseCandidat rc : lesReponsesCandidat)
			{
				lesQuestionPosees.add(rc.getLaQuestion());
			}
			
			// Données nécessaires au calcul du résultat			
			ArrayList<Section> lesSections = SectionDAO.getByID(lInscription.getIdTest());
			int nbSections = lesSections.size();
			int nbQuestions = lesQuestionPosees.size();
			int cptBonnesReponses = 0;
			
			// Pour chaque réponse donnée, on récupère la question et la réponse
			for (ReponseCandidat rc : lesReponsesCandidat)
			{
				
			}
			
			// Calcul des bonnes réponses, du pourcentage, du seuil obtenu			
			
			
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
