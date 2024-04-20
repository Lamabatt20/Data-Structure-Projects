package Application;


public class Order {
	private  String CustomerName;
	private String  CustomerMobile;
	private String Brand;
	private String Model;
	private String Year;
	private String Color;
	private String Price;
	private String orderDate;
	private String orderStatus;
	
	public Order(String CustomerName, String CustomerMobile, String Brand, String Model, String Year, String Color,
			String Price, String orderDate, String orderStatus) {
		this.CustomerName = CustomerName;
		this.CustomerMobile = CustomerMobile;
		this.Brand = Brand;
		this.Model = Model;
		this.Year = Year;
		this.Color = Color;
		this.Price = Price;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getCustomerMobile() {
		return CustomerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		CustomerMobile = customerMobile;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public Order(String CustomerName, String customerMobile, String Brand, String Model, String Year, String Color,
			String Price, String orderDate) {
		super();
		this.CustomerName = CustomerName;
		this.CustomerMobile = customerMobile;
		this.Brand = Brand;
		this.Model = Model;
		this.Year = Year;
		this.Color = Color;
		this.Price = Price;
		this.orderDate = orderDate;
	}

	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return  orderDate + "," + orderStatus ;
	}
	
	

}
