
public class Main {

	public static void main(String[] args) {
		int tam = 6;
		Grafo g = new Grafo(tam);
		g.seta_informacao(0, "0");
		g.seta_informacao(1, "1");
		g.seta_informacao(2, "2");
		g.seta_informacao(3, "3");
		g.seta_informacao(4, "4");
		g.seta_informacao(5, "5");
		
		g.cria_adjacencia(0, 1, 1);
		g.cria_adjacencia(1, 0, 2);
		g.cria_adjacencia(0, 3, 1);
		g.cria_adjacencia(3, 1, 3);
		g.cria_adjacencia(1, 4, 1);
		g.cria_adjacencia(1, 2, 3);
		g.cria_adjacencia(1, 5, 7);
		g.cria_adjacencia(2, 4, 1);
		g.cria_adjacencia(2, 5, 8);
		g.cria_adjacencia(4, 5, 2);
		
		g.imprimir();
		
//		Vertice[] temp = new Vertice[tam];
//		System.out.println("Qtd de adjacentes ao V4: " + g.adjacentes(1, temp));
	}

}

