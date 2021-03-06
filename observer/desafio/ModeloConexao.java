package observer.desafio;

/**
 * Modelo de uma conexao de uma rede local 
 * utilizando observer;
 * 
 * @author Gurgel,Fernando
 * @GitHub https://github.com/FernandoGurgel 
 * @version v1.23
 */


public class ModeloConexao {
	
	private String ip;
	private int porta;
	private String mensagem;
	
	public ModeloConexao(String ip, int porta) {
		this.ip = ip;
		this.porta = porta;
	}	
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int getPorta() {
		return porta;
	}
	
	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem){
		this.mensagem = mensagem;
	}
}