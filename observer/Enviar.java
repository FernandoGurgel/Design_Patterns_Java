package observer;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Enviar implements Observer{
	
	private TelaReceber tela;
	
	public Enviar(ModeloConexao conexao) {
		tela = new TelaReceber();
		enviarSolicitacao(conexao);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void enviarSolicitacao(ModeloConexao conexao){
		tela.apresentaMensagem("Conectando.....");
		new Thread(new ReceberNotificacao(this,"adicionar"));
	}
	
	public void receberMensagem(){
		
	}
	
	public void cancelarNotificacao(){
		new Thread(new ReceberNotificacao(this,"cancelar"));		
	}
	
	public class ReceberNotificacao extends Observable implements Runnable{

		public ReceberNotificacao(Enviar enviar, String string) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class TelaReceber extends JFrame{
		
		private JScrollPane panel;
		private GridLayout layout;
		private JTextArea areaText;
		
		public TelaReceber() {
			super("Monitor de recado ;)  ");
			onCreate();
		}

		public void apresentaMensagem (String mensagem) {
			areaText.append(mensagem+"\n");
			if (!areaText.getText().isEmpty() && !areaText.isFocusOwner()){
				areaText.setCaretPosition(areaText.getText().length());
			}
		}
		
		public void onCreate(){
			panel = new JScrollPane();
			areaText = new JTextArea();
			layout = new GridLayout(1, 1);
			
			areaText.setEditable(false);
			areaText.setColumns(20);
			areaText.setRows(5);
			panel.setViewportView(areaText);
			
			setLayout(layout);
			add(panel);
			setSize(386, 349);
			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	
}
