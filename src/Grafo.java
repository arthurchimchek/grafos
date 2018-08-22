import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Grafo {
	private int tam;
	private LinkedList<Map<Integer, Double>> listaDeAdjacencias;
	private List<Vertice> vertices;

	public Grafo(int n) {
		tam = n;
		vertices = new ArrayList<>();
		listaDeAdjacencias = new LinkedList<>();
		
		for(int i = 0; i < n; i++) {
			vertices.add(new Vertice());
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				listaDeAdjacencias.add(new HashMap<>());
			}
		}
	}

	public boolean seta_informacao(int i, String nome) {
		if (vertices.size() > i && i >= 0) {
			vertices.get(i).setNome(nome);
			return true;
		}
		return false;
	}
	
	public boolean cria_adjacencia(int i, int j, double peso) {
		if(i < tam && j < tam && i >= 0 && j >= 0 && peso > 0) {
			listaDeAdjacencias.get(i).put(j, peso);
			return true;
		}
		return false;
	}
	
	public boolean remove_adjacencia(int i, int j) {
		if(i < tam && j < tam && i >= 0 && j >= 0 && listaDeAdjacencias.get(i).containsKey(j)) {
			listaDeAdjacencias.get(i).remove(j);
			return true;
		}
		return false;
	}
	
	public int adjacentes(int i, Vertice[] v) {
		int cont = 0;
		Map<Integer, Double> m = listaDeAdjacencias.get(i);
		for(int k : m.keySet()) {
			v[cont] = vertices.get(k);
			cont++;
		}
		return cont;
	}
	
	public void imprimir() {
		System.out.println("Vetores:");
		for (int i = 0; i < tam; i++) {
			System.out.println("indice " + i + ":");
			vertices.get(i).imprimir();
			System.out.println();
		}
		
		System.out.println("Lista de adjacencias:");
		
		int cont = 0;
		for(Map<Integer, Double> m : listaDeAdjacencias) {
			if(!m.isEmpty()) {
				System.out.println("Adjacentes do vértice " + cont + ":");
				for(int i : m.keySet()) {
					System.out.println("\tVértice " + i + ", peso " + m.get(i));
				}
			}		
			cont++;
		}
	}
}
