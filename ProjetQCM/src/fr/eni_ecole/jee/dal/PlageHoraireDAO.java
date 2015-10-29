package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import fr.eni_ecole.jee.bean.PlageHoraire;
import fr.eni_ecole.jee.bean.Test;
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
	
	public static void createPlage(String dateDeb, String dateFin, String heureDeb, String heureFin) throws SQLException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		String debut = dateDeb+" "+heureDeb;
		String fin = dateFin+" "+heureFin;
		try {
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("INSERT INTO plageHoraire(dateDebut,dateFin) VALUES(CONVERT( datetime,?),CONVERT( datetime, ?));");	
			rqt.setString(1, debut);
			rqt.setString(2, fin);
			rqt.executeUpdate();
		} finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	} 
	
	public static ArrayList<PlageHoraire> getAll() throws SQLException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ArrayList<PlageHoraire> plages = new ArrayList<PlageHoraire>();

		try {
			cnx = AccesBase.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery("SELECT * FROM plageHoraire");
			PlageHoraire unePlageHoraire;
			while (rs.next()) {
				unePlageHoraire = new PlageHoraire(rs.getInt("id"), df.format(rs.getTimestamp("dateDebut")), df.format(rs.getTimestamp("dateFin")));
				plages.add(unePlageHoraire);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}

		return plages;
	}
	
	public static void supprimer(PlageHoraire plageHoraire) throws Exception{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("delete from plageHoraire where id=?;");
			rqt.setInt(1, plageHoraire.getId());
			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	
}
