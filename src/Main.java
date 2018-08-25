
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
		g.seta_informacao(6, "6");
		
		g.cria_adjacencia(0, 1, 1);
		g.cria_adjacencia(0, 4, 1);
		g.cria_adjacencia(1, 3, 1);
		g.cria_adjacencia(2, 0, 3);
		g.cria_adjacencia(3, 0, 1);
		g.cria_adjacencia(3, 5, 3);
		g.cria_adjacencia(3, 6, 7);
		g.cria_adjacencia(6, 1, 1);
//		g.cria_adjacencia(2, 5, 8);
//		g.cria_adjacencia(4, 5, 2);
		
		g.imprimir();
		
		boolean[][] m1 = g.m1();
		
		for(int i = 0; i < tam; i++)
		{
			for(int j = 0; j < tam; j++)
			{
				System.out.print(m1[i][j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println();System.out.println();
		
		boolean[][] fechamento = g.fechamento();
		for(int i = 0; i < tam; i++)
		{
			for(int j = 0; j < tam; j++)
			{
				System.out.print(fechamento[i][j] + "\t");
			}
			System.out.println();
		}
		
//		Vertice[] temp = new Vertice[tam];
//		System.out.println("Qtd de adjacentes ao V4: " + g.adjacentes(1, temp));
	}

}

