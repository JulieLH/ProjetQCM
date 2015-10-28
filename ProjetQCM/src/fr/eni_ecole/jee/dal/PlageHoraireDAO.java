package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.eni_ecole.jee.bean.PlageHoraire;
import fr.eni_ecole.jee.util.AccesBase;


public class PlageHoraireDAO 
{
	// Read
	public static ArrayList<PlageHoraire> getPlagesByTest(int idTest) throws SQLException
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<PlageHoraire> lesPlages = new ArrayList<PlageHoraire>();
		
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT ph.* FROM plageHoraire ph INNER JOIN plageHoraireTest pht ON ph.id = pht.id_plageHoraire INNER JOIN test t ON pht.id_test = t.id WHERE t.id = ? ORDER BY ph.dateDebut");	
			rqt.setInt(1, idTest);
			rs = rqt.executeQuery();
			PlageHoraire unePlage;
			
			while (rs.next())
			{
				unePlage = new PlageHoraire(rs.getInt("id"), df.format(rs.getTimestamp("dateDebut")), df.format(rs.getTimestamp("dateFin")));
				lesPlages.add(unePlage);				
			}
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return lesPlages;
	}
	
	public static ArrayList<PlageHoraire> getPlagesUlterieuresByTest(int idTest) throws SQLException
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<PlageHoraire> lesPlages = new ArrayList<PlageHoraire>();
		
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT ph.* FROM plageHoraire ph INNER JOIN plageHoraireTest pht ON ph.id = pht.id_plageHoraire INNER JOIN test t ON pht.id_test = t.id WHERE t.id = ? AND ph.dateDebut > GETDATE() ORDER BY ph.dateDebut");	
			rqt.setInt(1, idTest);
			rs = rqt.executeQuery();
			PlageHoraire unePlage;
			
			while (rs.next())
			{
				unePlage = new PlageHoraire(rs.getInt("id"), df.format(rs.getTimestamp("dateDebut")), df.format(rs.getTimestamp("dateFin")));
				lesPlages.add(unePlage);				
			}
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return lesPlages;
	}
}
