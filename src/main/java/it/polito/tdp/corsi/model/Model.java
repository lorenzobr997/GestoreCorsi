package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	public Model() {
		corsoDao = new CorsoDAO();
	}
	
	public List<Corso> getCorsiPeriodo(Integer pd){
		return corsoDao.getCorsiPeriodo(pd); 
	}
	
	public Map<Corso,Integer> getIscrittiPeriodo(Integer pd){
		return corsoDao.getIscrittiPeriodo(pd);
	}
}
