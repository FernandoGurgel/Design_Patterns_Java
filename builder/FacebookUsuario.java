package builder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

public class FacebookUsuario extends UsuarioBuilder {

	private FacebookClient fbCli;
	
	public FacebookUsuario() {
		usuario.linkProjeto = "https://github.com/FernandoGurgel/Design_Patterns_Java/tree/master/builder";
		usuario.accessToken = "EAAByLmAysVgBAKhId4R5Jv6Wan8bhZCJerwRo68TaWslT6HcZC8EFPu0LvnTr2ZBHJU0EFctATtgZABjod3fYk4OPdt2aCwukJDtiQuU7igc1mbzwZA0mtnsYQzMapo9kjn8fIVqf0YuC484B2qUf3ksoySUk4kHg7DHL9WqwL88R5uZBmwVwE9kxdvDX6RRcZD";
		this.fbCli = new DefaultFacebookClient(usuario.accessToken);
		User user = fbCli.fetchObject("me", User.class);
		usuario.nome = user.getName();		
	}
	
	@Override
	public void initiApp() {
		usuario.idApp = "125543507997016";
		usuario.tokenApp = "636b657f41cdef0760c39fe190bbfe92";
	}

	@Override
	public String postMensagem(String mensagem) {
		String link;
		FacebookType type = fbCli.publish("me/feed", FacebookType.class, 
				Parameter.with("message", mensagem),
				Parameter.with("link", usuario.linkProjeto));
		link = "fb.com/"+type.getId();
		return (link.equals(null) ? "Erro ao postar" : link);
	}

	@Override
	public String postImagem(String mensagem) {
		String link;
		FileInputStream img = null;
		try {
			img = new FileInputStream(escolherArquivos());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		FacebookType type = fbCli.publish("me/photos", FacebookType.class,
				BinaryAttachment.with("teste.jpg", img),
				Parameter.with("message", mensagem+"\n\n\n"+usuario.linkProjeto));
		link = "fb.com/"+type.getId();
		return (link.equals(null) ? "Erro ao postar" : link);
	}

	@Override
	public String getNome() {
		return usuario.nome;
	}

}
