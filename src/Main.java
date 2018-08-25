
public class Main {

	public static void main(String[] args) {
		int tam = 6;
		Grafo g = new Grafo(tam);
		g.seta_informacao(0, "V0");
		g.seta_informacao(1, "V1");
		g.seta_informacao(2, "V2");
		g.seta_informacao(3, "V3");
		g.seta_informacao(4, "V4");
		g.seta_informacao(5, "V5");

		g.cria_adjacencia(0, 1, 1);
		g.cria_adjacencia(0, 3, 1);
		g.cria_adjacencia(1, 0, 2);
		g.cria_adjacencia(1, 4, 1);
		g.cria_adjacencia(1, 2, 3);
		g.cria_adjacencia(1, 5, 7);
		g.cria_adjacencia(2, 5, 2);
		g.cria_adjacencia(2, 4, 1);
		g.cria_adjacencia(3, 1, 3);
		g.cria_adjacencia(4, 5, 2);

		g.imprimir();

		boolean[][] m1 = g.m1();

		System.out.println();

		for(int i = 0; i < tam; i++)
		{
			for(int j = 0; j < tam; j++)
			{
				System.out.print(m1[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();

		boolean[][] fechamento = g.fechamento();
		for(int i = 0; i < tam; i++)
		{
			for(int j = 0; j < tam; j++)
			{
				System.out.print(fechamento[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("Menor caminho: " + g.melhorCaminho(0, 5));
	}
}

