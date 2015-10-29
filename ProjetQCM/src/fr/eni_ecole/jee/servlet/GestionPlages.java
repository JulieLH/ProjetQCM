package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;




import fr.eni_ecole.jee.dal.PlageHoraireDAO;


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
			
		}else
		{	
			dispatcher = request.getRequestDispatcher("/formateur/GestionTestAjout.jsp"); 
			dispatcher.forward(request, response);
		}
	}	
	
}
