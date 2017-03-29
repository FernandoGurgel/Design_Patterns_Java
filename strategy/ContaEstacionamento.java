package strategy;

public class ContaEstacionamento {
	
	private CalculoValor calculo;
	private long entrada, saida;
	
	public double valorConta(Veiculo veiculo){
		return calculo.calcular(saida-entrada,veiculo);
	}
	
	public void setCalculo (CalculoValor valor){
		this.calculo = valor;
	}
}
