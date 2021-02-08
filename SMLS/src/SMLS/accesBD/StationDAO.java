package SMLS.accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import SMLS.controleursVues.Liste_StationControle.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class StationDAO {

	public boolean create(Station c) throws SQLException{
		if(c==null) return false;
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt;
		try {
		stmt= cnx.prepareStatement("insert into station values (?, ?)");
		stmt.setInt(1,c.getNumSt());
		stmt.setString(2,c.getNomSt());
		stmt.executeUpdate();
		stmt.close();
		return true;
		}
		catch(SQLException e) {//e.printStackTrace();
            return false;}
		
		
	}
	public boolean delete(Station c) throws SQLException {
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("delete from station where numSt=?");
		stmt.setInt(1,c.getNumSt());
		int n= stmt.executeUpdate();
		return n>0;
		
		
	}
	public Station find(int n) throws SQLException {
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("select * from station where numSt=?");
		stmt.setInt(1,n);
		ResultSet rs= stmt.executeQuery();
		Station c=null;
		if(rs.next()) 
			c=new Station(rs.getInt(1), rs.getString(2));
		return c;
	}
	
	public Collection<Station> findAll() {
		Collection<Station> stations= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		Station station;
		try {
			PreparedStatement st= cnx.prepareStatement("select * from station");
			ResultSet res= st.executeQuery();
			while (res.next()) {
				station=new Station(res.getInt(1), res.getString(2));
				stations.add(station);
				}
			st.close();
		} catch (SQLException e) {

		         //e.printStackTrace();
		}
		return stations;//la collection retournée peut être vide
	}
	
	 public static boolean isStationExists(Integer numS) {
		  Connection cnx= SConnection.getInstance();
	        try {
	            PreparedStatement st= cnx.prepareStatement("SELECT COUNT(*) FROM Station WHERE numSt=?");
	            st.setInt(1, numS);
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
	  
	  public boolean update(Station ln) {
		  Connection cnx= SConnection.getInstance();
	        try {
	            PreparedStatement stmt= cnx.prepareStatement("UPDATE Station SET nomSt=? WHERE numSt=?");
	            stmt.setString(1, ln.getNomSt());
	            stmt.setInt(2, ln.getNumSt());
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
				PreparedStatement st= cnx.prepareStatement("select numSt from station");
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
}

