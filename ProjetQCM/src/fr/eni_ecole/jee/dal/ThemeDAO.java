package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
}
