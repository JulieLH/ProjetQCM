package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni_ecole.jee.bean.Section;
import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.util.AccesBase;

public class SectionDAO {

	public static ArrayList<Section> getByID(int idTest) throws Exception
	{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs = null;
		ArrayList<Section> lesSection = new ArrayList<Section>();
		try
		{	
			// Je rajoute un com 
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT s.* FROM section s where s.id_test = ?");
			rqt.setInt(1, idTest);
			rs = rqt.executeQuery();
			Section uneSection;
			while (rs.next())
			{
				//création d'une section a ajouté dans la liste
				uneSection = new Section(rs.getInt("num_section"),rs.getInt("id_test"),rs.getInt("id_theme"),rs.getInt("nb_questions"));
				lesSection.add(uneSection);
			}
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return lesSection;
	}
	
	
	// COUNT 
	
		public static int getCountSectionByTest(int idTest) throws SQLException
		{
			Connection cnx = null;
			PreparedStatement rqt = null;
			ResultSet rs = null;
			int nbSection = 0;
			
			try
			{
				cnx = AccesBase.getConnection();
				rqt = cnx.prepareStatement("select COUNT(*) as nbSection from section s where s.id_test = ?;");	
				rqt.setInt(1, idTest);
				rs = rqt.executeQuery();
				
				while (rs.next())
				{
					nbSection = rs.getInt("nbSection");				
				}
			}
			finally
			{
				if (rs!=null) rs.close();
				if (rqt!=null) rqt.close();
				if (cnx!=null) cnx.close();
			}
			
			return nbSection;
		}
	
		public static void add(Section uneSection) throws Exception {
			Connection cnx = null;
			PreparedStatement rqt = null;
			try {
				cnx = AccesBase.getConnection();
				rqt = cnx
					.prepareStatement("INSERT INTO section(nb_questions,id_test,id_theme) values(?,?,?);");
				rqt.setInt(1, uneSection.getNbQuestions());
				rqt.setInt(2, uneSection.getIdTest());
				rqt.setInt(3, uneSection.getIdTheme());
				rqt.executeUpdate();
			} finally {
				if (rqt != null)
					rqt.close();
				if (cnx != null)
					cnx.close();
			}
		}
	
	
}
