package builder;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public abstract class UsuarioBuilder {
	
	protected Usuario usuario;	
	protected String nomeImagem;	
	
	public File escolherArquivos(){
        File arquivos  = null;
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Escolha o(s) arquivo(s)...");
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setApproveButtonText("OK");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(false);
        int resultado = fc.showOpenDialog(fc);
        if (resultado == JFileChooser.CANCEL_OPTION){
            System.exit(1);
        }
        arquivos = fc.getSelectedFile();
        try {
			System.out.println(arquivos.getCanonicalPath());
			this.nomeImagem = arquivos.getName();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return arquivos;
    }
	public abstract void initiApp();
	public abstract String postMensagem(String mensagem);
	public abstract String postImagem(String mensagem);
	public abstract String getNome();
}
