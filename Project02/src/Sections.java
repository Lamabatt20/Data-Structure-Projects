
public class Sections implements Comparable<Sections> {
	public static Stack<Infix> infix = new Stack<>();
	public static Stack<Postfix> postfix = new Stack<>();
	private String section;
	public static Stack<Infix> getInfix() {
		return infix;
	}

	public static void setInfix(Stack<Infix> infix) {
		Sections.infix = infix;
	}

	public static Stack<Postfix> getPostfix() {
		return postfix;
	}

	public static void setPostfix(Stack<Postfix> postfix) {
		Sections.postfix = postfix;
	}

	@Override
	public int compareTo(Sections o) {
		return this.section.compareTo(o.section);
	}

	@Override
	public String toString() {
		return "Sections [section=" + section + "]";
	}
	

}
