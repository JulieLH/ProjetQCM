package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni_ecole.jee.bean.Inscription;
import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.util.AccesBase;

public class InscriptionDAO 
{
	// Create
	public static void add(Inscription uneInscription) throws SQLException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;

		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("INSERT INTO inscription(id_utilisateur, id_test, id_plage) VALUES (?,?,?)");
			rqt.setInt(1, uneInscription.getIdUtilisateur());
			rqt.setInt(2, uneInscription.getIdTest());
			rqt.setInt(3, uneInscription.getIdPlage());
			rqt.executeUpdate();
		}
		finally
		{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	// Read
	public static Inscription getByID(int idInscription) throws Exception 
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Inscription monInscription = null;
		
		try 
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT * FROM inscription WHERE num_inscription = ?");
			rqt.setInt(1, idInscription);
			rs = rqt.executeQuery();
			while (rs.next()) 
			{
				monInscription = new Inscription(rs.getInt("num_inscription"), rs.getInt("id_utilisateur"), rs.getInt("id_test"), rs.getInt("id_plage"));
			}
		} 
		finally 
		{
			if (rqt != null) rqt.close();
			if (cnx != null) cnx.close();
		}
		
		return monInscription;
	}
}
