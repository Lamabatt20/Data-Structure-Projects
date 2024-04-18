
public class Postfix implements Comparable<Postfix> {
	private String postfix;
	private static Stack<Equation> e = new Stack<>();

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public  Stack<Equation> getE() {
		return e;
	}

	public  void setE(Stack<Equation> e) {
		Postfix.e = e;
	}

	public Postfix(String postfix) {
		super();
		this.postfix = postfix;
	}

	public Postfix() {
		super();
	}

	@Override
	public String toString() {
		return postfix;
	}

	@Override
	public int compareTo(Postfix o) {

		return this.postfix.compareTo(o.postfix);
	}

	
}
