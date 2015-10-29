package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni_ecole.jee.bean.Resultat;
import fr.eni_ecole.jee.util.AccesBase;

public class ResultatDAO 
{
	// Create
		public static void add(Resultat unResultat) throws SQLException
		{
			Connection cnx = null;
			PreparedStatement rqt = null;

			try
			{
				cnx = AccesBase.getConnection();
				rqt = cnx.prepareStatement("INSERT INTO resultat (num_inscription, nb_bonnes_rep, pourcentage, seuil_atteint, temps_passe) VALUES (?, ?, ?, ?, ?)");
				rqt.setInt(1, unResultat.getNumInscription());
				rqt.setInt(2, unResultat.getNbBonnesReponses());
				rqt.setInt(3, unResultat.getPourcentage());
				rqt.setInt(4, unResultat.getSeuilAtteint());
				rqt.setInt(5, unResultat.getTempsPasse());
				rqt.executeUpdate();
			}
			finally
			{
				if (rqt!=null) rqt.close();
				if (cnx!=null) cnx.close();
			}
		}
}
