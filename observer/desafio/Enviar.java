package observer.desafio;

import java.awt.GridLayout;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Enviar implements Observer{
	
	private boolean vida = false;	
	private Thread receber;
	private TelaReceber tela;
	private ModeloConexao conexao;
	
	public Enviar(ModeloConexao conexao) {
		this.conexao = conexao;
		tela = new TelaReceber();
		
		enviarSolicitacao(conexao);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.conexao.setMensagem(arg.toString());
		this.tela.apresentaMensagem(conexao.getMensagem());
	}
	
	public void enviarSolicitacao(ModeloConexao conexao){
		tela.apresentaMensagem("Solicitação enviada.....");
		enviandoSolicitacao(conexao,"entrar");
		receber = new Thread(new ReceberMensagem(this, true));
		vida = true;
		receber.start();
	}
	
	public void cancelarNotificacao(ModeloConexao conexao){
		vida = false;
		receber = new Thread(new ReceberMensagem(this, false));
		receber.start();
		enviandoSolicitacao(conexao, "sair");
	}
	
	private void enviandoSolicitacao(ModeloConexao conexao,String msg) {
		try{
			byte[] mensagem = msg.getBytes();
			DatagramSocket observadoSocket = new DatagramSocket();
			InetAddress endereco = InetAddress.getByName(conexao.getIp());
			DatagramPacket pacote = new DatagramPacket(mensagem, mensagem.length, endereco, conexao.getPorta());
			observadoSocket.send(pacote);
			observadoSocket.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class ReceberMensagem extends Observable implements Runnable{
		
		byte[] dadosReceber = new byte[255];
        boolean erro = false;
        DatagramSocket socket = null;
		
		public ReceberMensagem(Enviar enviar, boolean op) {	
			if (op){
				addObserver(enviar);
			}else if (op){
				deleteObserver(enviar);
			}
		}

		

		@Override
		public void run() {
			while (vida) {
                try {
                    socket = new DatagramSocket(conexao.getPorta());
                } catch (SocketException ex) {
                    System.out.println(ex.getMessage());
                }
                
                while (vida) {
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
                        String mensagem ="Mensagem ("+hora+"): "+s;
                        setChanged();
                        notifyObservers(mensagem);                   
                    } catch (Exception e) {
                        System.out.println("erro");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
		} // fim do me todo run
		
		public String getHora() {

        	String sb;
        	GregorianCalendar d = new GregorianCalendar();

        	sb = String.valueOf(d.get( GregorianCalendar.HOUR_OF_DAY))+":";
        	sb += String.valueOf( d.get( GregorianCalendar.MINUTE ) )+":";
        	sb += String.valueOf( d.get( GregorianCalendar.SECOND ) );

        	return sb.toString();
        }		
	}
	
	private class TelaReceber extends JFrame{
		
		private static final long serialVersionUID = 1L;
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
			
			JScrollPane panel = new JScrollPane();
			areaText = new JTextArea();
			GridLayout layout = new GridLayout(1, 1);
			
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
	
	public static void main(String[] args) {
		ModeloConexao conexao = new ModeloConexao("10.100.32.22", 5000);
		Enviar enviar = new Enviar(conexao);
		enviar.cancelarNotificacao(conexao);
	}
}