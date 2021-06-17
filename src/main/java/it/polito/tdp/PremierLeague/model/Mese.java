package it.polito.tdp.PremierLeague.model;

public class Mese {
	private int mese;
	private String nome;
	public int getMese() {
		return mese;
	}
	public void setMese(int mese) {
		this.mese = mese;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Mese(int mese, String nome) {
		super();
		this.mese = mese;
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "Mese [nome=" + nome + "]";
	}
	
	

}
