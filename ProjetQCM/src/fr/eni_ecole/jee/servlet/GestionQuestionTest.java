package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.jee.bean.Section;
import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.dal.SectionDAO;
import fr.eni_ecole.jee.dal.TestDAO;

/**
 * Servlet implementation class GestionQuestionTest
 */
@WebServlet("/GestionQuestionTest")
public class GestionQuestionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionQuestionTest() {
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
	
	protected ArrayList<String> processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	

		int  idTest =  Integer.parseInt(request.getParameter("selectTest"));
		ArrayList<String> lesQuestions = null;
		
		ArrayList<Section> lesSections = SectionDAO.getByID(idTest);
		
		for (Section section : lesSections) {
			
			try {
				int nbQuestion =section.getNbQuestions();
				int id_Test = section.getIdTest();
				int idTheme =  section.getIdTheme();
				lesQuestions =TestDAO.getListQuestion(nbQuestion,id_Test,idTheme);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("lesQuestion", lesQuestions);
		request.getRequestDispatcher("/candidat/affichageTest.jsp").forward(request, response);
		return lesQuestions;
	}

}
