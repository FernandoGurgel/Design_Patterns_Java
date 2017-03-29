package chain;

public class teste {

	public static void main(String[] args) {
		SolicitacaoRefrigerante solicitacao = new SolicitacaoRefrigerante();
		
		ModeloRefrigerante coca = new ModeloRefrigerante(new Refrigerante ("Coca", 10));
		ModeloRefrigerante fanta = new ModeloRefrigerante(new Refrigerante("Fanta", 10));
		ModeloRefrigerante uva = new ModeloRefrigerante(new Refrigerante("Fanta", 10));
		
		coca.setProximo(fanta);
		fanta.setProximo(uva);
		while(true){		
			try {
				solicitacao.retiraRefrigerente(coca);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		

	}

}
