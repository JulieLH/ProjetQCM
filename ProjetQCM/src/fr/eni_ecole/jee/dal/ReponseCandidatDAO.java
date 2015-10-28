package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni_ecole.jee.bean.ReponseCandidat;
import fr.eni_ecole.jee.util.AccesBase;

public class ReponseCandidatDAO 
{
	// Read
	public static ArrayList<ReponseCandidat> getReponsesByInscription(int numInscription) throws SQLException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<ReponseCandidat> lesReponsesCandidat = new ArrayList<ReponseCandidat>();
		
		try
		{
			cnx = AccesBase.getConnection();		
			rqt = cnx.prepareStatement("SELECT * FROM reponseCandidat WHERE numInscription = ?");
			rqt.setInt(1, numInscription);
			rs = rqt.executeQuery();
			ReponseCandidat uneReponseCandidat;
			
			while (rs.next())
			{
				uneReponseCandidat = new ReponseCandidat(rs.getInt("numReponseCandidat"), rs.getInt("numInscription"), rs.getInt("idQuestion"), rs.getInt("idReponse"));
				lesReponsesCandidat.add(uneReponseCandidat);				
			}
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return lesReponsesCandidat;
	}
}
