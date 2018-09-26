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
		
		Grafo g = new Grafo();
		g.criaVertice("V0");
		g.criaVertice("V1");
		g.criaVertice("V2");
		g.criaVertice("V3");
		g.criaVertice("V4");
		g.criaVertice("V5");
		g.criaVertice("V6");
		g.criaVertice("V7");

		g.cria_adjacencia(0, 1, 1);
		g.cria_adjacencia(0, 2, 1);
		g.cria_adjacencia(0, 3, 1);
		g.cria_adjacencia(0, 4, 1);
		g.cria_adjacencia(1, 4, 1);
		g.cria_adjacencia(2, 5, 1);
		g.cria_adjacencia(5, 3, 1);
		g.cria_adjacencia(5, 6, 1);
		g.cria_adjacencia(5, 7, 1);
		g.cria_adjacencia(3, 0, 1);
		g.cria_adjacencia(7, 3, 1);
		g.cria_adjacencia(3, 2, 1);
		g.imprimir();
		
//		g.maiorCaminho(0, 5);
//		g.menorCaminho(0, 5);
		
//		List<Integer> list = new ArrayList<Integer>();
		
//		g.buscaProfundidade(0, 4);
//		g.buscaLargura(0, 4);

		g.cria_adjacencia(1, 2, 3);
		g.cria_adjacencia(2, 5, 2);
		g.cria_adjacencia(2, 4, 1);
		g.cria_adjacencia(3, 1, 3);
		g.cria_adjacencia(4, 5, 2);
		g.imprimir();
		
		g.maiorCaminho(0, 5);
//		g.menorCaminho(0, 5);
		
		g.listarNosComDistanciaD(0, 3);
//		g.listarNosComDistanciaX(0, 2);
	}
}