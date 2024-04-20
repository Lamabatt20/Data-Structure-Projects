package Application;

public class Brand {
	private String brand;
	public SLL list=new SLL();

	public SLL getList() {
		return list;
	}

	public void setList(SLL list) {
		this.list = list;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Brand(String brand) {
		super();
		this.brand = brand;
	}

	public Brand() {
		super();
	}

	@Override
	public String toString() {
		return  brand ;
	}
	

}
