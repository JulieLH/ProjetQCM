package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni_ecole.jee.bean.*;
import fr.eni_ecole.jee.util.AccesBase;

public class TestDAO {
	// Create
	public static int add(Test unTest) throws Exception {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		int retourIndexTest = 0;
		try {
			cnx = AccesBase.getConnection();
			rqt = cnx
				.prepareStatement("INSERT INTO test(libelle, duree, seuil_min,seuil_max) VALUES (?,?,?,?) SELECT SCOPE_IDENTITY()");
			rqt.setString(1, unTest.getLibelle());
			rqt.setInt(2, unTest.getDuree());
			rqt.setInt(3, unTest.getSeuilMin());
			rqt.setInt(4, unTest.getSeuilMax());
			rs = rqt.executeQuery();
			while (rs.next())
			{
				retourIndexTest = rs.getInt(0);
			}
			return retourIndexTest;
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
	}

	// Delete
	public static void remove(Test unTest) throws Exception {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("DELETE FROM test WHERE id = ?");
			rqt.setInt(1, unTest.getId());
			rqt.executeUpdate();
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
	}

	// Update
	public static void update(Test unTest) throws Exception {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = AccesBase.getConnection();
			rqt = cnx
					.prepareStatement("UPDATE test SET libelle = ?,duree = ?,seuil_min = ?,seuil_max = ? WHERE id = ?");
			rqt.setString(1, unTest.getLibelle());
			rqt.setInt(2, unTest.getDuree());
			rqt.setInt(3, unTest.getSeuilMin());
			rqt.setInt(4, unTest.getSeuilMax());
			rqt.setInt(5, unTest.getId());
			rqt.executeUpdate();
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
	}

	// Read
	public static Test getByID(int idTest) throws Exception {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Test monTest = null;
		try {
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT * from test WHERE id= ?");
			rqt.setInt(1, idTest);
			rs = rqt.executeQuery();
			while (rs.next()) {
				monTest = new Test(rs.getInt("id"), rs.getString("libelle"),
						rs.getInt("duree"), rs.getInt("seuil_min"),
						rs.getInt("seuil_max"));
			}
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
		return monTest;
	}

	// Recupere tout les tests
	public static ArrayList<Test> getAll() throws SQLException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		ArrayList<Test> tests = new ArrayList<Test>();

		try {
			cnx = AccesBase.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery("SELECT * FROM test");
			Test unTest;
			while (rs.next()) {
				unTest = new Test(rs.getInt("id"), rs.getString("libelle"),
						rs.getInt("duree"), rs.getInt("seuil_min"),
						rs.getInt("seuil_max"));
				tests.add(unTest);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}

		return tests;
	}
	
	//Recupere la liste des tests d'un utilisateur via l'id du theme
	public static ArrayList<Test> getTestsByTheme(int idTheme)
			throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<Test> lesTests = new ArrayList<Test>();

		try {
			cnx = AccesBase.getConnection();
			rqt = cnx
					.prepareStatement("SELECT t.* FROM TEST t INNER JOIN SECTION s ON t.id = s.id_test INNER JOIN THEME th ON s.id_theme = th.id_theme WHERE th.id_theme = ?");
			rqt.setInt(1, idTheme);
			rs = rqt.executeQuery();
			Test unTest;

			while (rs.next()) {
				unTest = new Test(rs.getInt("id"), rs.getString("libelle"),
						rs.getInt("duree"), rs.getInt("seuil_min"),
						rs.getInt("seuil_max"));
				lesTests.add(unTest);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}

		return lesTests;
	}
	

	// Récupère le test lié à une inscription
	public static Test getTestByInscription(int numInscription) throws SQLException 
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Test unTest = null;

		try 
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT t.* FROM TEST t INNER JOIN INSCRIPTION i ON t.id = i.id_test WHERE i.num_inscription = ?");
			rqt.setInt(1, numInscription);
			rs = rqt.executeQuery();

			while (rs.next()) 
			{
				unTest = new Test(rs.getInt("id"), rs.getString("libelle"),	rs.getInt("duree"), rs.getInt("seuil_min"),	rs.getInt("seuil_max"));
			}
		} 
		finally 
		{
			if (rs != null) rs.close();
			if (rqt != null) rqt.close();
			if (cnx != null) cnx.close();
		}

		return unTest;
	}

	//Recupere la liste des tests d'un utilisateur via son ID
	public static ArrayList<Test> getTestsByUser(int idUtilisateur)
			throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<Test> userTests = new ArrayList<Test>();

		try {
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement(" select * " + "from inscription "
					+ "inner join test on test.id = inscription.id_test "
					+ "where id_utilisateur = ? ");

			rqt.setInt(1, idUtilisateur);
			rs = rqt.executeQuery();
			Test unTest;

			while (rs.next()) {
				unTest = new Test(rs.getInt("id"), rs.getString("libelle"),
						rs.getInt("duree"), rs.getInt("seuil_min"),
						rs.getInt("seuil_max"));
				userTests.add(unTest);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}

		return userTests;
	}

	//Creer une liste de questions aléatoires en fonction de chaque section avec en parametre
	//le nombre de questions , l'id du test et l'id du theme 
	public static ArrayList<Question> getListQuestion(int nbQuestion,int idTest, int idTheme) throws SQLException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<Question> lesQuestions = new ArrayList<Question>();
		
		try
		{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT TOP (?) question.id, question.enonce, question.id_theme, question.image, question.type_reponse FROM section INNER JOIN theme ON section.id_theme = theme.id_theme INNER JOIN question ON theme.id_theme = question.id_theme WHERE id_test = ? AND section.id_theme = ? ORDER BY NEWID() "); 

			rqt.setInt(1, nbQuestion);
			rqt.setInt(2, idTest);
			rqt.setInt(3, idTheme);
			rs = rqt.executeQuery();
			
			Question uneQuestion;
			while (rs.next())
			{		
				
				uneQuestion = new Question(rs.getInt("id"),rs.getInt("id_theme"),rs.getString("enonce"),rs.getBoolean("type_reponse"),rs.getString("image"));
				lesQuestions.add(uneQuestion);
				
			}
		}
		finally
		{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return lesQuestions;
	}
	
	// Récupère le nombre de questions posées total du test
		public static int getNbQuestions(int idTest) throws Exception 
		{
			Connection cnx = null;
			PreparedStatement rqt = null;
			ResultSet rs = null;
			int nbQuestions = 0;
			
			try 
			{
				cnx = AccesBase.getConnection();
				rqt = cnx.prepareStatement("SELECT SUM(nb_questions) nbQuestions FROM section WHERE id_test = ?");
				rqt.setInt(1, idTest);
				rs = rqt.executeQuery();
				while (rs.next()) 
				{
					nbQuestions = rs.getInt(1);
				}
			} 
			finally 
			{
				if (rqt != null) rqt.close();
				if (cnx != null) cnx.close();
			}
			
			return nbQuestions;
		}
	
	public static int getNumInscri(int idUtil, int idTest) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		int numInscription = 0;

		try {
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT num_inscription FROM inscription WHERE id_utilisateur =? AND id_test=?");
			rqt.setInt(1, idUtil);
			rqt.setInt(2, idTest);
			rs = rqt.executeQuery();
			
			while (rs.next()) {
				numInscription = rs.getInt("num_inscription");
				
			}
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}

		return numInscription;
	}
}
