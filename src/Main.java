import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		LeitorArquivos leitor = new LeitorArquivos();
		Grafo g = leitor.readDirectory();
		g.imprimir();
	}
}