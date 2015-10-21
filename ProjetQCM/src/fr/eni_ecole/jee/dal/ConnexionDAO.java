package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.eni_ecole.jee.bean.Inscription;
import fr.eni_ecole.jee.bean.Utilisateur;
import fr.eni_ecole.jee.util.AccesBase;

public class ConnexionDAO {

	// Create
	public static Utilisateur connectUser(String login, String mdp)
			throws SQLException {
		Utilisateur unUser = new Utilisateur();
		Connection cnx = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String req = "SELECT * FROM UTILISATEUR WHERE login = ? AND mdp = ? ;";

		try {
			cnx = AccesBase.getConnection();
		} catch (SQLException e2) {
			System.out
					.println("Erreur lors de la récupération de la connection (authentifiaction) : ");
			e2.printStackTrace();
		}

		try {
			ps = cnx.prepareStatement(req);
			ps.setString(1, login);
			ps.setString(2, mdp);
		} catch (SQLException e1) {
			System.out
					.println("Erreur lors de la préparation de la requete d'authentification : ");
			e1.printStackTrace();
		}

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out
					.println("Erreur lors de l'execution de la requete d'authentification : ");
			e.printStackTrace();
		}

		try {
			if (rs.next()) {
				unUser.setId(rs.getInt("id"));
				unUser.setLogin(rs.getString("login"));
				unUser.setMail(rs.getString("mail"));
				unUser.setMotDePasse(rs.getString("mdp"));
				unUser.setNom(rs.getString("nom"));
				unUser.setPrenom(rs.getString("prenom"));
				unUser.setNumTel(rs.getString("telephone"));
				unUser.setType(rs.getInt("type"));
			}

		} catch (Exception e) {
			System.out
					.println("Erreur lors du cast de la personne à authentifié : ");
			e.printStackTrace();
		}

		return unUser;
	}

}
