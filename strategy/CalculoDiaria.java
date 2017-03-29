package strategy;

public class CalculoDiaria implements CalculoValor{

	private double valorDiaria;
	
	public CalculoDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	
	@Override
	public double calcular(long l, Veiculo veiculo) {
		return valorDiaria * Math.ceil(l/60.0);
	}
	
	public static void main(String[] args) {
		long hora = (long) 190.562339;
		CalculoDiaria diaria = new CalculoDiaria(2.0);
		Veiculo veiculo = new Veiculo("jxn-4071", "Mille", "Vermelhor");
		System.out.println(diaria.calcular(hora, veiculo));
	}
}
