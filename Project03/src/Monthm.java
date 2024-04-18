import java.util.Comparator;

public class Monthm {

	private String month;
	private static AvlTree<Day> dAvl = new AvlTree<>(new Comparator<Day>() {

		@Override
		public int compare(Day o1, Day o2) {
			return o1.getDay() - o2.getDay();
		}
	});

	public static AvlTree<Day> getdAvl() {
		return dAvl;
	}

	public static void setdAvl(AvlTree<Day> dAvl) {
		Monthm.dAvl = dAvl;
	}

	public Monthm(String month) {
		super();
		this.month = month;
	}

	public Monthm(int month) {
		super();
		this.month = Monthm.getMonthName(month);
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override

	public String toString() {
		return month;
	}

	public static String getMonthName(int month) {
		switch (month) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";

		default:
			return null;
		}
	}
}
