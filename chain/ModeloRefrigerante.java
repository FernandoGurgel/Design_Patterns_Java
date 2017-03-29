package chain;

public class ModeloRefrigerante extends ControleRefrigerante {

	private Refrigerante refrigerante;
	
	public ModeloRefrigerante(Refrigerante refrigerante) {
		this.refrigerante = refrigerante;
	}

	@Override
	public void solicitacao(Refrigerante refrigerante) {
		
		if(refrigerante.getNome().equals(this.refrigerante.getNome()) && refrigerante.getQuantidade() <= this.refrigerante.getQuantidade()){
			this.refrigerante.setQuantidade(this.refrigerante.getQuantidade()-refrigerante.getQuantidade());
			System.out.println("Retire seu refrigerante!! "+ refrigerante.getNome()+"!!!!!");
		} else if (proximo != null){
			solicitacao(refrigerante);
		} else if (proximo == null){
			System.out.println("Quantidade Superior ou refrigerante nao existe!!!!");
			System.out.println("Retire seu dineiro!!!!");
		}
		
		if(!getProximo().equals(null)){
			System.out.println("Teste ok!!!");
		}
	}
}
