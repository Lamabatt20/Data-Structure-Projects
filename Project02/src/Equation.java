
public class Equation implements Comparable<Equation> {
	String equation;

	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	@Override
	public String toString() {
		return  equation ;
	}

	public Equation(String equation) {
		super();
		this.equation = equation;
	}

	public Equation() {
		super();
	}

	@Override
	public int compareTo(Equation o) {
		return this.equation.compareTo(o.equation);
	}
	
	

}
