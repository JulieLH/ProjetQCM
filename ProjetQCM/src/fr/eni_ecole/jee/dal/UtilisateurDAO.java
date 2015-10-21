package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.eni_ecole.jee.bean.Utilisateur;
import fr.eni_ecole.jee.util.AccesBase;

public class UtilisateurDAO 
{
	// Read
	public static ArrayList<Utilisateur> getCandidats() throws SQLException
	{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		ArrayList<Utilisateur> lesCandidats = new ArrayList<Utilisateur>();
		
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.createStatement();			
			rs = rqt.executeQuery("SELECT * FROM utilisateur WHERE type = '1'");
			Utilisateur unCandidat;
			while (rs.next())
			{
				unCandidat = new Utilisateur(rs.getInt("id"), rs.getInt("type"), rs.getString("login"), rs.getString("mdp"), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getString("telephone"));
				lesCandidats.add(unCandidat);				
			}
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return lesCandidats;
	}
}
