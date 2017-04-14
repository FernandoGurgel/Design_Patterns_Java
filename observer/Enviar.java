package observer;

import java.awt.GridLayout;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Enviar implements Observer{
	
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
		tela.apresentaMensagem("Conectando.....");
		new Thread(new ReceberNotificacao(this,true,conexao));
	}
	
	public void cancelarNotificacao(ModeloConexao conexao){
		new Thread(new ReceberNotificacao(this,false, conexao));		
	}
	
	private class ReceberNotificacao extends Observable implements Runnable{
		
		byte[] dadosReceber = new byte[255];
        boolean erro = false;
        DatagramSocket socket = null;
		private Enviar enviar;
		private ModeloConexao conexao;
		
		public ReceberNotificacao(Enviar enviar, boolean op, ModeloConexao conexao) {
			this.enviar = enviar;
			
			if (op){
				addObserver(enviar);
			}else if (op){
				deleteObserver(enviar);
			}
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
                        String mensagem ="\tMensagem ("+hora+"): "+s;
                        notifyObservers(mensagem);                   
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
	
}
