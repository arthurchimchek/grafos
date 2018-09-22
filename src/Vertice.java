
public class Vertice {
	private int id;
	private String nome;
	
	public Vertice() {
		this.nome = "";
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
