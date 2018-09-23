import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
//		LeitorArquivos leitor = new LeitorArquivos();
//		Grafo g = leitor.readDirectory();
//		System.out.println("Numero total de v�rtices: " + g.getNumeroDeVertices());
//		g.imprimir();
//		System.out.println();
//		g.imprimir20ComMaiorGrauDeSaida();
//		System.out.println();
//		g.imprimir20ComMaiorGrauDeEntrada();
		
		Grafo g = new Grafo();
		g.criaVertice("V0");
		g.criaVertice("V1");
		g.criaVertice("V2");
		g.criaVertice("V3");
		g.criaVertice("V4");
		g.criaVertice("V5");
		g.criaVertice("V6");
		g.criaVertice("V7");
		g.criaVertice("V8");
		g.criaVertice("V9");

		g.cria_adjacencia(0, 1, 1);
		g.cria_adjacencia(0, 2, 1);
		g.cria_adjacencia(0, 3, 1);
		g.cria_adjacencia(1, 4, 3);
		g.cria_adjacencia(1, 5, 2);
		g.cria_adjacencia(4, 9, 12);
		g.cria_adjacencia(5, 9, 13);
		g.cria_adjacencia(2, 6, 1);
		g.cria_adjacencia(2, 7, 1);
		g.cria_adjacencia(3, 8, 1);	
		g.imprimir();
		
		g.maiorCaminho(0, 9);
		
		List<Integer> list = new ArrayList<Integer>();
		
		g.profundidade(0, 4, list);
	}
}