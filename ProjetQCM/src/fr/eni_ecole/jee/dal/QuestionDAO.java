package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni_ecole.jee.bean.Question;
import fr.eni_ecole.jee.bean.Theme;
import fr.eni_ecole.jee.util.AccesBase;

public class QuestionDAO 
{
	// Read
	public static Question getQuestionByReponseCandidat(int numReponseCandidat) throws SQLException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Question laQuestion = null;
		
		try
		{
			cnx = AccesBase.getConnection();		
			rqt = cnx.prepareStatement("SELECT q.* FROM question q INNER JOIN reponseCandidat rc ON q.id = rc.idQuestion WHERE rc.numReponseCandidat = ?");
			rqt.setInt(1, numReponseCandidat);
			rs = rqt.executeQuery();
			
			while (rs.next())
			{
				laQuestion = new Question(rs.getInt("id"), rs.getInt("id_theme"), rs.getString("enonce"), rs.getBoolean("type_reponse"), "");			
			}
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}		

		return laQuestion;
	}
}
