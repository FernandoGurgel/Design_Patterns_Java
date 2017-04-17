package observer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Receber extends Observable {

	private ModeloConexao conexao;
	private ArrayList<Observador> listaObsevadores;
	private JanelaMonitor monitor;
	
	public Receber(ModeloConexao conexao) {
		this.conexao = conexao;
		listaObsevadores = new ArrayList<Observador>();
		monitor = new JanelaMonitor();
		JanelaEnvio janelaEnvio = new JanelaEnvio(this);
		new Thread(new ReceberSolicitacoes()).start();
	}

	public class JanelaMonitor {

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

			escreveLista("Inciando lista de monitoramento ip "+conexao.getIp()+"...");
		}

		private void escreveLista(String mensagem) {
			areaText.append(mensagem + "\n");
			if (!areaText.getText().isEmpty() && !areaText.isFocusOwner()) {
				areaText.setCaretPosition(areaText.getText().length());
			}
		}
	}

	public class Observador implements Observer {

		private ModeloConexao conexaoObsevador;
		
		public Observador(ModeloConexao conexao) {
			System.out.println(conexao.getIp()+"  "+conexao.getPorta());
			this.conexaoObsevador = conexao;
		}
		
		public String getIp(){
			return conexao.getIp();
		}

		@Override
		public void update(Observable o, Object arg) {
			this.conexaoObsevador.setMensagem(arg.toString());			

			try {
				byte[] dados = conexaoObsevador.getMensagem().getBytes();
				DatagramSocket observadoSocket = new DatagramSocket();
				InetAddress endereco = InetAddress.getByName(conexaoObsevador.getIp());
				DatagramPacket pacote = new DatagramPacket(dados, dados.length, endereco, conexaoObsevador.getPorta());
				observadoSocket.send(pacote);
				observadoSocket.close();
	
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public class JanelaEnvio {

		public JanelaEnvio(Receber receber) {
					
			JFrame frame = new JFrame("Gerar Mensagem!!");
			JPanel panel = new JPanel();
			panel.setSize(300, 100);

			JTextField input = new JTextField(10);
			panel.add(new JLabel("Enter: "));
			panel.add(input);
			input.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					conexao.setMensagem(input.getText());
					receber.notifyObservers(input.getText().toString());
					input.setText("");
					//new Thread(new EnviarMensagem(receber.getListaObsevadores())).start(); 
				}
			});
			frame.addWindowListener(new WindowAdapter() {

				public void windowCloing(WindowEvent e) {
					System.exit(0);
				}
			});
			frame.add(panel);
			frame.setSize(300, 70);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}

	/*public class EnviarMensagem implements Runnable {
		
		private List<Observador> list;
		
		public EnviarMensagem(List<Observador> listaObsevadores) {
			list = listaObsevadores;
		}

		@Override
		public void run() {
			
			for (Observador observador : list){
				observador = new Observador();
			}
		}
		
	}*/
	
	public class ReceberSolicitacoes implements Runnable{		
		@Override
		public void run() {
			byte[] dadosReceber;
			boolean erro = true;
			try {
				DatagramSocket socket = new DatagramSocket(conexao.getPorta());
				while(erro){
					dadosReceber = new byte[255];
	                DatagramPacket pacoteRecebido = new DatagramPacket(dadosReceber, dadosReceber.length);
	                try {
	                    socket.receive(pacoteRecebido);
	                    byte[] b = pacoteRecebido.getData();
	                    String s = "";
	                    for (int i = 0; i < b.length; i++) {
	                        if (b[i] != 0) {
	                            s += (char) b[i];
	                        }
	                    }
	                    String ip = "";
                    	for (int a = 1; a < pacoteRecebido.getAddress().toString().length();a++){
                    		ip +=pacoteRecebido.getAddress().toString().charAt(a);
                    	}
	                    String hora = getHora();
	                    String mensagem ="solicitação ("+s+") -> "+hora + " ip -> "+ip;
	                    if (s.equals("entrar")){
	                    	Observador observador = new Observador(new ModeloConexao(ip, 5000)); 
	                    	listaObsevadores.add(observador);
	                    	addObserver(observador);
	                    	monitor.escreveLista(mensagem);
	                    }else if (s.equals("sair")){
	                    	for (Observador ob : listaObsevadores){
	                    		if (ob.getIp().equals(ip)){
	                    			listaObsevadores.remove(ob);
	                    			deleteObserver(ob);
	                    			monitor.escreveLista(mensagem);
	                    		}
	                    		
	                    	}
	                    }else{
	                    	monitor.escreveLista(mensagem);
	                    }
	                } catch (Exception e) {
	                    System.out.println("erro");
	                    e.printStackTrace();
	                    try {
	                        Thread.sleep(100);
	                    } catch (InterruptedException ex) {
	                        System.out.println(ex.getMessage());
	                    }
	                    erro = false;
	                    continue;
	                }
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public String getHora() {

        	String sb;
        	GregorianCalendar d = new GregorianCalendar();

        	sb = String.valueOf(d.get( GregorianCalendar.HOUR_OF_DAY))+":";
        	sb += String.valueOf( d.get( GregorianCalendar.MINUTE ) )+":";
        	sb += String.valueOf( d.get( GregorianCalendar.SECOND ) );

        	return sb.toString();
        }		
	}
	
	
	public static void main(String[] args) {
		ModeloConexao conexao = new ModeloConexao("192.168.1.3", 5000);
		Receber receber = new Receber(conexao);
	}
}