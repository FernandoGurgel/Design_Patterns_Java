package builder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterUsuario extends UsuarioBuilder {

	private ConfigurationBuilder twitter = new ConfigurationBuilder();
	
	public TwitterUsuario() {
		setLinkProjeto ("https://github.com/FernandoGurgel/Design_Patterns_Java/tree/master/builder");
		setAccessToken ("YxFos341xK5jFziG3csiwQpIy");
		setTokenApp ("VzfngPdiFaAI8rr03gMIaNr0QLlmyxaKtqGxNo7DmocHeW5NvO");
		setToken ("869911459911880705-kTxHnjv98VbMi0VJBknzCgAyojrOc6e");
		setIdApp ("pDZJXTZLyS3tXPNp2aoTOJSixzPDMc3jQUgCMczz2QWKk");
	}
	
	@Override
	public String postMensagem(String mensagem) {
		Status resposta = null;
		TwitterFactory twitterFactory = new TwitterFactory(this.twitter.build());
		Twitter twitter = twitterFactory.getInstance();
		try {
			resposta = twitter.updateStatus(mensagem);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resposta.getText();
	}

	@Override
	public String postImagem(String mensagem) {
		Status resposta = null;
		InputStream img = null;
		TwitterFactory twitterFactory = new TwitterFactory(this.twitter.build());
		Twitter twitter = twitterFactory.getInstance();
		StatusUpdate update = new StatusUpdate(mensagem);
		
		try {
			img = new FileInputStream(escolherArquivos());
			update.setMedia("teste.jpg",img);
			resposta = twitter.updateStatus(update);
		} catch (TwitterException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return resposta.getText();
	}

	@Override
	public String getNome() {
		
		return null;
	}

	public TwitterUsuario initiApp() {
		twitter.setDebugEnabled(true);
		twitter.setOAuthConsumerKey(getAccessToken());
		twitter.setOAuthConsumerSecret(getTokenApp());
		twitter.setOAuthAccessToken(getToken());
		twitter.setOAuthAccessTokenSecret(getIdApp());
		return this;
	}
	
	public static void main(String[] args) {
		TwitterUsuario twitterUsuario = new TwitterUsuario();
		System.out.println(twitterUsuario.initiApp().postImagem("Ola mundo"));
	}

}
