package fr.eni_ecole.jee.servlet;

import java.awt.print.Printable;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.dal.TestDAO;

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
		Test testdetest = new Test(1,"test5", 20, 12, 38);
		ArrayList<Test> listeTests = (ArrayList<Test>) request.getSession().getAttribute("listeTest");
		Test monTest = (Test)request.getSession().getAttribute("monTest");
		if (listeTests== null) listeTests=new ArrayList<Test>();
		if (monTest == null) monTest = new Test();
		try {
			listeTests = TestDAO.getAll();
			monTest = TestDAO.getByID(testdetest);
		}catch (SQLException sqle){
			return;
		}
		request.getSession().setAttribute("listeTest", listeTests);			
		dispatcher = request.getRequestDispatcher("/formateur/gestionTest.jsp"); 
		dispatcher.forward(request, response);
	}
	
	
	
	

}
