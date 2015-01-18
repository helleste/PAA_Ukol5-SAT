package sat.entities;

import java.util.ArrayList;

public class Clause {

	private ArrayList<Literal> literals = new ArrayList<Literal>(3);

	// TODO constructor from literals
	/*public Clause(Literal literal1, Literal literal2, Literal literal3) {
		literals.set(0, literal1);
		literals.set(1, literal2);
		literals.set(2, literal3);
	}*/
	
	public void addLiteral(Literal literal) {
		if (literals.size() < 3)
			literals.add(literal);
		else
			System.out.println("Clause already has 3 literals");
	}
	
	public ArrayList<Literal> getLiterals() {
		return literals;
	}

	public void setLiterals(ArrayList<Literal> literals) {
		this.literals = literals;
	}
	
	@Override
	public String toString() {
		String output = "(\n";
		
		for (Literal literal : literals) {
			output += literal.toString();
			output += "\n+\n";
		}
		
		return output += "\n)";
	}
	
}
