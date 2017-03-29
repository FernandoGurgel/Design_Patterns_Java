package chain;

public class Refrigerante {
	
	private int quantidade;
	private String nome;
	
	public Refrigerante (String nome, int quantidade){
		this.quantidade = quantidade;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
