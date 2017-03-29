package chain;

public abstract class ControleRefrigerante {
	
	protected ControleRefrigerante proximo;
	
	
	public void setProximo (ControleRefrigerante refrigerante){
		this.proximo = refrigerante;
	}
	
	abstract public void solicitacao(Refrigerante refrigerante);
	
	public ControleRefrigerante getProximo(){
		return this.proximo;
	}
}
