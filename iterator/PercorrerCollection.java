package iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator - Design Patterns
 *
 * @author Fernando gurgel
 * @version v1.0 26 Abr 2017
 */

public class PercorrerCollection implements IContainer<Pessoa>{

	private List<Pessoa> lista;
	
	public PercorrerCollection() {
		this.lista = new ArrayList<>();
	}

	@Override
	public Iterator<Pessoa> iniciarIterator() {
		return new PercorrerIterator();
	}

	@Override
	public String add(Pessoa pessoa) {
		lista.add(pessoa);
		return "Pessoa cadastrada com sucesso!!";
	}

	@Override
	public String remove(Pessoa pessoa) {
		
		for (Pessoa p: lista){
			if (p.getNome().equals(pessoa.getNome())){
				lista.remove(p);
				return "Pessoa excluida com sucesso!!";
			}
		}
		return "Pessoa não encontrada!!";
	}
	
	@Override
	public void ordenarLista() {
		Collections.sort(lista,new Comparator<Pessoa>() {

			@Override
			public int compare(Pessoa o1, Pessoa o2) {
				return  o1.getNome().compareTo(o2.getNome());
			}
		});
	}
	
	
	public class PercorrerIterator implements Iterator<Pessoa>{

		private int index;
		
		@Override
		public boolean hasNext() {
			if (index < lista.size()){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public Pessoa next() {
			if (this.hasNext()){
				return lista.get(index++);
			}else{
				return null;
			}
		}
		
	}
}
