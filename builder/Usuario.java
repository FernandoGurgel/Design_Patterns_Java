package builder;

import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

public class Usuario {
	
	@Facebook
	private String nome;
	@Facebook
	private String sobrenome;
	private String idApp;
	private String tokenApp;
	private String accessToken;
	private FacebookClient fbCli;

	public Usuario() {
		this.accessToken = "EAAByLmAysVgBAB6JFXaZAZCXLrBZB4YL8Xrj80XvOaZBf0gqkzqeLsuIEPajmKoLvxTZB3qIa48cRq3b6gbJW2rRzfGkp4jH0EndxS5GaSMbreJ08ivQ1yD5pglLNt9ajFZAIzJM0ROI8Ijc2pv9kayId3T9OBRRzzQHBytd3mamGXUFfdZCQU37Bi11xcME4bc6jTKbAJLyNckHJDgPHxM";
		this.fbCli = new DefaultFacebookClient(accessToken);
		User user = fbCli.fetchObject("me", User.class);
		this.nome = user.getName();
	}
	
	public void initiApp(){
		this.idApp = "125543507997016";
		this.tokenApp = "636b657f41cdef0760c39fe190bbfe92";
	}
	
	public String postMensagem(String menssagem){
		String link;
		FacebookType type = fbCli.publish("me/feed", FacebookType.class, Parameter.with("message", menssagem));
		link = "fb.com/"+type.getId();
		return (link.equals(null) ? "Erro ao postar" : link);
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getSobrenome(){
		return sobrenome;
	}
	
	
}
