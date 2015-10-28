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
import fr.eni_ecole.jee.dal.SectionDAO;
import fr.eni_ecole.jee.dal.TestDAO;
import fr.eni_ecole.jee.dal.ThemeDAO;

/**
 * Servlet implementation class GestionTest
 */
@WebServlet("/GestionTest")
public class GestionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionTest() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		
		if("getTests".equals(action))
		{
			HashMap<String, Test> mapTest = new HashMap<String, Test>();
			gson = new Gson();
			Test test = TestDAO.getByID(Integer.parseInt(request.getParameter("idTest")));
			
			mapTest.put("data", test);
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapTest));
			out.flush();
		}else if ("getPlagesTest".equals(action))
		{
			HashMap<String, List<PlageHoraire>> mapPlage = new HashMap<String, List<PlageHoraire>>();
			gson = new Gson();
			List<PlageHoraire> listePlage = PlageHoraireDAO.getPlagesByTest(Integer.parseInt(request.getParameter("idTest")));
			mapPlage.put("data", listePlage);
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapPlage));
			out.flush();
			
		}else if("getCount".equals(action))
		{
			HashMap<String, Integer> mapCountPlage = new HashMap<String, Integer>();
			gson = new Gson();
			int nbSection = SectionDAO.getCountSectionByTest(Integer.parseInt(request.getParameter("idTest")));
			
			mapCountPlage.put("data", nbSection);
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapCountPlage));
			out.flush();
		}else if("getSection".equals(action))
		{
			HashMap<String, List<Section>> mapSection = new HashMap<String, List<Section>>();
			gson = new Gson();
			List<Section> listeSection = SectionDAO.getByID(Integer.parseInt(request.getParameter("idTest")));
			mapSection.put("data", listeSection);
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapSection));
			out.flush();
		}else if("getTheme".equals(action))
		{
			HashMap<String, Theme> mapTheme = new HashMap<String, Theme>();
			gson = new Gson();
			Theme theme = ThemeDAO.getByID(Integer.parseInt(request.getParameter("idTheme")));
			
			mapTheme.put("theme", theme);
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(mapTheme));
			out.flush();
		}else if("ajoutTest".equals(action))
		{
			dispatcher = request.getRequestDispatcher("/formateur/GestionTestAjout.jsp"); 
			dispatcher.forward(request, response);
		}else
		{
			Test testdetest = new Test(1,"test5", 20, 12, 38);
			ArrayList<Test> listeTests = (ArrayList<Test>) request.getSession().getAttribute("listeTest");
			Test monTest = (Test)request.getSession().getAttribute("monTest");
			if (listeTests== null) listeTests=new ArrayList<Test>();
			if (monTest == null) monTest = new Test();
			try {
				listeTests = TestDAO.getAll();
				monTest = TestDAO.getByID(testdetest.getId());
			}catch (SQLException sqle){
				return;
			}
			request.getSession().setAttribute("listeTest", listeTests);			
			dispatcher = request.getRequestDispatcher("/formateur/gestionTest.jsp"); 
			dispatcher.forward(request, response);
		}
	}
	
	
	
	

}
