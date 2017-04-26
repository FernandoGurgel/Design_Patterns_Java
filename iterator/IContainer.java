package iterator;

import java.util.Iterator;

/**
 * Iterator - Design Patterns
 *
 * @author Fernando gurgel
 * @version v1.0 26 Abr 2017
 */

public interface IContainer<E> {

	public Iterator<E> iniciarIterator();
	public String add(Pessoa pessoa);
	public String remove(Pessoa pessoa);
	public void ordenarLista();
	
}
