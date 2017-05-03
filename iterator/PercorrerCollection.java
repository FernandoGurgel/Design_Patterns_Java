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
	private boolean inversa;
	
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
	
	@Override
	public Iterator<Pessoa> listaInversa() {
		inversa = true;
		return new PercorrerIterator(inversa);
	}
	
	public class PercorrerIterator implements Iterator<Pessoa>{
		
		private int index;
		
		public PercorrerIterator() {}
		
		public PercorrerIterator(boolean inversa) {
			if(inversa){
				index = lista.size();
			}
		}

		@Override
		public boolean hasNext() {
			if (inversa && index > 0){
				return true;
			}if (index < lista.size() && !inversa){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public Pessoa next() {
			if(inversa && this.hasNext()){
				index --;
				return lista.get(index);
			}
			if (this.hasNext() && !inversa){
				return lista.get(index++);
			}else{
				return null;
			}
		}
		
		
		
	}
}
