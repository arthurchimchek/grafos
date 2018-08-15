
public class Main {

	public static void main(String[] args) {
		int tam = 5;
		Grafo g = cria_grafo(tam);
		seta_informacao(g, 0, "Jose");
		seta_informacao(g, 1, "Maria");
		seta_informacao(g, 2, "Paulo");
		seta_informacao(g, 3, "Claudio");
		seta_informacao(g, 4, "Carlos");
		
		cria_adjacencia(g, 0, 1, 12);
		cria_adjacencia(g, 1, 2, 20);
		cria_adjacencia(g, 1, 3, 5);
		cria_adjacencia(g, 4, 1, 7);
		cria_adjacencia(g, 4, 0, 18);
		
		g.imprimir();
		
		Vertice[] temp = new Vertice[tam];
		System.out.println("Qtd de adjacentes: " + adjacentes(g, 4, temp));
		System.out.println(temp[0]);
		System.out.println(temp[1]);
	}
	
	
	
	public static Grafo cria_grafo(int n) {
		return new Grafo(n);
	}
	
	public static boolean cria_adjacencia(Grafo g, int i, int j, int peso) {
		if(g == null) {
			return false;
		}
		return g.cria_adjacencia(i, j, peso);
	}
	
	public static boolean remove_adjacencia(Grafo g, int i, int j) {
		if(g == null) {
			return false;
		}
		return g.remove_adjacencia(i, j);
	}
	
	public static int adjacentes(Grafo g, int i, Vertice[] v) {
		if(g != null) {
			return g.adjacentes(i, v);
		}
		return 0;
	}
	
	public static boolean seta_informacao(Grafo g, int i, String nome) {
		return g.seta_informacao(i, nome);
	}
	
	public static void imprime(Grafo g) {
		g.imprimir();
	}

}

