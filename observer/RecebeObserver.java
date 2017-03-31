package observer;

import java.awt.GridLayout;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.GregorianCalendar;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Aplicação que recebe informacoes de uma rede local 
 * utilizando observer;
 * 
 * @author Gurgel,Fernando
 * @GitHub https://github.com/FernandoGurgel 
 * @version v1.23
 */


public class RecebeObserver extends ModeloConexao{

	JanelaRecebe janela = new JanelaRecebe();
	
	public RecebeObserver(ModeloConexao conexao){		
		super(conexao.getIp(),conexao.getPorta());
		janela.escreveTela("Observer Conectado "+getIp()+":"+getPorta());
		new Thread(new Recebe(this)).start();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		super.setMensagem(arg.toString());
		janela.escreveTela(super.getMensagem());		
	}
	
	public class JanelaRecebe extends JFrame{
		
		private JScrollPane panel;
		private GridLayout layout;
		private JTextArea areaText;
		
		public JanelaRecebe() {
			super("Monitor de recado ;)  ");
			conteiner();
		}
		private void conteiner() {
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
		public void escreveTela(String mensagem) {
			areaText.append(mensagem+"\n");
			if (!areaText.getText().isEmpty() && !areaText.isFocusOwner()){
				areaText.setCaretPosition(areaText.getText().length());
			}
		}
	}
	
	public class Recebe extends Observable implements Runnable{

		byte[] dadosReceber = new byte[255];
        boolean erro = false;
        DatagramSocket socket = null;
        ModeloConexao conexao;
        
        public Recebe(RecebeObserver recebeObserver) {
        	addObserver(recebeObserver);
        	this.conexao = recebeObserver; 
		}
        
        @Override
        public void run() {
            while (true) {
                try {
                    socket = new DatagramSocket(conexao.getPorta());
                } catch (SocketException ex) {
                    System.out.println(ex.getMessage());
                }
                erro = false;
                while (!erro) {
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
                        String hora = getHora();
                        String nome ="/"+pacoteRecebido.getAddress().toString()+"\t"+hora+"\tMensagem "+": ";
                        notifica(nome + s);                   
                    } catch (Exception e) {
                        System.out.println("erro");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                        erro = true;
                        continue;
                    }
                }
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
        
        public void notifica(String mensagem){
        	setChanged();
        	notifyObservers(mensagem);
        }
	}
}
