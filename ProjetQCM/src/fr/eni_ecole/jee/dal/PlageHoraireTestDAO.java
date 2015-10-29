package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni_ecole.jee.util.AccesBase;

public class PlageHoraireTestDAO {
	public static void createPlageHoraireTestDAO(int idTest, int idPlage) throws SQLException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("insert into plageHoraireTest(id_plageHoraire,id_test) values(?,?);");	
			rqt.setInt(1, idPlage);
			rqt.setInt(2, idTest);
			rqt.executeUpdate();
		} finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	} 
}
