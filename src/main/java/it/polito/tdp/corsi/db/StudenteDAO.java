package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getStudenteCorso(Corso corso){
		
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM iscrizione i, studente s "
				+ "WHERE s.matricola = i.matricola AND i.codins = ?";
		
		List<Studente> res = new LinkedList<Studente>();
		try {
			Connection con = DBConnect.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
				res.add(s);
			}
			rs.close();
			st.close();
			con.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return res;
	}

}
