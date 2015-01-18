package sat.entities;

import java.util.ArrayList;
import java.util.BitSet;

public class Formula {

	private ArrayList<Clause> clauses = new ArrayList<Clause>();

	public void addClause(Clause newClause) {
		clauses.add(newClause);
	}
	
	public ArrayList<Clause> getClauses() {
		return clauses;
	}

	public void setClauses(ArrayList<Clause> clauses) {
		this.clauses = clauses;
	}
	
	// Determines if the formula is satisfied with current variables evaluation
	public Boolean isSatisfied(BitSet variables) {
		int satCount = 0;
		
		for (Clause clause : clauses) {
			satCount = 0;
			
			for (Literal literal : clause.getLiterals()) {
				if (variables.get(literal.getId() - 1) && !literal.getNegativity())
					break;
				else if (!variables.get(literal.getId() - 1) && literal.getNegativity())
					break;
				else
					satCount++;
			}
			
			if (satCount == 3) 
				return false; 
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		String output = "F = ";
		
		for (Clause clause : clauses) {
			output += clause.toString();
			output += ".";
		}
		
		return output;
	}
}
