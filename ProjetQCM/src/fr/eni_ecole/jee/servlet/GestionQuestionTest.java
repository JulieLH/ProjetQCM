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
import javax.servlet.http.HttpSession;

import com.sun.rowset.internal.Row;

import fr.eni_ecole.jee.bean.Question;
import fr.eni_ecole.jee.bean.QuestionReponses;
import fr.eni_ecole.jee.bean.Reponse;
import fr.eni_ecole.jee.bean.Section;
import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.bean.Utilisateur;
import fr.eni_ecole.jee.dal.QuestionReponsesDAO;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected ArrayList<QuestionReponses> processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int idTest = Integer.parseInt(request.getParameter("selectTest"));

		ArrayList<Question> lesQuestions = null;
		ArrayList<QuestionReponses> lesQuestionsReponses = new ArrayList<QuestionReponses>();
		
		

		ArrayList<Section> lesSections = SectionDAO.getByID(idTest);

		for (Section section : lesSections) {

			try {
				int nbQuestion = section.getNbQuestions();
				int id_Test = section.getIdTest();
				int idTheme = section.getIdTheme();
				if (lesQuestions == null) {
					lesQuestions = TestDAO.getListQuestion(nbQuestion, id_Test,idTheme);
				} else {
					lesQuestions.addAll(TestDAO.getListQuestion(nbQuestion,id_Test, idTheme));
				}
				
			} catch (SQLException e) {
				System.out
						.println("ServletException - Impossible d'obtenir les questions liées à ce test : ");
				e.printStackTrace();
			}
			
			
		}
		for (Question q : lesQuestions) {
			QuestionReponses laQR = QuestionReponsesDAO.getReponsesByQuestion(q.getId());
			lesQuestionsReponses.add(laQR);
		}
		HttpSession session = request.getSession(true);
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		int id = user.getId();	
		int numInscri = TestDAO.getNumInscri(id, idTest);
		
		
		session.setAttribute("lesQuestionsReponses", lesQuestionsReponses);
		session.setAttribute("numInscri", numInscri);

		
		request.getRequestDispatcher("/candidat/affichageTest.jsp").forward(
				request, response);
		return lesQuestionsReponses;
	}
	

}
