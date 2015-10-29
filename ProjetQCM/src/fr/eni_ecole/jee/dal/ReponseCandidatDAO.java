package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni_ecole.jee.bean.Inscription;
import fr.eni_ecole.jee.bean.Question;
import fr.eni_ecole.jee.bean.Reponse;
import fr.eni_ecole.jee.bean.ReponseCandidat;
import fr.eni_ecole.jee.bean.Test;
import fr.eni_ecole.jee.util.AccesBase;

public class ReponseCandidatDAO 
{
	// Read
	public static ArrayList<ReponseCandidat> getReponsesByInscription(int numInscription) throws SQLException
	{
		Connection cnx = null;
		PreparedStatement rqtQuestion = null;
		PreparedStatement rqtReponses = null;
		ResultSet rsQuestion = null;
		ResultSet rsReponses = null;
		// Déclaration de la liste attendue
		ArrayList<ReponseCandidat> lesReponsesCandidat = new ArrayList<ReponseCandidat>();
		
		try
		{
			// Récupération des questions posées
			cnx = AccesBase.getConnection();		
			rqtQuestion = cnx.prepareStatement("SELECT q.* FROM question q INNER JOIN reponseCandidat rc ON q.id = rc.idQuestion WHERE rc.numInscription = ?");
			rqtQuestion.setInt(1, numInscription);
			rsQuestion = rqtQuestion.executeQuery();
			
			// Pour chaque question posée
			while (rsQuestion.next())
			{
				ReponseCandidat uneReponseCandidat = new ReponseCandidat();
				ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();
				// On créé l'objet associé à la question
				Question laQuestion = new Question(rsQuestion.getInt("id"), rsQuestion.getInt("id_theme"), rsQuestion.getString("enonce"), rsQuestion.getBoolean("type_reponse"), rsQuestion.getString(""));
				
				// On récupère les réponses données par le candidat à cette question
				rqtReponses = cnx.prepareStatement("SELECT r.* FROM reponse r WHERE r.id IN (SELECT rc.idReponse FROM reponseCandidat rc WHERE rc.numInscription = ? AND rc.idQuestion = ?)");
				rqtReponses.setInt(1, numInscription);
				rqtReponses.setInt(2, laQuestion.getId());
				rsReponses = rqtReponses.executeQuery();
				
				// Pour chacune des réponses données
				while (rsReponses.next())					
				{
					// On créé l'objet associé à la réponse donnée 
					Reponse uneReponse = new Reponse(rsReponses.getInt("id"), rsReponses.getInt("id_question"), rsReponses.getString("libelle"), rsReponses.getBoolean("bonne_rep"));
					// Puis on l'ajoute dans la liste des réponses données
					lesReponses.add(uneReponse);
				}
				// Ajout de la question et de la liste des réponses répondues par le candidat
				uneReponseCandidat.setLaQuestion(laQuestion);
				uneReponseCandidat.setLesReponses(lesReponses);
				
				// Ajout à la liste de toutes les réponses données au test
				lesReponsesCandidat.add(uneReponseCandidat);
			}
		}
		finally
		{
			if (rsQuestion!=null) rsQuestion.close();
			if (rsReponses!=null) rsReponses.close();
			if (rqtQuestion!=null) rqtQuestion.close();
			if (rqtReponses!=null) rqtReponses.close();
			if (cnx!=null) cnx.close();
		}
		
		return lesReponsesCandidat;
	}
	
	/**
	 * Get reponse by ID rep
	 * @return
	 * @throws SQLException
	 */
		public static Reponse getRepByID(int idRep) throws SQLException {
			Connection cnx = null;
			PreparedStatement rqt = null;
			ResultSet rs = null;
			Reponse reponses = new Reponse();

			try {
				cnx = AccesBase.getConnection();
				rqt = cnx.prepareStatement("SELECT * FROM reponse WHERE id =?");
				rqt.setInt(1, idRep);
				rs = rqt.executeQuery();
				
				while (rs.next()) {
					reponses = new Reponse(rs.getInt("id"), rs.getInt("id_question"),
							rs.getString("libelle"), rs.getBoolean("bonne_rep"));
					
				}
			} finally {
				if (rs != null)
					rs.close();
				if (rqt != null)
					rqt.close();
				if (cnx != null)
					cnx.close();
			}

			return reponses;
		}
		
		// Create
		public static void add(int numInscri, int idQ, int idRep) throws SQLException
		{
			Connection cnx = null;
			PreparedStatement rqt = null;

			try
			{
				cnx = AccesBase.getConnection();
				rqt = cnx.prepareStatement("INSERT INTO reponseCandidat(numInscription,idQuestion,idReponse ) VALUES (?,?,?)");
				rqt.setInt(1, numInscri);
				rqt.setInt(2, idQ);
				rqt.setInt(3, idRep);
				rqt.executeUpdate();
			}
			finally
			{
				if (rqt!=null) rqt.close();
				if (cnx!=null) cnx.close();
			}
		}
}
