package Application;

public class CarInfo {
	private String model;
	private String year;
	public CarInfo() {
		super();
	}
	private String color;
	private String price;
	public CarInfo(String model, String year, String color, String price) {
		super();
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return  model + "," + year + "," + color + ", " + price ;
	}
	
	

}
