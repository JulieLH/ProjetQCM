package fr.eni_ecole.jee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GestionDeconnexion
 */
@WebServlet("/GestionDeconnexion")
public class GestionDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ATT_SESSION_USER = "utilisateur";
	public static final String VUE_ACCUEIL = "/connexion.jsp";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/* Recuperation de la session depuis la requete */
		HttpSession session = request.getSession();

		/* Invalidation de la session */
		session.removeAttribute(ATT_SESSION_USER);
		session.invalidate();

		/* Redirect vers la page d'accueil */
		response.sendRedirect(request.getContextPath() + VUE_ACCUEIL);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/* Redirect vers la page d'accueil */
		response.sendRedirect(request.getContextPath() + VUE_ACCUEIL);
	}
}
