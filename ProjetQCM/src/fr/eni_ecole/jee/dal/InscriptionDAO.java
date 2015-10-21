package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import fr.eni_ecole.jee.bean.Inscription;
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
			rqt = cnx.prepareStatement("INSERT INTO inscriptions(id_utilisateur, id_test, date_debut, date_fin) VALUES (?,?,?,?)");
			rqt.setInt(1, uneInscription.getIdUtilisateur());
			rqt.setInt(2, uneInscription.getIdTest());
			rqt.setDate(3, new java.sql.Date(uneInscription.getDateDebut().getTime()));
			rqt.setDate(4, new java.sql.Date(uneInscription.getDateFin().getTime()));
			rqt.executeUpdate();
		}
		finally
		{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
}
