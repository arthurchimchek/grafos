
public class Vertice {
	private String nome;

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
