package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	PremierLeagueDAO dao = new PremierLeagueDAO();
	Graph <Match,DefaultWeightedEdge> grafo;
	Map<Integer, Match> idMap= new LinkedHashMap<>();
	List<Arco> migliori = new LinkedList<>();
	List<Match> migliore;
	List<Match> prova;
	
	double pesoTot;
	int pesoParz;
	
	
	
	public void listaMigliore(Match m1, Match m2){
		migliore = new LinkedList<>();
		prova = new LinkedList<>();
		Match finale = m2;
		prova.add(m1);
		pesoTot = 0;
		pesoParz=0;
		ricorsione(prova, 0, finale, pesoParz);
		
		
	}
	
	public void ricorsione(List <Match> prova, int livello, Match finale, double pesoParz) {
		if(finale.equals(prova.get(livello))) {
			if(pesoTot==0) {
				pesoTot=pesoParz;
				migliore.clear();
				migliore.addAll(prova);
				return;
			}else if(pesoTot<pesoParz){
				pesoTot=pesoParz;
				migliore.clear();
				migliore.addAll(prova);
				return;
			}else if(pesoTot>=pesoParz) {
				return;
				
			}}
			
			for(Match m:Graphs.neighborListOf(grafo, prova.get(livello))) {
				if(!prova.contains(m)&&!prova.get(livello).teamAwayID.equals(m.teamAwayID)&&!prova.get(livello).teamHomeID.equals(m.teamHomeID)&&!prova.get(livello).teamHomeID.equals(m.teamAwayID)) {
					{
						prova.add(m);
						livello++;
						double val=grafo.getEdgeWeight(grafo.getEdge(prova.get(livello-1), m));
						pesoParz+=val;
						ricorsione(prova, livello, finale, pesoParz);
						
						prova.remove(livello);
						pesoParz-=val;
						livello--;
						
					}
					
					
				}
				
				
			}
				
			
		}
		
		
		
	
	public List<Match> getMigliore() {
		return migliore;
	}

	public void setMigliore(List<Match> migliore) {
		this.migliore = migliore;
	}

	public double getPesoTot() {
		return pesoTot;
	}

	public void setPesoTot(double pesoTot) {
		this.pesoTot = pesoTot;
	}

	public void creaGrafo(int minGioc,int mese) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		
		Graphs.addAllVertices(grafo, dao.listAllMatchesMonth(mese));
		for(Match m: dao.listAllMatchesMonth(mese))
			if(!idMap.containsKey(m.getMatchID()))
				idMap.put(m.matchID, m);
		
		
		for(Arco a: dao.listArchi(minGioc,mese, idMap)) {
			if(!grafo.containsEdge(grafo.getEdge(a.getM1(), a.getM2())) && !grafo.containsEdge(grafo.getEdge(a.getM2(), a.getM1()))) {
				Graphs.addEdge(grafo, a.getM1(), a.getM2(), a.getPeso());
			    if(migliori.isEmpty())
			    	migliori.add(a);
			    else if(migliori.get(0).getPeso()==a.getPeso())
			    	migliori.add(a);
			    else if(migliori.get(0).getPeso()<a.getPeso())
			    {
			    	migliori.clear();
			    	migliori.add(a);
			    }
				
			}
		}
		}
		
		public String numVA() {
			String s = "VERICI E ARCHI: "+ grafo.vertexSet().size() + " "+ grafo.edgeSet().size();
			return s; 
			
		}
		
		public String matchMigliori() {
			
			String s="";
			for(Arco a: this.migliori) {
				s = s + a.getM1()+ " - " + a.getM2()+" "+ a.getPeso()+"\n";
			}
				return s;
			}
		
		public Set<Match> getVertexSet(){
			
			return this.grafo.vertexSet();
			
		}
		
		
	
	
}
