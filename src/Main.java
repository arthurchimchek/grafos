import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Grafo g = new Grafo();
		g.criaVertice("V0");
		g.criaVertice("V1");
		g.criaVertice("V2");
		g.criaVertice("V3");
		g.criaVertice("V4");
		g.criaVertice("V5");

		g.cria_adjacencia(0, 1, 3);
		g.cria_adjacencia(0, 3, 4);
		g.cria_adjacencia(0, 5, 10);
		g.cria_adjacencia(0, 2, 8);
		g.cria_adjacencia(1, 3, 6);
		g.cria_adjacencia(2, 4, 7);
		g.cria_adjacencia(3, 4, 1);
		g.cria_adjacencia(3, 5, 3);
		g.cria_adjacencia(4, 5, 1);
		g.imprimir();
		
		List<Aresta> arestas = g.prim();
		for(Aresta a : arestas){
			System.out.println("Origem: " + a.getOrigem() + "\tDestino: " + a.getDestino() + "\tPeso: " + a.getPeso());
		}
	}
}