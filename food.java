package Hotell;

public class food {

	double price;
	String type;

	public food(double price) {

		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	food sandwich = new food(30);
	food pasta = new food(50);
	food noodles = new food(20);
	food cola = new food(10);

}
