

public class Year implements Comparable<Year> {
	private int year;
	private static LinkedList<Month>mList=new LinkedList<Month>();
	public static LinkedList<Month> getmList() {
		return mList;
	}

	public static void setmList(LinkedList<Month> mList) {
		Year.mList = mList;
	}

	public Year(int year) {
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
		return  year + " ";
	}
    @Override
    public int compareTo(Year otherYear) {
                return Integer.compare(this.year, otherYear.year);
    }

	
    

	
}
