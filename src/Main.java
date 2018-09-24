import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		LeitorArquivos leitor = new LeitorArquivos();
		Grafo g1 = leitor.readDirectory();
		System.out.println("Numero total de vértices: " + g1.getNumeroDeVertices());
		g1.imprimir();
		System.out.println();
		g1.imprimir20ComMaiorGrauDeSaida();
		System.out.println();
		g1.imprimir20ComMaiorGrauDeEntrada();
		System.out.println();
		g1.buscaLargura(0, 33);
		
	}
}