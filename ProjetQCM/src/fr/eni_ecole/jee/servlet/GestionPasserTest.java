package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.bean.Utilisateur;
import fr.eni_ecole.jee.dal.TestDAO;

/**
 * Servlet implementation class GestionPasserTest
 */
@WebServlet("/GestionPasserTest")
public class GestionPasserTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionPasserTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		
		
		try {

			Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
			int id = user.getId();
			ArrayList<Test> lesTests = TestDAO.getTestsByUser(id);
			request.setAttribute("tests", lesTests);
			request.getRequestDispatcher("/candidat/choixTest.jsp").forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		
	}
	

}
