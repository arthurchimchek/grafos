import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		LeitorArquivos leitor = new LeitorArquivos();
		Grafo g = leitor.readDirectory();
		System.out.println("Numero total de vértices: " + g.getNumeroDeVertices());
		g.imprimir();
		System.out.println();
		g.imprimir20ComMaiorGrauDeSaida();
		System.out.println();
		g.imprimir20ComMaiorGrauDeEntrada();
		
	}
}