package SMLS.accesBD;

import java.sql.*;
import java.util.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import SMLS.accesBD.SConnection;
import SMLS.controleursVues.Liste_AbonneeControle.Abonnee;


public class AbonneeDAO {

	
	public boolean create(Abonnee c) throws SQLException{
		if(c==null) return false;
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt;
		try {
		stmt= cnx.prepareStatement("insert into abonnee values (?, ?, ?, ?, ?)");
		stmt.setInt(1,c.getNumAb());
		stmt.setString(2,c.getNom());
		stmt.setString(3,c.getPrenom());
		stmt.setString(4,c.getAdrAb());
		stmt.setDate(5, new java.sql.Date(c.getDateNais().getTime()));
		stmt.executeUpdate();
		stmt.close();
		return true;
		}
		catch(SQLException e) {//e.printStackTrace();
            return false;}
		
		
	}
	public boolean delete(Abonnee c) throws SQLException {
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("delete from abonnee where numAb=?");
		stmt.setInt(1,c.getNumAb());
		int n= stmt.executeUpdate();
		return n>0;
		
		
	}
	public Abonnee find(int n) throws SQLException {
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("select * from abonnee where numAb=?");
		stmt.setInt(1,n);
		ResultSet rs= stmt.executeQuery();
		Abonnee c=null;
		if(rs.next()) 
			c=new Abonnee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
		return c;
	}
	
	public Collection<Abonnee> findAll() {
		Collection<Abonnee> lignes= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		Abonnee ligne;
		try {
			PreparedStatement st= cnx.prepareStatement("select * from abonnee");
			ResultSet res= st.executeQuery();
			while (res.next()) {
				ligne=new Abonnee(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5));
				lignes.add(ligne);
				}
			st.close();
		} catch (SQLException e) {
		         //e.printStackTrace();
		}
		return lignes;//la collection retournée peut être vide
	}

	  public ObservableList<PieChart.Data> getAbonneeGraphStatistics() {
		  Connection cnx= SConnection.getInstance();
	        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
	        try {
	        	PreparedStatement st= cnx.prepareStatement("SELECT COUNT(*) FROM abonnee");

	            ResultSet rs = st.executeQuery();
	            if (rs.next()) {
	                int count = rs.getInt(1);
	                data.add(new PieChart.Data("Total Abonnée (" + count + ")", count));
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
	            PreparedStatement st= cnx.prepareStatement("SELECT COUNT(*) FROM Ligne WHERE numLig=?");
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
	  
	  public boolean update(Abonnee ln) {
		  Connection cnx= SConnection.getInstance();
	        try {
	            PreparedStatement stmt= cnx.prepareStatement("UPDATE abonnee SET nom=?, prenom=?, adrAb=?, dateNais=?   WHERE numAb=?");
	            stmt.setString(1, ln.getNom());
	            stmt.setString(2, ln.getPrenom());
	            stmt.setString(3, ln.getAdrAb());
	            stmt.setDate(4,  new java.sql.Date(ln.getDateNais().getTime()));
	            stmt.setInt(5, ln.getNumAb());
	            int res = stmt.executeUpdate();
	            return (res > 0);
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return false;
	    }
}