import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
//		LeitorArquivos leitor = new LeitorArquivos();
//		Grafo g = new Grafo();
//		g = leitor.readDirectory();
//		System.out.println("Numero total de vértices: " + g.getNumeroDeVertices());
//		g.imprimir();
//		System.out.println();
//		g.imprimir20ComMaiorGrauDeSaida();
//		System.out.println();
//		g.imprimir20ComMaiorGrauDeEntrada();
		
//		
		Grafo g = new Grafo();
		g.criaVertice("V0");
		g.criaVertice("V1");
		g.criaVertice("V2");
		g.criaVertice("V3");
		g.criaVertice("V4");
		g.criaVertice("V5");

		g.cria_adjacencia(0, 1, 1);
		g.cria_adjacencia(0, 3, 1);
		g.cria_adjacencia(1, 0, 2);
		g.cria_adjacencia(1, 4, 1);
		g.cria_adjacencia(1, 2, 3);
		g.cria_adjacencia(2, 5, 2);
		g.cria_adjacencia(2, 4, 1);
		g.cria_adjacencia(3, 1, 3);
		g.cria_adjacencia(4, 5, 2);
		g.imprimir();
		
		g.maiorCaminho(0, 5);
//		g.menorCaminho(0, 5);
		
	}
}