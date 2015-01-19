package sat.bruteforce;

import sat.solver.Instance;
import sat.solver.State;

import java.util.BitSet;

public class BruteForce {
	
	// Solves the problem using brute force
	public State solve(Instance instance) {
		State curState = new State(0);
		State bestState = new State(0);
		
		// Taking every number from the interval (1,2^n - 1) and try its binary form
		for (int i = 0; i < Math.pow(2, instance.getnVariables()); i++) {
			BitSet bs = BitSet.valueOf(new long[]{i});
			
			curState.setVariables(bs);
			curState.setFormulaSatisfied(instance.getFormula().isSatisfied(curState.getVariables()));
			if (curState.getFormulaSatisfied()) {
				curState.setSumWeight(curState.countSumWeight(instance.getWeights()));
				
				if (curState.getSumWeight() > bestState.getSumWeight())
					bestState = new State(curState);
			}
		}
	
	return bestState;
	
	}

}
