
public class Vertice {
	private int id;
	private String nome;
	private int grauDeEntrada;
	private int grauDeSaida;
	
	public Vertice() {
		this.nome = "";
		this.grauDeEntrada = 0;
		this.grauDeSaida = 0;
	}

	public int getGrauDeEntrada() {
		return grauDeEntrada;
	}

	public void setGrauDeEntrada(int grauDeEntrada) {
		this.grauDeEntrada = grauDeEntrada;
	}

	public int getGrauDeSaida() {
		return grauDeSaida;
	}

	public void setGrauDeSaida(int grauDeSaida) {
		this.grauDeSaida = grauDeSaida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void imprimir() {
		if(nome == null) {
			System.out.println("sem nome");
		} else {
			System.out.println(this.getNome());
		}
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
