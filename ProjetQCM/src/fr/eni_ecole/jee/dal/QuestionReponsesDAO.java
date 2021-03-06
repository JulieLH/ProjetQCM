package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni_ecole.jee.bean.Question;
import fr.eni_ecole.jee.bean.QuestionReponses;
import fr.eni_ecole.jee.bean.Reponse;
import fr.eni_ecole.jee.util.AccesBase;

public class QuestionReponsesDAO 
{
	// Read
	public static QuestionReponses getReponsesByQuestion(int idQuestion) throws SQLException
	{
		Connection cnx = null;
		PreparedStatement rqtQuestion = null;
		PreparedStatement rqtReponses = null;
		ResultSet rsQuestion = null;
		ResultSet rsReponses = null;
		Question laQuestion = null;
		ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();
		QuestionReponses result = new QuestionReponses();
		
		try
		{
			cnx = AccesBase.getConnection();		
			rqtQuestion = cnx.prepareStatement("SELECT * FROM question WHERE id = ?");
			rqtQuestion.setInt(1, idQuestion);
			rsQuestion = rqtQuestion.executeQuery();
			
			while (rsQuestion.next())
			{
				laQuestion = new Question(rsQuestion.getInt("id"), rsQuestion.getInt("id_theme"), rsQuestion.getString("enonce"), rsQuestion.getBoolean("type_reponse"), rsQuestion.getString("image"));
				result.setLaQuestion(laQuestion);
			}
			
			rqtReponses = cnx.prepareStatement("SELECT * FROM reponse WHERE id_question = ?");
			rqtReponses.setInt(1, idQuestion);
			rsReponses = rqtReponses.executeQuery();
			Reponse uneReponse;
			
			while (rsReponses.next())
			{
				uneReponse = new Reponse(rsReponses.getInt("id"), rsReponses.getInt("id_question"), rsReponses.getString("libelle"), rsReponses.getBoolean("bonne_rep"));
				lesReponses.add(uneReponse);				
			}
			
			result.setLesReponses(lesReponses);			
		}
		finally
		{
			if (rsQuestion!=null) rsQuestion.close();
			if (rsReponses!=null) rsReponses.close();
			if (rqtQuestion!=null) rqtQuestion.close();
			if (rqtReponses!=null) rqtReponses.close();
			if (cnx!=null) cnx.close();
		}
		
		return result;
	}
	
	// Read
	public static ArrayList<QuestionReponses> getQuestionsReponsesByTest(int idTest) throws SQLException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<Question> lesQuestions = new ArrayList<Question>();
		ArrayList<QuestionReponses> lesQuestionsReponses = new ArrayList<QuestionReponses>();
		
		try		
		{
			cnx = AccesBase.getConnection();		
			rqt = cnx.prepareStatement("SELECT q.* FROM question q INNER JOIN theme th ON q.id_theme = th.id_theme INNER JOIN section s ON th.id_theme = s.id_theme INNER JOIN test t ON s.id_test = t.id WHERE t.id = ?");
			rqt.setInt(1, idTest);
			rs = rqt.executeQuery();
			
			while (rs.next())
			{
				Question uneQuestion = new Question(rs.getInt("id"), rs.getInt("id_theme"), rs.getString("enonce"), rs.getBoolean("type_reponse"), rs.getString("image"));
				lesQuestions.add(uneQuestion);
			}
			
			for (Question q : lesQuestions)
			{
				QuestionReponses uneQuestionReponse = getReponsesByQuestion(q.getId());
				lesQuestionsReponses.add(uneQuestionReponse);
			}			
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return lesQuestionsReponses;
	}
}
