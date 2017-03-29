package composite;

public class MenuTestDrive {
	
	public static void main(String[] args) {
		MenuComponent  pancakeHouseMenu = new Menu("Pancake Menu","Breakfast");
		MenuComponent  dinerMenu = new Menu("Diner Menu","Launh");
		MenuComponent  cafeHouseMenu = new Menu("cafe Menu","Dinner");
		MenuComponent  dessrtMenu = new Menu("Dessrt Menu","dessrt of caurse");
		
		MenuComponent allMenus = new Menu("all menus","all menus combiend");
		allMenus.add(pancakeHouseMenu);
		allMenus.add(dinerMenu);
		allMenus.add(cafeHouseMenu);
		allMenus.add(dessrtMenu);
		
		dinerMenu.add(new MenuItem("macarrao", "espaguete ao oleo", true, 12.75));
		cafeHouseMenu.add(new MenuItem("cafe com leite", "cafe com leite 30ml", true, 0.75));
		dinerMenu.add(new MenuItem("Banana Frita", "Banana no oleio",true ,5.13));
		Waitress waitress = new Waitress(allMenus);
		waitress.printMenu();		
	}
}
