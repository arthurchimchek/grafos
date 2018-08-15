import java.util.ArrayList;
import java.util.List;

public class Grafo {
	private int tam;
	private double[][] matriz;
	private List<Vertice> vertices;
	private final static double CONST_INFINITO = Double.MAX_VALUE;

	public Grafo(int n) {
		this.tam = n;
		vertices = new ArrayList<>();
		matriz = new double[tam][tam];
		
		for(int i = 0; i < n; i++) {
			vertices.add(new Vertice());
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = CONST_INFINITO;
			}
		}
	}

	public boolean seta_informacao(int i, String nome) {
		if (vertices.size() > i && i >= 0) {
			vertices.get(i).setNome(nome);
			return true;
		}
		return false;
	}
	
	public boolean cria_adjacencia(int i, int j, double peso) {
		if(i < tam && j < tam && i >= 0 && j >= 0 && peso > 0) {
			matriz[i][j] = peso;
			return true;
		}
		return false;
	}
	
	public boolean remove_adjacencia(int i, int j) {
		if(i < tam && j < tam && i >= 0 && j >= 0 && matriz[i][j] != 0) {
			matriz[i][j] = CONST_INFINITO;
			return true;
		}
		return false;
	}
	
	public int adjacentes(int i, Vertice[] v) {
		int cont = 0;
		for (int j = 0; j < tam; j++) {
			if(matriz[i][j] >= 0 && matriz[i][j] < CONST_INFINITO) {
				v[cont] = vertices.get(j);	
				cont++;
			}
		}
		return cont;
	}
	
	public void imprimir() {
		System.out.println("Vetores:");
		for (int i = 0; i < tam; i++) {
			System.out.println("indice " + i + ":");
			vertices.get(i).imprimir();
			System.out.println("");
		}
		System.out.println("Matriz de adjacencias:");
		System.out.print("\t");
		for (int i = 0; i < tam; i++) {
			System.out.print(i + "\t");
		}
		System.out.println("");
		for (int i = 0; i < tam; i++) {
			System.out.print("________");
//			System.out.print("--------");
		}
		System.out.print("___");
//		System.out.print("---");
		System.out.println("");
		
		for (int i = 0; i < tam; i++) {
			System.out.print(i + "  |\t");
			for (int j = 0; j < tam; j++) {
				if(matriz[i][j] != CONST_INFINITO)
					System.out.print(matriz[i][j] + "\t");
				else
					System.out.print("INF\t");
			}
			System.out.println("");
		}
	}
}
