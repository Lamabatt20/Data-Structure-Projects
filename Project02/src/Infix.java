
public class Infix implements Comparable<Infix> {
	public String infix;
	public static Stack<Equation> e = new Stack<>();

	public String getInfix() {
		return infix;
	}

	public void setInfix(String infix) {
		this.infix = infix;
	}

	public  Stack<Equation> getE() {
		return e;
	}

	public Infix(String infix) {
		super();
		this.infix = infix;
	}

	public  void setE(Stack<Equation> e) {
		Infix.e = e;
	}

	@Override
	public int compareTo(Infix o) {

		return this.infix.compareTo(o.infix);
	}

	public Infix() {
		super();
	}

	@Override
	public String toString() {
		return infix;
	}

}
