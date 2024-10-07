package Grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;

public class ExemploGrafo {
	public static void main(String[] args) {
		Grafo<String> grafo = new Grafo<String>();

		// Adicionando os vértices
		grafo.adicionarVertice("Entrada");
		grafo.adicionarVertice("Praça 1");
		grafo.adicionarVertice("Praça 2");
		grafo.adicionarVertice("Praça 3");
		grafo.adicionarVertice("Acadêmico 1");
		grafo.adicionarVertice("Acadêmico 2");
		grafo.adicionarVertice("Biblioteca");
		grafo.adicionarVertice("Auditório");

		// Adicionando as arestas
		grafo.adicionaAresta(1, "Entrada", "Biblioteca");
		grafo.adicionaAresta(2, "Entrada", "Praça 1");
		grafo.adicionaAresta(2, "Entrada", "Acadêmico 1");
		grafo.adicionaAresta(2, "Praça 1", "Entrada");
		grafo.adicionaAresta(1, "Praça 1", "Biblioteca");
		grafo.adicionaAresta(1, "Praça 1", "Acadêmico 1");
		grafo.adicionaAresta(2, "Praça 1", "Acadêmico 2");
		grafo.adicionaAresta(1, "Praça 2", "Acadêmico 1");
		grafo.adicionaAresta(2, "Praça 2", "Praça 3");
		grafo.adicionaAresta(1, "Praça 2", "Acadêmico 2");
		grafo.adicionaAresta(1, "Praça 2", "Auditório");
		grafo.adicionaAresta(1, "Praça 3", "Acadêmico 2");
		grafo.adicionaAresta(2, "Praça 3", "Praça 2");
		grafo.adicionaAresta(1, "Biblioteca", "Acadêmico 1");
		grafo.adicionaAresta(1, "Biblioteca", "Praça 1");
		grafo.adicionaAresta(1, "Biblioteca", "Entrada");
		grafo.adicionaAresta(1, "Acadêmico 1", "Auditório");
		grafo.adicionaAresta(1, "Acadêmico 1", "Acadêmico 2");
		grafo.adicionaAresta(1, "Acadêmico 1", "Praça 2");
		grafo.adicionaAresta(1, "Acadêmico 1", "Entrada");	
		grafo.adicionaAresta(1, "Acadêmico 1", "Praça 1");
		grafo.adicionaAresta(1, "Acadêmico 1", "Biblioteca");
		grafo.adicionaAresta(1, "Acadêmico 2", "Praça 3");
		grafo.adicionaAresta(1, "Acadêmico 2", "Praça 2");
		grafo.adicionaAresta(1, "Acadêmico 2", "Acadêmico 1");
		grafo.adicionaAresta(1, "Auditório", "Acadêmico 1");
		grafo.adicionaAresta(1, "Auditório", "Praça 2");

		// Calcular o caminho mais curto
		String[] valores = {"Entrada", "Praça 1", "Praça 2", "Praça 3", "Acadêmico 1", "Acadêmico 2", "Biblioteca", "Auditório"};
		Object escolha1 = JOptionPane.showInputDialog(null, "Escolha uma origem: ",
				"Calcula rota", JOptionPane.WARNING_MESSAGE, null, valores, valores);
		String origem = escolha1 + "";
		Object escolha2 = JOptionPane.showInputDialog(null, "Escolha um destino: ",
				"Calcula rota", JOptionPane.WARNING_MESSAGE, null, valores, valores);
		String destino = escolha2 + "";
		Map<Vertice<String>, Double> distancias = dijkstra(grafo, origem);

		// Exibir o caminho mais curto e o tempo total
		System.out.println("Caminho mais curto de " + origem + " para " + destino + ":");
		ArrayList<Vertice<String>> caminho = obterCaminhoMaisCurto(grafo, origem, destino, distancias);
		for (Vertice<String> vertice : caminho) {
			System.out.print(vertice.getDado() + " -> ");
		}
		System.out.println("\nTempo total: " + distancias.get(grafo.getVertice(destino)) + " minutos");
	}

	// Algoritmo de Dijkstra para calcular as distâncias mais curtas
	public static <TIPO> Map<Vertice<TIPO>, Double> dijkstra(Grafo<TIPO> grafo, TIPO origem) {
		Map<Vertice<TIPO>, Double> distancias = new HashMap<>();
		PriorityQueue<Vertice<TIPO>> filaPrioridade = new PriorityQueue<>((v1, v2) -> {
			double diferenca = distancias.get(v1) - distancias.get(v2);
			if (diferenca == 0)
				return 0;
			return diferenca < 0 ? -1 : 1;
		});

		// Inicialização
		for (Vertice<TIPO> vertice : grafo.getVertices()) {
			distancias.put(vertice, Double.POSITIVE_INFINITY);
			filaPrioridade.add(vertice);
		}
		distancias.put(grafo.getVertice(origem), 0.0);

		// Algoritmo de Dijkstra
		while (!filaPrioridade.isEmpty()) {
			Vertice<TIPO> vertice = filaPrioridade.poll();
			for (Aresta<TIPO> aresta : vertice.getArestaSaida()) {
				Vertice<TIPO> vizinho = aresta.getFim();
				double novaDistancia = distancias.get(vertice) + aresta.getPeso();
				if (novaDistancia < distancias.get(vizinho)) {
					filaPrioridade.remove(vizinho);
					distancias.put(vizinho, novaDistancia);
					filaPrioridade.add(vizinho);
				}
			}
		}
		return distancias;
	}

	// Método para obter o caminho mais curto a partir das distâncias calculadas
	public static <TIPO> ArrayList<Vertice<TIPO>> obterCaminhoMaisCurto(Grafo<TIPO> grafo, TIPO origem, TIPO destino, Map<Vertice<TIPO>, Double> distancias) {
	    ArrayList<Vertice<TIPO>> caminho = new ArrayList<>();
	    Vertice<TIPO> atual = grafo.getVertice(destino);
	    caminho.add(atual);
	    while (!atual.getDado().equals(origem)) {
	        for (Aresta<TIPO> aresta : atual.getArestasEntrada()) {
	            Vertice<TIPO> vizinho = aresta.getInicio();
	            if (distancias.get(vizinho) + aresta.getPeso() == distancias.get(atual)) {
	                caminho.add(vizinho);
	                atual = vizinho;
	                break;
	            }
	        }
	    }
	    // Inverter o caminho antes de retornar
	    Collections.reverse(caminho);
	    return caminho;
	}
}
