package SMLS.accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import SMLS.controleursVues.Liste_ArretControle.Arret;


public class ArretDAO {
	
	public boolean create(Arret c) throws SQLException{
		if(c==null) return false;
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt;
		try {
		stmt= cnx.prepareStatement("insert into arret values (?, ?, ?, ?)");
		stmt.setString(1,c.getNumAr());
		stmt.setInt(2,c.getNumLig());
		stmt.setInt(3,c.getNumZone());
		stmt.setInt(4,c.getNumSt());
		stmt.executeUpdate();
		stmt.close();
		return true;
		}
		catch(SQLException e) {//e.printStackTrace();
            return false;
        }
	}
	
	public Arret find(String n) throws SQLException {
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("select * from arret where numAr=?");
		stmt.setString(1,n);
		ResultSet rs= stmt.executeQuery();
		Arret c=null;
		if(rs.next()) 
			c=new Arret(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
		return c;
	}
	
	public Collection<Arret> findAll() {
		Collection<Arret> arrets= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		Arret arret;
		try {
			PreparedStatement st= cnx.prepareStatement("select * from arret");
			ResultSet res= st.executeQuery();
			while (res.next()) {
				arret=new Arret(res.getString(1), res.getInt(2), res.getInt(3), res.getInt(4));
				arrets.add(arret);
				}
			st.close();
		} catch (SQLException e) {
		         //e.printStackTrace();
		}
		return arrets;//la collection retournée peut être vide
	}
	
	public static boolean isArretExists(String numA) {
		  Connection cnx= SConnection.getInstance();
	        try {
	            PreparedStatement st= cnx.prepareStatement("SELECT COUNT(*) FROM Arret WHERE numAr=?");
	            st.setString(1, numA);
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

	public boolean update(Arret ln) {
		  Connection cnx= SConnection.getInstance();
	        try {
	        	
	            PreparedStatement stmt= cnx.prepareStatement("UPDATE Arret SET numZone=? , numSt=? WHERE numAr=?");
	            stmt.setInt(1, ln.getNumZone());
	            stmt.setInt(2, ln.getNumSt());
	            stmt.setString(3, ln.getNumAr());
	            int res = stmt.executeUpdate();
	            return (res > 0);
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return false;
	    }
	
	public boolean delete(Arret c) throws SQLException {
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("delete from Arret where numAr=?");
		stmt.setString(1,c.getNumAr());
		int n= stmt.executeUpdate();
		return n>0;
		
	}
	
	/*public Collection<Object> findAlljoin() {
		Collection<Object> arrets= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		Object arret;
		try {
			PreparedStatement st= cnx.prepareStatement("select a.numAr , l.nomter , a.numZone , s.nomSt from arret a , ligne l , station s where a.numLig=l.numLig and a.numSt=s.numSt");
			ResultSet res= st.executeQuery();
			while (res.next()) {
				arret=new Object(res.getObject( getString(1), res.getString(2), res.getInt(3), res.getString(4));
				arrets.add(arret);
				}
			st.close();
		} catch (SQLException e) {
		         //e.printStackTrace();
		}
		return arrets;//la collection retournée peut être vide
	}*/

}
