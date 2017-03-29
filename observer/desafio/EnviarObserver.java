package observer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Aplicação que enviar informacoes de uma rede local 
 * utilizando observer;
 * 
 * @author Gurgel,Fernando
 * @GitHub https://github.com/FernandoGurgel 
 * @version v1.23
 */


public class EnviarObserver extends Observable {

	private ModeloConexao conexao;
	private JPanel panel;
	private JTextField input;

	public EnviarObserver(ModeloConexao conexao) {
		this.conexao = conexao;
		JanelaObsevado obsevado = new JanelaObsevado();
		addObserver(this.conexao);
	}

	class JanelaObsevado extends JFrame {

		public JanelaObsevado() {
			super("Enviar mensagem");
			onPropeties();
			
			panel = new JPanel();
			panel.setSize(300,100);
			
			input = new JTextField(10);
			panel.add(new JLabel("Enter: "));
			panel.add(input);
			input.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					conexao.setMensagem(input.getText());
					new Thread(new Enviar()).start();
				}
			});
			addWindowListener(new WindowAdapter() {

				public void windowCloing(WindowEvent e) {
					System.exit(0);
				}
			});
			add(panel);
			
		}

		private void onPropeties() {
			setSize(300,70);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	class Enviar implements Runnable {

		private byte[] dados;
		
		@Override
		public void run() {
			
			try {
				dados = conexao.getMensagem().getBytes("UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					

			try {

				DatagramSocket observadoSocket = new DatagramSocket();
				InetAddress endereco = InetAddress.getByName(conexao.getIp());
				DatagramPacket pacote = new DatagramPacket(dados, dados.length, endereco, conexao.getPorta());
				observadoSocket.send(pacote);
				observadoSocket.close();
				conexao.setMensagem("");
				input.setText(conexao.getMensagem());
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
	
	public static void main(String[] args) {
		EnviarObserver enviar = new EnviarObserver(new ModeloConexao("192.168.1.3", 5000));
	}
}
