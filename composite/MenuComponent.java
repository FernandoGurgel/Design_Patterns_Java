package composite;

public abstract class MenuComponent {
	
	public void add(MenuComponent menuComponent){
		throw new UnsupportedOperationException();
	}
	public void remove(MenuComponent menuComponent){
		throw new UnsupportedOperationException();
	}
	public MenuComponent getchild(int i){
		throw new UnsupportedOperationException();
	}
	public String getNome(){
		throw new UnsupportedOperationException();
	}
	public String getDescription(){
		throw new UnsupportedOperationException();
	}
	public double getPrice(){
		throw new UnsupportedOperationException();
	}
	public boolean isVagetarian(){
		throw new UnsupportedOperationException();
	}
	public void print(){
		throw new UnsupportedOperationException();
	}
}
