package sat.solver;

import java.util.ArrayList;

import sat.entities.Formula;

public class Instance {

	private int nVariables;
	private int nClauses;
	private Formula formula;
	private ArrayList<Integer> weights; // TODO change to int array?
	
	public int getnVariables() {
		return nVariables;
	}
	
	public void setnVariables(int nVariables) {
		this.nVariables = nVariables;
	}
	
	public int getnClauses() {
		return nClauses;
	}

	public void setnClauses(int nClauses) {
		this.nClauses = nClauses;
	}

	public Formula getFormula() {
		return formula;
	}
	
	public void setFormula(Formula formula) {
		this.formula = formula;
	}
	
	public ArrayList<Integer> getWeights() {
		return weights;
	}

	public void setWeights(ArrayList<Integer> weights) {
		this.weights = weights;
	}

	@Override
	public String toString() {
		return "Instance with " + this.nVariables + " variables & " + this.nClauses + " clauses\n" + this.formula.toString();
	}
}
