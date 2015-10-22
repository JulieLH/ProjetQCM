package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import fr.eni_ecole.jee.bean.Utilisateur;
import fr.eni_ecole.jee.dal.ConnexionDAO;

/**
 * Servlet implementation class GestionConnexion
 */
@WebServlet("/GestionConnexion")
public class GestionConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionConnexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		Utilisateur unUser = null;
		String identifiant = null;
		String mdp = null;
		//final String ATT_USER  = "utilisateur";
		//final String ATT_SESSION_USER = "sessionUtilisateur";
		
		
		

		identifiant = request.getParameter("login");
		mdp = request.getParameter("password");
		try {
			unUser = ConnexionDAO.connectUser(identifiant, mdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Redirige l'utilisateur suivant la réussite de son authentification
		if (unUser != null && unUser.getId() > 0) {

			// TODO : Spécialiser l'utilisateur suivant son idStatut
			// TODO : Ajoute à la session la personne authentifier
			
			HttpSession session = request.getSession();
			session.setAttribute( "utilisateur", unUser );
		
			redirection(request, response, "Accueil.jsp");

		}
		else{
			//TODO : Gérer le message d'erreur de login
			HttpSession session = request.getSession();
			session.setAttribute( "utilisateur", null );
			redirection(request, response, "connexion.jsp");
		}
	}

	private void redirection(HttpServletRequest request,
			HttpServletResponse response, String chemin) {

		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher(chemin);

		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out
					.println("ServletException - Impossible de redirigé vers la page d'accueil : ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out
					.println("IOException - Impossible de redirigé vers la page d'accueil : ");
			e.printStackTrace();
		}

	}

}
