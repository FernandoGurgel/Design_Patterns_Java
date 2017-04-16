package observer;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Receber extends Observable{

	private ModeloConexao conexao;
	private List <Observador> listaObsevadores;
	private JanelaMonitor monitor;
	
	
	public Receber(ModeloConexao conexao) {
		this.conexao = conexao;
		listaObsevadores = new ArrayList<Observador>();
		iniciarServico();
	}
	
	
	
	private void iniciarServico() {
		
	}

	private class JanelaMonitor{
		
		private JTextArea areaText;
		
		public JanelaMonitor() {
		
			JFrame janela = new JFrame("Monitor");
			JScrollPane panel = new JScrollPane();
			areaText = new JTextArea();
			GridLayout layout = new GridLayout(1, 1);
			
			areaText.setEditable(false);
			areaText.setColumns(20);
			areaText.setRows(5);
			panel.setViewportView(areaText);
			
			janela.setLayout(layout);
			janela.add(panel);
			janela.setSize(386, 349);
			janela.setLocationRelativeTo(null);
			janela.setVisible(true);
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			escreveLista("Inciando....");			
		}

		private void escreveLista(String mensagem) {
			areaText.append(mensagem+"\n");
			if (!areaText.getText().isEmpty() && !areaText.isFocusOwner()){
				areaText.setCaretPosition(areaText.getText().length());
			}
		}
	}

	private class Observador implements Observer{

		public Observador(ModeloConexao conexao) {
			
		}
		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			
		}
		
	}

}