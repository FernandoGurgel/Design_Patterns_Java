package builder;

public class TesteFacebook {

	public static void main(String[] args) {
		Usuario usuario = new Usuario();
		System.out.println(usuario.getNome()+" "+usuario.getSobrenome());
		System.out.println(usuario.postMensagem("Estou com Albert num teste de java"));		
	}
}
