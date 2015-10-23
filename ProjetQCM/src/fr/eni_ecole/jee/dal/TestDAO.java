package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni_ecole.jee.bean.*;
import fr.eni_ecole.jee.util.AccesBase;

public class TestDAO 
{
	// Create
	public static void add(Test unTest) throws Exception
	{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("INSERT INTO test(libelle, duree, seuil_min,seuil_max) VALUES (?,?,?,?)");
			rqt.setString(1, unTest.getLibelle());
			rqt.setInt(2, unTest.getDuree());
			rqt.setInt(3, unTest.getSeuilMin());
			rqt.setInt(4, unTest.getSeuilMax());
			rqt.executeUpdate();
		}
		finally
		{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}

	// Delete
	public static void remove(Test unTest) throws Exception
	{		
		Connection cnx=null;
		PreparedStatement rqt=null;
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("DELETE FROM test WHERE id = ?");
			rqt.setInt(1, unTest.getId());
			rqt.executeUpdate();
		}
		finally
		{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}

	// Update
	public static void update(Test unTest) throws Exception
	{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("UPDATE test SET libelle = ?,duree = ?,seuil_min = ?,seuil_max = ? WHERE id = ?");
			rqt.setString(1, unTest.getLibelle());
			rqt.setInt(2, unTest.getDuree());
			rqt.setInt(3, unTest.getSeuilMin());
			rqt.setInt(4, unTest.getSeuilMax());
			rqt.setInt(5, unTest.getId());
			rqt.executeUpdate();
		}
		finally
		{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	// Read
	public static Test getByID(Test unTest) throws Exception
	{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs = null;
		Test monTest = null;
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT * from test WHERE id= ?");
			rqt.setInt(1, unTest.getId());
			rs = rqt.executeQuery();
			while (rs.next())
			{
				monTest = new Test(rs.getInt("id"), rs.getString("libelle"),rs.getInt("duree"),rs.getInt("seuil_min"),rs.getInt("seuil_max"));
			}
		}
		finally
		{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return monTest;
	}
	
	public static ArrayList<Test> getAll() throws SQLException
	{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		ArrayList<Test> tests = new ArrayList<Test>();
		
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.createStatement();			
			rs = rqt.executeQuery("SELECT * FROM test");
			Test unTest;
			while (rs.next())
			{
				unTest = new Test(rs.getInt("id"), rs.getString("libelle"),rs.getInt("duree"),rs.getInt("seuil_min"),rs.getInt("seuil_max"));
				tests.add(unTest);				
			}
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return tests;	
	}
	
	public static ArrayList<Test> getTestsByTheme(int idTheme) throws SQLException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<Test> lesTests = new ArrayList<Test>();
		
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT t.* FROM TEST t INNER JOIN SECTION s ON t.id = s.id_test INNER JOIN THEME th ON s.id_theme = th.id_theme WHERE th.id_theme = ?");	
			rqt.setInt(1, idTheme);
			rs = rqt.executeQuery();
			Test unTest;
			
			while (rs.next())
			{
				unTest = new Test(rs.getInt("id"), rs.getString("libelle"),rs.getInt("duree"),rs.getInt("seuil_min"),rs.getInt("seuil_max"));
				lesTests.add(unTest);				
			}
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return lesTests;
	}
	
//	public static String[][] getDetailTest(int idTest) 
//	{
//		String[][] tabDetailTest = null;
//		Connection cnx = null;
//		PreparedStatement rqt = null;
//		ResultSet rs = null;
//		
//		try
//		{
//			cnx = AccesBase.getConnection();
//			rqt = cnx.prepareStatement(
//					" select t.libelle, t.duree,t.seuil_min,t.seuil_max, "+
//					" p.dateDebut,p.dateFin, s.num_section,s.nb_questions,th.libelle "+
//					" from test t "+
//					" inner join plageHoraireTest ph ON ph.id_test = t.id "+
//					" inner join plageHoraire p ON p.id = ph.id_plageHoraire "+
//					" inner join section s ON s.id_test = t.id "+
//					" inner join theme th ON th.id_theme = s.id_theme "+
//					" where t.id = ? "
//					);	
//			rqt.setInt(1, idTest);
//			rs = rqt.executeQuery();
//			Test unTest;
//			
//			while (rs.next())
//			{
//								
//			}
//		}
//		finally
//		{
//			if (rs!=null) rs.close();
//			if (rqt!=null) rqt.close();
//			if (cnx!=null) cnx.close();
//		}
//		
//		return tabDetailTest;
//		
//	}
	
	
}
