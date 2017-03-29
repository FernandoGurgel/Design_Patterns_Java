package chain;

	import java.util.Scanner;

public class SolicitacaoRefrigerante {

	private Scanner scanner;

	public void retiraRefrigerente(ControleRefrigerante controle) {
		scanner = new Scanner(System.in);
		System.out.println("Maquina matagente!!!!");
		System.out.print("Informe a quantidade >> ");
		int qtd = scanner.nextInt();
		System.out.print("Informe o refrigerante >> ");
		String nome = scanner.next();
		controle.solicitacao(new Refrigerante(nome, qtd));
	}
}
