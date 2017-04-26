package iterator;

import java.util.Iterator;

/**
 * Iterator - Design Patterns
 *
 * @author Fernando gurgel
 * @version v1.0 26 Abr 2017
 */

public class TesteIterator {

	
	private PercorrerCollection collection;
	
	public TesteIterator() {
		collection = new PercorrerCollection();
	}
	
	public void addPessoa(Pessoa pessoa){
		System.out.println(collection.add(pessoa));
	}
	
	
	public void removePessoa(Pessoa pessoa){
		System.out.println(collection.remove(pessoa));
	}
	
	public void lista(){
		Iterator<Pessoa> iterator = collection.iniciarIterator();
		
		while(iterator.hasNext()){
			System.out.println(iterator.next().getNome());
		}
	}
	
	public void listaOrdenada(){
		
		collection.ordenarLista();
		
		System.out.println("\n\n------------------ Lista Ordenanda ---------------\n\n");
		Iterator<Pessoa> iterator = collection.iniciarIterator();
		
		while(iterator.hasNext()){
			System.out.println(iterator.next().getNome());
		}
	}
	
	
	public static void main(String[] args) {
		
		PercorrerCollection collection = new PercorrerCollection();
		System.out.println(collection.add(new Pessoa("Fernando")));
		System.out.println(collection.add(new Pessoa("Albert")));
		System.out.println(collection.add(new Pessoa("Rafael")));
		System.out.println(collection.add(new Pessoa("Abilio")));
		
		Iterator<Pessoa> iterator = collection.iniciarIterator();
		
		while(iterator.hasNext()){
			System.out.println(iterator.next().getNome());
		}
		
		collection.ordenarLista();
		
		iterator = collection.iniciarIterator();
		
		while(iterator.hasNext()){
			System.out.println(iterator.next().getNome());
		}
		

	}

}
