package composite;

public class MenuItem extends MenuComponent{
	
	String name;
	String description;
	boolean vegetarian;
	double price;
	
	public MenuItem(String name, String description, boolean vegetarion, double price) {
		super();
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarion;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isVegetarion() {
		return vegetarian;
	}

	public double getPrice() {
		return price;
	}
	
	@Override
	public void print() {
		System.out.println("   "+ getName());
		if(isVegetarion()){
			System.out.println("(V)");
		}
		System.out.println(",  "+ getPrice());
		System.out.println("    --"+getDescription());
	}
	
	
}
