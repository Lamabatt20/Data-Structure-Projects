
public class Day {
	private int day;
	Electricity e;

	public Electricity getE() {
		return e;
	}

	public void setE(Electricity e) {
		this.e = e;
	}

	public Day(int day) {
		super();
		this.day = day;
	}

	public Day(int day, Electricity e) {
		super();
		this.day = day;
		this.e = e;
	}

	public int getDay() {
		return day;
	}

	@Override
	public String toString() {
		return day + " ";
	}

	public void setDay(int day) {
		this.day = day;
	}

}
