package SMLS.accesBD;

import java.sql.*;
import java.util.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import SMLS.accesBD.SConnection;
import SMLS.controleursVues.Liste_AbonneeControle.Abonnee;
import SMLS.controleursVues.Liste_AbonnementControle.Abonnement;
import SMLS.controleursVues.Liste_LigneControle.Ligne;


public class AbonnementDAO {

	public boolean create(Abonnement c) throws SQLException{
		if(c==null) return false;
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt;
		try {
		stmt= cnx.prepareStatement("insert into abonnement values (?, ?, ?, ?)");
		stmt.setInt(1,c.getNumAbon());
		stmt.setDate(2,new java.sql.Date(c.getDateDeb().getTime()));
		stmt.setDate(3,new java.sql.Date(c.getDateFin().getTime()));
		stmt.setInt(4,c.getNumAb());
		stmt.executeUpdate();
		stmt.close();
		return true;
		}
		catch(SQLException e) {//e.printStackTrace();
            return false;}
		
		
	}
	public boolean delete(Abonnement c) throws SQLException {
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("delete from abonnement where numAbon=?");
		stmt.setInt(1,c.getNumAbon());
		int n= stmt.executeUpdate();
		return n>0;
		
		
	}
	public Abonnement find(int n) throws SQLException {
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("select * from abonnement where numAbon=?");
		stmt.setInt(1,n);
		ResultSet rs= stmt.executeQuery();
		Abonnement c=null;
		if(rs.next()) 
			c=new Abonnement(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4));
		return c;
	}
	
	public Collection<Abonnement> findAll() {
		Collection<Abonnement> lignes= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		Abonnement ligne;
		try {
			PreparedStatement st= cnx.prepareStatement("select * from abonnement");
			ResultSet rs= st.executeQuery();
			while (rs.next()) {
				ligne=new Abonnement(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4));
				lignes.add(ligne);
				}
			st.close();
		} catch (SQLException e) {
		         //e.printStackTrace();
		}
		return lignes;//la collection retournée peut être vide
	}

	  public ObservableList<PieChart.Data> getAbonnementGraphStatistics() {
		  Connection cnx= SConnection.getInstance();
	        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
	        try {
	        	PreparedStatement st= cnx.prepareStatement("SELECT COUNT(*) FROM abonnement");

	            ResultSet rs = st.executeQuery();
	            if (rs.next()) {
	                int count = rs.getInt(1);
	                data.add(new PieChart.Data("Total Abonnement (" + count + ")", count));
	            }
	           
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	  
	  public static boolean isLigneExists(Integer numL) {
		  Connection cnx= SConnection.getInstance();
	        try {
	            PreparedStatement st= cnx.prepareStatement("SELECT COUNT(*) FROM Abonnement WHERE numAbon=?");
	            st.setInt(1, numL);
	            ResultSet rs = st.executeQuery();
	            if (rs.next()) {
	                int count = rs.getInt(1);
	                System.out.println(count);
	                return (count > 0);
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return false;
	    }
	  
	  public boolean update(Abonnement ln) {
		  Connection cnx= SConnection.getInstance();
	        try {
	            PreparedStatement stmt= cnx.prepareStatement("UPDATE abonnement SET dateFin=? WHERE numAbon=?");

	            stmt.setDate(1,  new java.sql.Date(ln.getDateFin().getTime()));
	            stmt.setInt(2, ln.getNumAbon());
	            int res = stmt.executeUpdate();
	            return (res > 0);
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return false;
	    }
}