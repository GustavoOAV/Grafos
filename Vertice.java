package Grafo;

import java.util.ArrayList;

public class Vertice<TIPO> {
	private TIPO dado;
	private ArrayList<Aresta<TIPO>> arestaEntrada;
	private ArrayList<Aresta<TIPO>> arestaSaida;
	
	public Vertice(TIPO valor) {
		this.dado = valor;
		this.arestaEntrada = new ArrayList<Aresta<TIPO>>();
		this.arestaSaida = new ArrayList<Aresta<TIPO>>();
	}

	public TIPO getDado() {
		return dado;
	}

	public void setDado(TIPO dado) {
		this.dado = dado;
	}

	public ArrayList<Aresta<TIPO>> getArestasEntrada() {
		return arestaEntrada;
	}

	public void setArestasEntrada(ArrayList<Aresta<TIPO>> arestasEntrada) {
		this.arestaEntrada = arestasEntrada;
	}

	public ArrayList<Aresta<TIPO>> getArestaSaida() {
		return arestaSaida;
	}

	public void setArestaSaida(ArrayList<Aresta<TIPO>> arestaSaida) {
		this.arestaSaida = arestaSaida;
	}
	
	public void adicionarArestaEntrada(Aresta<TIPO> aresta) {
		this.arestaEntrada.add(aresta);
	}
	
	public void adicionaArestaSaida(Aresta<TIPO> aresta) {
		this.arestaSaida.add(aresta);
	}
}