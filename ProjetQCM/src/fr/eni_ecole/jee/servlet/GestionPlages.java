package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;












import fr.eni_ecole.jee.bean.PlageHoraire;
import fr.eni_ecole.jee.bean.Section;
import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.bean.Theme;
import fr.eni_ecole.jee.dal.PlageHoraireDAO;
import fr.eni_ecole.jee.dal.PlageHoraireTestDAO;
import fr.eni_ecole.jee.dal.SectionDAO;
import fr.eni_ecole.jee.dal.TestDAO;
import fr.eni_ecole.jee.dal.ThemeDAO;


/**
 * Servlet implementation class GestionPlages
 */
@WebServlet("/GestionPlages")
public class GestionPlages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher dispatcher; 
		String action = request.getParameter("action");
		Gson gson = null;
		if("createPlage".equals(action))
		{
			PlageHoraireDAO.createPlage(request.getParameter("dateDebut"), request.getParameter("dateFin"),request.getParameter("heureDeb"),request.getParameter("heureFin"));
			HashMap<String, Boolean> mapPlage = new HashMap<String, Boolean>();
			mapPlage.put("InsertOk", true);
			gson = new Gson();
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapPlage));
			out.flush();

		}else if("getPlages".equals(action))
		{
			HashMap<String, List<PlageHoraire>> mapPlage = new HashMap<String, List<PlageHoraire>>();
			gson = new Gson();
			List<PlageHoraire> listePlage = PlageHoraireDAO.getAll();
			mapPlage.put("data", listePlage);
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapPlage));
			out.flush();
		}else if("removePlage".equals(action))
		{
			PlageHoraire plageHoraire = new PlageHoraire(Integer.parseInt(request.getParameter("idPlage")), "", "");
			PlageHoraireDAO.supprimer(plageHoraire);
			HashMap<String, Boolean> mapPlage = new HashMap<String, Boolean>();
			mapPlage.put("RemoveOk", true);
			gson = new Gson();
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapPlage));
			out.flush();
		}else if("getTheme".equals(action))
		{
			HashMap<String, List<Theme>> mapTheme = new HashMap<String, List<Theme>>();
			gson = new Gson();
			List<Theme> listeTheme = ThemeDAO.getThemes();
			mapTheme.put("data", listeTheme);
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapTheme));
			out.flush();
		}else if("createTest".equals(action))
		{
			Test monTest = new Test(1, request.getParameter("nom"), Integer.parseInt(request.getParameter("duree")), Integer.parseInt(request.getParameter("seuil1")), Integer.parseInt(request.getParameter("seuil2")));
			int idTest = TestDAO.add(monTest);
			PlageHoraireTestDAO.createPlageHoraireTestDAO(idTest, Integer.parseInt(request.getParameter("idPlage")));
			HashMap<String, Integer> mapTest = new HashMap<String, Integer>();
			mapTest.put("data", idTest);
			gson = new Gson();
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapTest));
			out.flush();
			
		}else if("createSection".equals(action))
		{
			Section maSection = new Section(1, Integer.parseInt(request.getParameter("idTest")), Integer.parseInt(request.getParameter("idTheme")), Integer.parseInt(request.getParameter("nbQuestion"))); 
			SectionDAO.add(maSection);
			HashMap<String, Boolean> mapSection = new HashMap<String, Boolean>();
			mapSection.put("CreateOk", true);
			gson = new Gson();
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapSection));
			out.flush();
		}else
		{
			ArrayList<PlageHoraire> listePlages = (ArrayList<PlageHoraire>) request.getSession().getAttribute("listePlages");
			if (listePlages== null) listePlages=new ArrayList<PlageHoraire>();
			try {
				listePlages = PlageHoraireDAO.getAll();
			}catch (SQLException sqle){
				return;
			}
			request.getSession().setAttribute("listePlages", listePlages);			
			dispatcher = request.getRequestDispatcher("/formateur/GestionTestAjout.jsp"); 
			dispatcher.forward(request, response);
		}
		
		
	}	

}
