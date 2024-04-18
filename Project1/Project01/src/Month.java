
public class Month implements Comparable<Month>   {
	private int month;
	private static LinkedList<Day>dList=new LinkedList<Day>();
	

	public static LinkedList<Day> getdList() {
		return dList;
	}

	public static void setdList(LinkedList<Day> dList) {
		Month.dList = dList;
	}

	public Month(int month) {
		super();
		this.month = month;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return  month +"";
	}
	@Override
    public int compareTo(Month otherMonth) {
                return Integer.compare(this.month, otherMonth.month);
    }
	

}
