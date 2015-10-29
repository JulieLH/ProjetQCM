package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni_ecole.jee.bean.Question;
import fr.eni_ecole.jee.bean.QuestionReponses;
import fr.eni_ecole.jee.bean.Reponse;
import fr.eni_ecole.jee.bean.Section;
import fr.eni_ecole.jee.bean.Utilisateur;
import fr.eni_ecole.jee.dal.ReponseCandidatDAO;
import fr.eni_ecole.jee.dal.SectionDAO;
import fr.eni_ecole.jee.dal.TestDAO;

/**
 * Servlet implementation class GestionReponseTest
 */
@WebServlet("/GestionReponseTest")
public class GestionReponseTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionReponseTest() {
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
		//HttpSession session = request.getSession(true);
		int numInscription = (int) request.getSession().getAttribute("numInscri");		
		
		ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();				
		String[] rep = request.getParameterValues("CheckRep");
		
		for (String string : rep) {
			Reponse unReponse = ReponseCandidatDAO.getRepByID(Integer.parseInt(string));
			lesReponses.add(unReponse);
			
			ReponseCandidatDAO.add(numInscription,unReponse.getIdQuestion(), Integer.parseInt(string));
		}
		
		dispatcher = request.getRequestDispatcher("/candidat/affichageTest.jsp"); 
		dispatcher.forward(request, response);
		
		
	}

}
