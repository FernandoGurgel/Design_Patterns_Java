package builder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

public class FacebookUsuario extends UsuarioBuilder {

	private FacebookClient fbCli;
	
	public FacebookUsuario() {
		setLinkProjeto ("https://github.com/FernandoGurgel/Design_Patterns_Java/tree/master/builder");
		setIdApp ("125543507997016");
		setTokenApp ("636b657f41cdef0760c39fe190bbfe92");
		setAccessToken ("EAAByLmAysVgBAKz7GySJzykx2cLvZB3C8xo3SfFYaSKZCmSa9118r9rZC1pDKZCd7XZCK5xBBnN46icnIz9jdibqTZBpqkbIEvLV0Mxujj9y9fJ77yZAWuhWN2EGBvAEIBy7ZA84v2ogDRY6zFtIBnz7q5ZBX39kYhDHB9wwQJEBmDRIoeSU5ZBQGkkUzf7pgwUn4ZD");
	}

	@Override
	public String postMensagem(String mensagem) {
		String link;
		FacebookType type = fbCli.publish("me/feed", FacebookType.class, 
				Parameter.with("message", mensagem),
				Parameter.with("link", getLinkProjeto()));
		link = "fb.com/"+type.getId();
		return (link.equals(null) ? "Erro ao postar" : link);
	}

	@Override
	public String postImagem(String mensagem) {
		String link;
		InputStream img = null;
		try {
			img = new FileInputStream(escolherArquivos());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		FacebookType type = fbCli.publish("me/photos", FacebookType.class,
				BinaryAttachment.with("teste.jpg", img),
				Parameter.with("message", mensagem+"\n\n\n"+getLinkProjeto()));
		link = "fb.com/"+type.getId();
		return (link.equals(null) ? "Erro ao postar" : link);
	}

	@Override
	public String getNome() {
		return getNome();
	}

	public FacebookUsuario initiApp() {
		this.fbCli = new DefaultFacebookClient(getAccessToken());
		User user = fbCli.fetchObject("me", User.class);
		setNome (user.getName());		
		return this;
	}
	
	public static void main(String[] args) {
		FacebookUsuario facebookUsuario = new FacebookUsuario();
		facebookUsuario.initiApp().postImagem("fer");
	}
}
