package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni_ecole.jee.bean.Section;
import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.bean.Theme;
import fr.eni_ecole.jee.util.AccesBase;

public class ThemeDAO 
{
	// Read
	public static ArrayList<Theme> getThemes() throws SQLException
	{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		ArrayList<Theme> lesThemes = new ArrayList<Theme>();
		
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.createStatement();			
			rs = rqt.executeQuery("SELECT * FROM theme");
			Theme unTheme;
			while (rs.next())
			{
				unTheme = new Theme(rs.getInt("id_theme"), rs.getString("libelle"));
				lesThemes.add(unTheme);				
			}
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return lesThemes;
	}
	
	public static Theme getByID(int idTheme) throws Exception
	{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs = null;
		Theme monTheme = null;
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT t.* FROM theme t WHERE t.id_theme = ?");
			rqt.setInt(1, idTheme);
			rs = rqt.executeQuery();
			while (rs.next())
			{
				//création d'une section a ajouté dans la liste
				monTheme = new Theme(rs.getInt("id_theme"),rs.getString("libelle"));
			}
		}
		finally
		{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return monTheme;
	}	
}
