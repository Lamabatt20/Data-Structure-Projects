import java.util.Comparator;

public class Yeary implements Comparable<Yeary> {
	private int year;
	private static AvlTree<Monthm> mAvl = new AvlTree<Monthm>(new Comparator<Monthm>() {

		@Override
		public int compare(Monthm o1, Monthm o2) {

			return o1.getMonth().compareTo(o2.getMonth());
		}

	});

	public static AvlTree<Monthm> getmAvl() {
		return mAvl;
	}

	public static void setmAvl(AvlTree<Monthm> mAvl) {
		Yeary.mAvl = mAvl;
	}

	public Yeary(int year) {
		super();
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return year + " ";
	}

	@Override
	public int compareTo(Yeary o) {
		return year - o.year;
	}

}
