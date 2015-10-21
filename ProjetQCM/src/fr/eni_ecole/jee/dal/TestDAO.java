package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni_ecole.jee.bean.*;


public class TestDAO {

	public static void ajouter(Test test) throws Exception{
		Connection cnx=null;
		PreparedStatement rqt=null;
	}


	public static void supprimer(Test test) throws Exception{
		Connection cnx=null;
		PreparedStatement rqt=null;
	}

	public static void modifier(Test test) throws Exception{
		Connection cnx=null;
		PreparedStatement rqt=null;
	}
	
	public static Test rechercher(Test test) throws Exception{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs=null;
		return test;
	}
	
	public static Test rechercherParID(int id)
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Test monTest = new Test();
		
		return monTest;
		
	}
}
