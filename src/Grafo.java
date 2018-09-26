import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grafo {
	private static final boolean MEMBRO = true;
	private static final boolean NAOMEMBRO = false;
	private static final double INFINITO = 99999999.0;
	private static final double INFINITO_NEW = 1/10;

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

	public Grafo() {
		tam = 0;
		vertices = new ArrayList<>();
		listaDeAdjacencias = new LinkedList<>();
	}

	public boolean setaInformacao(int i, String nome) {
		if (vertices.size() > i && i >= 0) {
			vertices.get(i).setNome(nome);
			return true;
		}
		return false;
	}

	public boolean criaVertice(String nomeVertice) {
		if(procuraVertice(nomeVertice) == -1) {
			Vertice v = new Vertice();
			v.setId(vertices.size());
			v.setNome(nomeVertice);
			vertices.add(v);
			tam++;
			listaDeAdjacencias.add(new HashMap<>());
			return true;
		}
		return false;
	}

	public int getNumeroDeVertices(){
		return vertices.size();
	}

	public int getNumeroDeArestas(){
		int arestas = 0;
		for(int i = 0; i < listaDeAdjacencias.size(); i++){
			Map<Integer, Double> temp = listaDeAdjacencias.get(i);
			arestas += temp.size();
		}
		return arestas;
	}

	public int procuraVertice(String nomeVertice){
		for(int i = 0; i < vertices.size(); i++)
		{
			Vertice v = vertices.get(i);
			if(v != null && v.getNome().equals(nomeVertice))
				return i;
		}
		return -1;
	}

	public boolean cria_adjacencia(int i, int j, double peso) {
		if(i < tam && j < tam && i >= 0 && j >= 0 && peso > 0) {
			listaDeAdjacencias.get(i).put(j, peso);
			return true;
		}
		return false;
	}

	public boolean criaAdjacencia(int i, int j) {
		if(i < tam && j < tam && i >= 0 && j >= 0) {
			if(!listaDeAdjacencias.get(i).containsKey(j)) {
				listaDeAdjacencias.get(i).put(j, 0.0);
				int grauDeEntradaAtual = vertices.get(j).getGrauDeEntrada();
				int grauDeSaidaAtual = vertices.get(i).getGrauDeSaida();
				vertices.get(j).setGrauDeEntrada(grauDeEntradaAtual + 1);
				vertices.get(i).setGrauDeSaida(grauDeSaidaAtual + 1);
			}

			double peso = listaDeAdjacencias.get(i).get(j);
			listaDeAdjacencias.get(i).put(j, peso + 1);
			return true;
		}
		return false;
	}

	public boolean removeAdjacencia(int i, int j) {
		if(i < tam && j < tam && i >= 0 && j >= 0 && listaDeAdjacencias.get(i).containsKey(j)) {
			listaDeAdjacencias.get(i).remove(j);
			int grauDeEntradaAtual = vertices.get(j).getGrauDeEntrada();
			int grauDeSaidaAtual = vertices.get(i).getGrauDeSaida();
			vertices.get(j).setGrauDeEntrada(grauDeEntradaAtual - 1);
			vertices.get(i).setGrauDeSaida(grauDeSaidaAtual - 1);
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

	public boolean[][] m1() {
		boolean[][] m = new boolean[tam][tam];
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				if(listaDeAdjacencias.get(i).containsKey(j)) {
					m[i][j] = true;
				} else {
					m[i][j] = false;	
				}
			}
		}

		return m;
	}

	boolean[][] fechamento() { 
		boolean fechamento[][] = this.m1();

		//Warshall
		for(int k = 0; k < tam; k++) {
			for(int i = 0; i < tam; i++) {
				if(fechamento[i][k]) {
					for(int j = 0; j < tam; j++)
						fechamento[i][j] = fechamento[i][j] || fechamento[k][j]; 
				}
			}
		}
		return fechamento;
	}

	public void buscaProfundidade(int x, int y) {
		List<Integer> visitados = new ArrayList<Integer>();
		List<Integer> caminho = new ArrayList<Integer>();
		boolean resultado = profundidade(x, y, visitados, caminho);
		if(!resultado){
			System.out.println("A origem não consegue chegar ao destino");
		}
		else{
			System.out.print("Caminho: ");
			for(int i : caminho){
				System.out.print(i + " ");
			}
		}
	}

	public boolean profundidade(int origem, int destino, List<Integer> visitados, List<Integer> caminho) {
		if(origem == destino){
			caminho.add(caminho.size(), origem);
			visitados.add(origem);
			return true;
		}
		else {
			if(!visitados.contains(origem)) {
				caminho.add(caminho.size(), origem);
				visitados.add(origem);
				for(int key : listaDeAdjacencias.get(origem).keySet()) {
					if(profundidade(key, destino, visitados, caminho)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public double menorCaminho(int vOrigem, int vDestino) {

		boolean[][] alcancabilidade = this.fechamento();
		if(alcancabilidade[vOrigem][vDestino] == false)
			return -1.0;

		double distancia[] = new double[tam];
		int caminho[] = new int[tam];
		boolean perm[]  = new boolean[tam];
		int vCorrente, i, k=vOrigem;
		double menorDist, novaDist, distCorrente;

		//inicialização
		for(i=0; i < tam; ++i) {
			perm[i] = NAOMEMBRO;
			distancia[i] = INFINITO;
			caminho[i] = -1;
		}

		perm[vOrigem] = MEMBRO;
		distancia[vOrigem] = 0;
		vCorrente = vOrigem;
		while(vCorrente != vDestino) {
			menorDist = INFINITO;
			distCorrente = distancia[vCorrente];
			for(i = 0; i < tam; i++) {
				if(!perm[i]) {
					if(listaDeAdjacencias.get(vCorrente).containsKey(i)) {
						novaDist = distCorrente + listaDeAdjacencias.get(vCorrente).get(i);
						if(novaDist < distancia[i]) {
							distancia[i] = novaDist;
							caminho[i] = vCorrente;						
						}
						if(distancia[i] < menorDist) {
							menorDist = distancia[i];
							k = i;
						}
					}
				}
			}
			vCorrente = k;
			perm[vCorrente] = MEMBRO;
		}

		imprimeCaminho(vOrigem, vDestino, caminho);

		return distancia[vDestino];
	}

	public double maiorCaminho(int vOrigem, int vDestino) {

		boolean[][] alcancabilidade = this.fechamento();
		if(alcancabilidade[vOrigem][vDestino] == false)
			return -1.0;

		double distancia[] = new double[tam];
		int caminho[] = new int[tam];
		boolean perm[]  = new boolean[tam];
		int vCorrente, i, k=vOrigem;
		double menorDist, novaDist, distCorrente;

		//inicialização
		for(i=0; i < tam; ++i) {
			perm[i] = NAOMEMBRO;
			distancia[i] = INFINITO;
			caminho[i] = -1;
		}

		perm[vOrigem] = MEMBRO;
		distancia[vOrigem] = 0;
		vCorrente = vOrigem;
		while(vCorrente != vDestino) {
			menorDist = INFINITO;
			distCorrente = distancia[vCorrente];
			for(i = 0; i < tam; i++) {
				if(!perm[i]) {
					if(listaDeAdjacencias.get(vCorrente).containsKey(i)) {
						novaDist = distCorrente + listaDeAdjacencias.get(vCorrente).get(i);
						double test = 1.0 / novaDist;
						double test2 = 1.0 / distancia[i]; 
						if(test < test2) {
							distancia[i] = novaDist;
							caminho[i] = vCorrente;						
						}
						if(distancia[i] > distCorrente){
							menorDist = distancia[i];
							k = i;
						}
					}
				}
			}
			vCorrente = k;
			perm[vCorrente] = MEMBRO;
		}

		imprimeMaiorCaminho(vOrigem, vDestino, caminho);

		return distancia[vDestino];
	}

	public void imprimeCaminho(int vOrigem, int vDestino, int caminho[]) {
		System.out.println(MessageFormat.format("Menor caminho para chegar de {0} até {1}:", vertices.get(vOrigem).getNome(), vertices.get(vDestino).getNome()));
		System.out.print(vertices.get(vDestino).getNome() + " ");
		int i = caminho[vDestino];
		while(i != vOrigem) {
			System.out.print(vertices.get(i).getNome() + " ");
			i = caminho[i];
		}
		System.out.println(vertices.get(i).getNome());
	}

	public void imprimeMaiorCaminho(int vOrigem, int vDestino, int caminho[]) {
		System.out.println(MessageFormat.format("Menor caminho para chegar de {0} até {1}:", vertices.get(vOrigem).getNome(), vertices.get(vDestino).getNome()));
		System.out.print(vertices.get(vDestino).getNome() + " ");
		int i = caminho[vDestino];
		while(i != vOrigem) {
			if(i == -1)
				break;
			System.out.print(vertices.get(i).getNome() + " ");
			i = caminho[i];
		}
		System.out.println(vertices.get(vOrigem).getNome() + " ");
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

	public void imprimir20ComMaiorGrauDeSaida() {
		List<Vertice> copia = new ArrayList<>(vertices);

		Collections.sort(copia, new Comparator<Vertice>() {
			@Override
			public int compare(Vertice o1, Vertice o2) {
				return o2.getGrauDeSaida() - o1.getGrauDeSaida();
			}
		});

		System.out.println("20 com maior grau de saida:");
		for (int i = 0; i < 20; i++) {
			System.out.println(MessageFormat.format("{0}: {1}", copia.get(i).getNome(), copia.get(i).getGrauDeSaida()));
		}
	}

	public void imprimir20ComMaiorGrauDeEntrada() {
		List<Vertice> copia = new ArrayList<>(vertices);

		Collections.sort(copia, new Comparator<Vertice>() {
			@Override
			public int compare(Vertice o1, Vertice o2) {
				return o2.getGrauDeEntrada() - o1.getGrauDeEntrada();
			}
		});

		System.out.println("20 com maior grau de entrada:");
		for (int i = 0; i < 20; i++) {
			System.out.println(MessageFormat.format("{0}: {1}", copia.get(i).getNome(), copia.get(i).getGrauDeEntrada()));
		}
	}

	public boolean largura(int destino, List<Integer> fila, List<Integer> visitados) {
		if(fila.isEmpty()) {
			return false;
		} else {
			int x = fila.remove(0);
			visitados.add(x);
			if(x == destino) {
				return true;
			} else {
				Map<Integer, Double> adjacentesDeX = listaDeAdjacencias.get(x);
				for(int i : adjacentesDeX.keySet()) {
					if(!visitados.contains(i) && !fila.contains(i)) {
						fila.add(i);
					}
				}
				return largura(destino, fila, visitados);
			}
		}
	}

	public void buscaLargura(int origem, int destino) {
		List<Integer> visitados = new ArrayList<>();
		List<Integer> fila = new ArrayList<>();
		fila.add(origem);
		boolean chegaAoDestino = largura(destino, fila, visitados);
		if(!chegaAoDestino) {
			System.out.println("Não é possivel chegar ao destino a partir desta origem!");
		}
		System.out.println(MessageFormat.format("Nós visitados de {0} a {1}:", origem, destino));
		for(int i : visitados) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public void listarNosComDistanciaD(int origem, int distancia) {
		Set<Integer> verticesAdj = listaDeAdjacencias.get(origem).keySet();
		Set<Integer> verticesAdjAtuais = new HashSet<>(verticesAdj);
		List<List<Integer>> listaDeArestasVisitadas = new ArrayList<>();
		
		for (int i = 0; i < tam; i++) {
			listaDeArestasVisitadas.add(new ArrayList<>());
		}
		
		for (Integer adj : verticesAdj) {
			listaDeArestasVisitadas.get(origem).add(adj);
		}
		
		for(int i = 0; i < distancia - 1; i++) {
			verticesAdjAtuais = new HashSet<>();
			for (int vAdj : verticesAdj) {
				for (int v1 : listaDeAdjacencias.get(vAdj).keySet()) {
					if (listaDeArestasVisitadas.get(vAdj).contains(v1) || v1 == origem) {
						continue;
					}
					verticesAdjAtuais.add(v1);
					listaDeArestasVisitadas.get(vAdj).add(v1);
				}
			}
			verticesAdj = new HashSet<>(verticesAdjAtuais);
		}
		
		System.out.println(MessageFormat.format("Nós com distancia {0} da origem {1}: ",distancia, origem));
		for (int adj : verticesAdjAtuais) {
			System.out.print(adj + " ");
		}
		System.out.println();
	}

	public void listarNosComDistanciaX(int origem, int distancia) {
		boolean [][] m1  = m1();
		boolean [][] produto  = m1();

		for (int d = 0; d < distancia - 1; d++) {
			for(int i = 0; i < tam; i++) {
				for (int j = 0; j < tam; j++) {
					boolean valor = false;
					for (int k = 0; k < tam; k++) {
						valor |= produto[i][k] && m1[k][j];
						if(valor) {
							break;
						}
					}
					produto[i][j] = valor;
				}
			}
		}
		
		System.out.println(MessageFormat.format("Nós com distancia {0} da origem {1}: ",distancia, origem));
		for (int i = 0; i < tam; i++) {
			if(produto[origem][i]) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}
}
