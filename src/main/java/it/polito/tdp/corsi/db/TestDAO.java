package it.polito.tdp.corsi.db;

import it.polito.tdp.corsi.model.Corso;

public class TestDAO {
	
	public static void main(String[] args) {
		StudenteDAO dao = new StudenteDAO();
		System.out.println(dao.getStudenteCorso(new Corso("01NBAPG", null, null, null)));
	}

}
