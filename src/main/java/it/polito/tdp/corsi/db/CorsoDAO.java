package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	public List<Corso> getCorsiPeriodo(Integer n){
		String sql = "SELECT * "
				+ "FROM corso "
				+ "WHERE pd = ?";
		List <Corso> res = new ArrayList<Corso>();
		try {
			
			Connection con = DBConnect.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, n);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				res.add(c);
			}
			
			rs.close();
			st.close();
			con.close();
			
		}catch(SQLException e) {
			
			throw new RuntimeException (e);
		
		}
		return res;
	}
	
	public Map<Corso,Integer> getIscrittiPeriodo(Integer n){
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS tot "
				+ "FROM iscrizione i, corso c "
				+ "WHERE c.pd = ? AND c.codins = i.codins "
				+ "GROUP BY c.codins, c.crediti, c.nome, c.pd";
		Map<Corso,Integer> res = new HashMap<Corso,Integer>();
		try {
			
			Connection con = DBConnect.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, n);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				Integer i = rs.getInt("tot"); 
				res.put(c,i);
			}
			
			rs.close();
			st.close();
			con.close();
			
		}catch(SQLException e) {
			
			throw new RuntimeException (e);
		
		}
		return res;
	}

	public boolean esisteCorso(Corso corso) {
		String sql = "SELECT * FROM corso WHERE codins = ?";
		
		try {
			Connection con = DBConnect.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				
				rs.close();
				st.close();
				con.close();
				
				return true;
			}else {
				
				rs.close();
				st.close();
				con.close();
				
				return false;
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

