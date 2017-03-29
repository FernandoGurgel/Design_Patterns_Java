package composite;

import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends MenuComponent {
	
	ArrayList menuComponets = new ArrayList();
	String name;
	String descripion;
	
	public Menu(String name, String descripion) {
		this.name = name;
		this.descripion = descripion;
	}
	
	@Override
	public void add(MenuComponent menuComponent) {
		menuComponets.add(menuComponent);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescripion() {
		return descripion;
	}
	
	@Override
	public MenuComponent getchild(int i) {
		// TODO Auto-generated method stub
		return (MenuComponent) this.menuComponets.get(i);
	}
	
	@Override
	public void remove(MenuComponent menuComponent) {
		this.menuComponets.remove(menuComponent);
	}
	
	@Override
	public void print() {
		System.out.println("\n "+getName());
		System.out.println(" ,   "+getDescripion());
		System.out.println("-------------------");
		
		Iterator iterator = this.menuComponets.iterator();
		while (iterator.hasNext()) {
			MenuComponent menuComponent = (MenuComponent)iterator.next();
			menuComponent.print();
		}
	}
}
