package observer;

import javax.swing.JFrame;

public class Enviar extends ModeloConexao{
	
	TelaReceber tela;
	
	public Enviar(String ip, int porta) {
		super(ip, porta);
		tela = new TelaReceber();
	}
	
	public void enviarSolicitacao(){
		
	}
	
	public void receberMensagem(){
		
	}

	private class TelaReceber extends JFrame{
		
		public TelaReceber() {
			apresentaMensagem("Conectando....");
		}

		private void apresentaMensagem(String string) {
			
		}
	}
}
