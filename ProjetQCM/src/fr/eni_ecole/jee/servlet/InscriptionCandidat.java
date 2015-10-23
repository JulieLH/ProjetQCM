package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.bean.Theme;
import fr.eni_ecole.jee.bean.Utilisateur;
import fr.eni_ecole.jee.dal.TestDAO;
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
		String action = request.getParameter("action");
		ArrayList<Utilisateur> lesCandidats = null;
		ArrayList<Theme> lesThemes = null;
		Gson gson = null;

		try 
		{
			if("getTests".equals(action))
			{
				HashMap<String, List<Test>> map = new HashMap<String, List<Test>>();
				gson = new Gson();
				
				Theme theme = new Theme();
				theme.setId(Integer.parseInt(request.getParameter("id"))); ;
				
				List<Test> tests = TestDAO.getTestsByTheme(theme.getId());
				
				response.setContentType("application/json");        
				response.setHeader("Cache-Control", "no-store");
				
				map.put("data", tests);
				
				PrintWriter out = response.getWriter();
				out.println(gson.toJson(map));
				out.flush();
			}
			else
			{
				// Récupération des thèmes
				lesThemes = ThemeDAO.getThemes();
				
				// Récupération des candidats		
				lesCandidats = UtilisateurDAO.getCandidats();
				
				// Passage en attributs
				request.setAttribute("candidats", lesCandidats);
				request.setAttribute("themes", lesThemes);
				request.getRequestDispatcher("/formateur/inscriptionCandidat.jsp").forward(request, response);
			}
		}
		catch (Exception e)
		{
			// Redirection vers la page d'erreur
			request.setAttribute("erreur", e);		
			request.getRequestDispatcher("/erreur.jsp").forward(request, response);				
		}
	}
}
