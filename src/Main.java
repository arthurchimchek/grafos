import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Grafo g = new Grafo(false);
		g.criaVertice("V0");
		g.criaVertice("V1");
		g.criaVertice("V2");
		g.criaVertice("V3");
		g.criaVertice("V4");
		g.criaVertice("V5");
		g.criaVertice("V6");

		g.cria_adjacencia(0, 1, 3);
		g.cria_adjacencia(0, 3, 4);
		g.cria_adjacencia(0, 5, 10);
		g.cria_adjacencia(0, 2, 8);
		g.cria_adjacencia(1, 3, 6);
		g.cria_adjacencia(2, 4, 7);
		g.cria_adjacencia(3, 4, 1);
		g.cria_adjacencia(3, 5, 3);
		g.cria_adjacencia(4, 5, 1);
		g.cria_adjacencia(0, 1, 1);
		g.cria_adjacencia(1, 2, 1);
		g.cria_adjacencia(2, 1, 1);
		g.cria_adjacencia(3, 4, 1);
		g.cria_adjacencia(3, 6, 1);
		g.cria_adjacencia(4, 5, 1);
		g.cria_adjacencia(5, 6, 1);
		g.cria_adjacencia(6, 4, 1);
		
//		Grafo g = PajekFile.LerPajek("C:\\www\\grafos\\teste.txt");
		PajekFile.EscreverPajek(g, "C:\\www\\grafos\\teste3.txt");
		g.imprimir();
		
	}
}