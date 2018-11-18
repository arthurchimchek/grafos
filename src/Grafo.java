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

	private int tam;
	private LinkedList<Map<Integer, Double>> listaDeAdjacencias;
	private List<Vertice> vertices;
	boolean direcionado;

	public List<Vertice> getVertices() {
		return vertices;
	}
	
	public boolean isDirecionado() {
		return direcionado;
	}

	public void setDirecionado(boolean direcionado) {
		this.direcionado = direcionado;
	}

	public LinkedList<Map<Integer, Double>> getListaDeAdjacencias() {
		return listaDeAdjacencias;
	}

	public Grafo(int n, boolean isDirecionado) {
		tam = n;
		vertices = new ArrayList<>();
		direcionado = isDirecionado;
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
		direcionado = false;
	}
	
	public Grafo(boolean isDirecionado) {
		tam = 0;
		vertices = new ArrayList<>();
		listaDeAdjacencias = new LinkedList<>();
		direcionado = isDirecionado;
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
	
	public boolean criaVertice(int id, String nomeVertice){
		if(procuraVertice(nomeVertice) == -1) {
			Vertice v = new Vertice();
			v.setId(id);
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
			int grauDeEntradaAtual = vertices.get(j).getGrauDeEntrada();
			int grauDeSaidaAtual = vertices.get(i).getGrauDeSaida();
			vertices.get(j).setGrauDeEntrada(grauDeEntradaAtual + 1);
			vertices.get(i).setGrauDeSaida(grauDeSaidaAtual + 1);
			
			if(!this.direcionado){
				grauDeEntradaAtual = vertices.get(i).getGrauDeEntrada();
				grauDeSaidaAtual = vertices.get(j).getGrauDeSaida();
				vertices.get(i).setGrauDeEntrada(grauDeEntradaAtual + 1);
				vertices.get(j).setGrauDeSaida(grauDeSaidaAtual + 1);
				listaDeAdjacencias.get(j).put(i, peso);
			}
			
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
	
	public void buscaProfundidade(int x, int y){
		if(vertices.size() == 0){
			System.out.println("Não existem vértices no grafo");
			return;
		}
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
		if(origem == destino) {
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
	
	public double menorCaminho(int vOrigem, int vDestino, int caminho[]) {

		boolean[][] alcancabilidade = this.fechamento();
		if(alcancabilidade[vOrigem][vDestino] == false)
			return -1.0;

		double distancia[] = new double[tam];
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

		return distancia[vDestino];
	}
	
	public double maiorCaminho(int vOrigem, int vDestino) {
		if(vertices.size() == 0){
			System.out.println("Não existem vértices no grafo");
			return -1.0;
		}
	
		boolean[][] alcancabilidade = this.fechamento();
		if(alcancabilidade[vOrigem][vDestino] == false){
			System.out.println("A origem não consegue alcançar o destino");
			return -1.0;
		}
		
		double distancia[] = new double[tam];
		int caminho[] = new int[tam];
		boolean perm[]  = new boolean[tam];
		int vCorrente, i, k=vOrigem;
		double maiorDist, novaDist, distCorrente;

		//inicialização
		for(i=0; i < tam; ++i) {
			perm[i] = NAOMEMBRO;
			distancia[i] = 1/INFINITO;
			caminho[i] = -1;
		}

		perm[vOrigem] = MEMBRO;
		distancia[vOrigem] = 0;
		vCorrente = vOrigem;
		
		while(vCorrente != vDestino){
			if(listaDeAdjacencias.get(vCorrente) != null){
				distCorrente = distancia[vCorrente];
				for(i =0; i < tam; i++){
					if(listaDeAdjacencias.get(vCorrente).containsKey(i)){
						novaDist = distCorrente + listaDeAdjacencias.get(vCorrente).get(i);
						if( (1.0 / novaDist) < (1.0 / distancia[i])) {
							distancia[i] = novaDist;
							caminho[i] = vCorrente;						
						}
					}
				}
			}
			maiorDist = 1 / INFINITO;
			for(i =0; i < tam; i++){
				if(perm[i] == false && distancia[i] > maiorDist){
					maiorDist = distancia[i];
					k = i;
				}
			}
			vCorrente = k;
			perm[vCorrente] = MEMBRO;
		}

		imprimeMaiorCaminho(vOrigem, vDestino, caminho, distancia[vDestino]);

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
	
	public void imprimeMaiorCaminho(int vOrigem, int vDestino, int caminho[], double peso) {
		System.out.println(MessageFormat.format("Maior caminho para chegar de {0} até {1} tem peso {2}:", vertices.get(vOrigem).getNome(), vertices.get(vDestino).getNome(), peso));
		System.out.print("Caminho percorrido: " + vertices.get(vDestino).getNome() + " ");

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

//	public void listarNosComDistanciaX(int origem, int distancia) {
//		boolean [][] m1  = m1();
//		boolean [][] produto  = m1();
//
//		for (int d = 0; d < distancia - 1; d++) {
//			for(int i = 0; i < tam; i++) {
//				for (int j = 0; j < tam; j++) {
//					boolean valor = false;
//					for (int k = 0; k < tam; k++) {
//						valor = produto[i][k] && m1[k][j];
//						if(valor) {
//							break;
//						}
//					}
//					produto[i][j] = valor;
//				}
//			}
//		}
//		
//		System.out.println(MessageFormat.format("Nós com distancia {0} da origem {1}: ",distancia, origem));
//		for (int i = 0; i < tam; i++) {
//			if(produto[origem][i]) {
//				System.out.print(i + " ");
//			}
//		}
//		System.out.println();
//	}
	
	public boolean ehCiclico() {
		List<Integer> fila = new ArrayList<>();
		fila.add(vertices.get(0).getId());
		
		List<Integer> visitados = new ArrayList<>();
		List<Integer> nosComAdjacencia = new ArrayList<>();
		
		return ciclico(fila, visitados, nosComAdjacencia);
	}
	
	public boolean ciclico(/*int destino,*/ List<Integer> fila, List<Integer> visitados, List<Integer> nosComAdjacencia) {
		if(fila.isEmpty()) {
			return false;
		} else {
			int x = fila.remove(0);
			visitados.add(x);
//			if(x == destino) {
//				return true;
//			} else {
				Map<Integer, Double> adjacentesDeX = listaDeAdjacencias.get(x);
				for(int i : adjacentesDeX.keySet()) {
					if(!visitados.contains(i) && !fila.contains(i)) {
						fila.add(i);
						if(!nosComAdjacencia.contains(i) && listaDeAdjacencias.get(i).size() > 0)
							nosComAdjacencia.add(i);
					}
					else if(visitados.contains(i) && nosComAdjacencia.contains(i)){
						return true;
					}
				}
				return ciclico(/*destino,*/ fila, visitados, nosComAdjacencia);
//			}
		}
	}
	
	public List<Aresta> prim() {
		List<Aresta> arestas = new ArrayList<Aresta>();
		List<Aresta> todasArestas = new ArrayList<>();
		List<Integer> permitidos = new ArrayList<Integer>();
		
		int v = vertices.get(0).getId();
		permitidos.add(v);
		
		while(permitidos.size() != vertices.size()) {
			Map<Integer, Double> adjacenciasAtuais = listaDeAdjacencias.get(v); 
			for(int k : adjacenciasAtuais.keySet()) {
				todasArestas.add(new Aresta(v, k, adjacenciasAtuais.get(k)));
			}
			
			for(int i = 0; i < listaDeAdjacencias.size(); i++) {
				if(i == v)
					continue;
				Map<Integer, Double> adjacencias = listaDeAdjacencias.get(i);
				for(int k : adjacencias.keySet()) {
					if(k == v)
						todasArestas.add(new Aresta(i, v, adjacencias.get(k)));
				}
			}
			
			Aresta a = null;
			for(Aresta temp : todasArestas) {
				if(a != null) {
					if(temp.getPeso() < a.getPeso()) {
						if(!permitidos.contains(temp.getDestino()) || !permitidos.contains(temp.getOrigem())) {
							a = temp;
						}
					}
				} else {
					a = temp;
				}	
			}
			System.out.println(v);
			v = a.getDestino();
			permitidos.add(v);
			arestas.add(a);
			todasArestas.remove(a);
		}
		
		return arestas;
	}
	
	public List<List<Integer>> componentes() {
		List<List<Integer>> componentes = new ArrayList<>();
		List<Integer> visitados = new ArrayList<>();
		int i = 0;
		int origem = 0;
		while(i < vertices.size())
		{
			origem = vertices.get(i).getId();
			if(!visitados.contains(origem)) {
				visitados.add(origem);
				List<Integer> temp = new ArrayList<>();
				profundidade2(origem, temp);
				componentes.add(temp);
				for(int k : temp)
				{
					if(!visitados.contains(k))
						visitados.add(k);
				}
			}
			i++;
		}
		
		return componentes;
		
	}
	
	public void profundidade2(int origem, List<Integer> visitados) {
		visitados.add(origem);		
		for(int key : listaDeAdjacencias.get(origem).keySet()) {
			if(!visitados.contains(key))
				profundidade2(key, visitados);
		}
	}
	
	public void buscaProfundidade2(int x){
		if(vertices.size() == 0){
			System.out.println("Não existem vértices no grafo");
			return;
		}
		List<Integer> visitados = new ArrayList<Integer>();
		profundidade2(x, visitados);
		System.out.print("Vértices fortemente conectados: ");
		for(int i : visitados){
			System.out.print(i + " ");
		}
	}
	
	public boolean clique(List<Integer> verticesClique) {
		for(int vertice : verticesClique) {
			for(int adjacente : verticesClique){
				if(adjacente == vertice)
					continue;
				if(!listaDeAdjacencias.get(vertice).keySet().contains(adjacente))
					return false;
			}
				
		}
		return true;
	}
	
	public boolean maximal(List<Integer> verticesClique) {
		for(Vertice vertice : vertices) {
			if(!verticesClique.contains(vertice.getId())) {
				List<Integer> tmp = verticesClique;
				tmp.add(vertice.getId());
				if(clique(tmp))
					return false;
			}
		}
		return true;
	}
	
	public double centralidade(int x){
		int sum = 0;
		Map<Integer, Double> adjacencias = listaDeAdjacencias.get(x);
		for(Vertice v : vertices)
		{
			int id = v.getId();
			if(adjacencias.containsKey(id))
				sum++;
		}
		return (sum / vertices.size() - 1);
	}
	
	public List<Double> centralidadeTodos(){
		List<Double> grauCentralidadeTodos = new ArrayList<>();
		
		for(Vertice v : vertices)
			grauCentralidadeTodos.add(centralidade(v.getId()));
		
		return grauCentralidadeTodos;
	}
	
	public double intermediacao(int x){
		int gx = 0;
		for(Vertice v : vertices){
			if(x != v.getId()){
				int caminho[] = new int[tam];
				menorCaminho(x, v.getId(), caminho);
				for(int i = 0; i < tam; i++){
					if(x == caminho[i] && i != 0 && i != tam -1){
						gx++;
						break;
					}
				}
			}
		}
		if(direcionado)
			return gx / ((vertices.size() - 1) * (vertices.size() - 2));
		
		return (gx / ((vertices.size() - 1) * (vertices.size() - 2))) *2;
	}
	
	public List<Double> intermediacaoTodos(){
		List<Double> grauIntermediacaoTodos = new ArrayList<>();
		
		for(Vertice v : vertices)
			grauIntermediacaoTodos.add(intermediacao(v.getId()));
		
		return grauIntermediacaoTodos;
	}
}
