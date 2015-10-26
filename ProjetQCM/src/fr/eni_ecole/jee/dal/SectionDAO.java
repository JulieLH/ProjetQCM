package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fr.eni_ecole.jee.bean.Section;
import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.util.AccesBase;

public class SectionDAO {

	public static ArrayList<Section> getByID(Test unTest) throws Exception
	{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs = null;
		ArrayList<Section> tabSection = null;
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT s.num_section,s.nb_questions,s.id_test,s.id_theme FROM section s where s.id_test = ?");
			rqt.setInt(1, unTest.getId());
			rs = rqt.executeQuery();
			while (rs.next())
			{
				//création d'une section a ajouté dans la liste
				Section uneSection = new Section(rs.getInt("s.num_section"),rs.getInt("s.nb_questions"),rs.getInt("s.id_test"),rs.getInt("s.id_theme"));
				tabSection.add(uneSection);
			}
		}
		finally
		{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return tabSection;
	}
	
	
	public static int getNbSectionByTest(Test unTest) throws Exception
	{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs = null;
		int nbSection = 0;
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT COUNT(*) AS nbSection FROM section s WHERE s.id_test = ?");
			rqt.setInt(1, unTest.getId());
			rs = rqt.executeQuery();
			while (rs.next())
			{
				nbSection = rs.getInt("nbSection");
			}
		}
		finally
		{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return nbSection;
	}
	
	
	
	
	
}
