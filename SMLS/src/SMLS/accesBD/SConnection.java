package SMLS.accesBD;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;


public class SConnection {
	private static String url="jdbc:mysql://localhost/smls?autoReconnect=true&useSSL=false";
	private static String utilisateur="root";
	private static String motPasse="root";
	private static Connection cnx;

	private SConnection()  {
		try {
			if(cnx==null || cnx.isClosed()) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		try {
			cnx= DriverManager.getConnection(url, utilisateur, motPasse);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnx;
	}

	public static void close()  {
		try {
			if(cnx!=null && !cnx.isClosed())
				cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

