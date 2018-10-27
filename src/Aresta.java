public class Aresta {
	private int origem;
	private int destino;
	private double peso;
	
	public Aresta(int origem, int destino, double peso) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}
	
	public int getOrigem() {
		return origem;
	}
	
	public void setOrigem(int origem) {
		this.origem = origem;
	}
	
	public int getDestino() {
		return destino;
	}
	
	public void setDestino(int destino) {
		this.destino = destino;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}
}