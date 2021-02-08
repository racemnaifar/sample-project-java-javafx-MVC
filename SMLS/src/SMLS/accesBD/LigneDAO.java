package SMLS.accesBD;

import java.sql.*;
import java.util.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import SMLS.accesBD.SConnection;
import SMLS.controleursVues.Liste_LigneControle.Ligne;


public class LigneDAO {

	
	public boolean create(Ligne c) throws SQLException{
		if(c==null) return false;
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt;
		try {
		stmt= cnx.prepareStatement("insert into ligne values (?, ?)");
		stmt.setInt(1,c.getNumLig());
		stmt.setString(2,c.getNomter());
		stmt.executeUpdate();
		stmt.close();
		return true;
		}
		catch(SQLException e) {//e.printStackTrace();
            return false;}
		
		
	}
	public boolean delete(Ligne c) throws SQLException {
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("delete from ligne where numLig=?");
		stmt.setInt(1,c.getNumLig());
		int n= stmt.executeUpdate();
		return n>0;
		
		
	}
	public Ligne find(int n) throws SQLException {
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("select * from ligne where numLig=?");
		stmt.setInt(1,n);
		ResultSet rs= stmt.executeQuery();
		Ligne c=null;
		if(rs.next()) 
			c=new Ligne(rs.getInt(1), rs.getString(2));
		return c;
	}
	
	public Collection<Ligne> findAll() {
		Collection<Ligne> lignes= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		Ligne ligne;
		try {
			PreparedStatement st= cnx.prepareStatement("select * from ligne");
			ResultSet res= st.executeQuery();
			while (res.next()) {
				ligne=new Ligne(res.getInt(1), res.getString(2));
				lignes.add(ligne);
				}
			st.close();
		} catch (SQLException e) {
		         //e.printStackTrace();
		}
		return lignes;//la collection retournée peut être vide
	}

	  public ObservableList<PieChart.Data> getLigneGraphStatistics() {
		  Connection cnx= SConnection.getInstance();
	        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
	        try {
	        	PreparedStatement st= cnx.prepareStatement("SELECT COUNT(*) FROM ligne");

	            ResultSet rs = st.executeQuery();
	            if (rs.next()) {
	                int count = rs.getInt(1);
	                data.add(new PieChart.Data("Total Books (" + count + ")", count));
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
	  
	  public boolean update(Ligne ln) {
		  Connection cnx= SConnection.getInstance();
	        try {
	            PreparedStatement stmt= cnx.prepareStatement("UPDATE Ligne SET nomter=? WHERE numLig=?");
	            stmt.setString(1, ln.getNomter());
	            stmt.setInt(2, ln.getNumLig());
	            int res = stmt.executeUpdate();
	            return (res > 0);
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return false;
	    }
	  
	  
		public ObservableList<String> findAllob() {
	        ObservableList<String> lignes = FXCollections.observableArrayList();
			Connection cnx= SConnection.getInstance();
			try {
				PreparedStatement st= cnx.prepareStatement("select numLig from ligne");
				ResultSet res= st.executeQuery();
				while (res.next()) {
					lignes.add(res.getString(1));
					}
				st.close();
			} catch (SQLException e) {
			         //e.printStackTrace();
			}
			return lignes;//la collection retournée peut être vide
		}
		
		public static String findNom(Integer n) throws SQLException {
			Connection cnx= SConnection.getInstance();
			PreparedStatement stmt= cnx.prepareStatement("select nomter from ligne where numLig=?");
			stmt.setInt(1,n);
			ResultSet rs= stmt.executeQuery();
			String c=null;
			if(rs.next()) 
				c=rs.getString(1);
			return c;
		}
}