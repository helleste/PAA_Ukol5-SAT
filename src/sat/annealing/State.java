package sat.annealing;

import java.util.ArrayList;
import java.util.BitSet;

public class State {

	private BitSet variables;
	private int sumWeight;
	private Boolean formulaSatisfied;
	
	public State(int nVariables) {
		this.variables = BitSet.valueOf(new long[]{(long) Math.pow(2, nVariables)});
		this.sumWeight = 0;
		this.formulaSatisfied = false;
	}
	
	public State(State oldState) {
		this.variables = (BitSet) oldState.getVariables().clone();
		this.sumWeight = oldState.getSumWeight();
		this.formulaSatisfied = oldState.getFormulaSatisfied();
	}
	
	public BitSet getVariables() {
		return variables;
	}
	
	public void setVariables(BitSet variables) {
		this.variables = variables;
	}
	
	public void setVariables(int x) {
		this.variables = BitSet.valueOf(new long[]{x});
	}
	
	public int getSumWeight() {
		return sumWeight;
	}
	
	public void setSumWeight(int sumWeight) {
		this.sumWeight = sumWeight;
	}
	
	public Boolean getFormulaSatisfied() {
		return formulaSatisfied;
	}
	
	public void setFormulaSatisfied(Boolean formulaSatisfied) {
		this.formulaSatisfied = formulaSatisfied;
	}
	
	public int countSumWeight(ArrayList<Integer> weights) {
		int sumWeight = 0;
		
		for (int i = variables.nextSetBit(0); i >= 0; i = variables.nextSetBit(i+1)) {
			sumWeight += weights.get(i+1);
		}
		
		return sumWeight;
	}
	
	@Override
	public String toString() {
		return "State\n" + "Variables: " + Long.toString(variables.toLongArray()[0], 2) + "\nsumWeight: " + sumWeight + "\nsat: " + formulaSatisfied.toString();
	}
	
}
