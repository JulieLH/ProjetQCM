package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.jee.bean.Theme;
import fr.eni_ecole.jee.bean.Utilisateur;
import fr.eni_ecole.jee.dal.ThemeDAO;
import fr.eni_ecole.jee.dal.UtilisateurDAO;

@WebServlet("/InscriptionCandidat")
public class InscriptionCandidat extends HttpServlet 
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
		ArrayList<Utilisateur> lesCandidats = null;
		ArrayList<Theme> lesThemes = null;

		// Récupération des thèmes
		try 
		{
			lesThemes = ThemeDAO.getThemes();
		} catch (SQLException e) {
			System.out.println("Erreur lors de la récupération des thèmes : " + e.getMessage());
		}
		
		// Récupération des candidats		
		try 
		{
			lesCandidats = UtilisateurDAO.getCandidats();
		} 
		catch (SQLException e) {
			System.out.println("Erreur lors de la récupération des candidats : " + e.getMessage());
		}
		
		// Passage en attributs
		request.setAttribute("candidats", lesCandidats);
		request.setAttribute("themes", lesThemes);
		request.getRequestDispatcher("/formateur/inscriptionCandidat.jsp").forward(request, response);
	}
}
