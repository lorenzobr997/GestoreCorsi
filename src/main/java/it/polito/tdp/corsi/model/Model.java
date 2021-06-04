package it.polito.tdp.corsi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}
	
	public List<Corso> getCorsiPeriodo(Integer pd){
		return corsoDao.getCorsiPeriodo(pd); 
	}
	
	public Map<Corso,Integer> getIscrittiPeriodo(Integer pd){
		return corsoDao.getIscrittiPeriodo(pd);
	}
	
	public List<Studente> getStudentiCorso(String corso){
		return studenteDao.getStudenteCorso(new Corso (corso, null, null, null));
	}

	public Map<String, Integer> getDivisione(String codice){
		Map<String, Integer> divisione = new HashMap<String, Integer>();
		List<Studente> studenti = this.getStudentiCorso(codice);
		for (Studente s : studenti) {
			if(s.getCDS() != null && !s.getCDS().equals("")) {
				if(divisione.get(s.getCDS()) == null) {
					divisione.put(s.getCDS(), 1);
				}else {
					divisione.put(s.getCDS(), divisione.get(s.getCDS())+1);
				}
			}
		}
		return divisione;
	}
	
	public boolean esisteCorso(String codice) {
		return corsoDao.esisteCorso(new Corso (codice, null, null, null));
	}
}
