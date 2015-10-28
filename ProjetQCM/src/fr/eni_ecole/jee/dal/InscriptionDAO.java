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
}
