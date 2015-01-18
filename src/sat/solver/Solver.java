package sat.solver;

import java.util.BitSet;

import sat.annealing.SimulatedAnnealing;
import sat.annealing.State;
import sat.filehandle.FileLoader;

public class Solver {

	public static void main(String[] args) {
		FileLoader loader = new FileLoader();
		Instance instance = loader.loadInstance();
		SimulatedAnnealing sa = new SimulatedAnnealing();
		
		State resultState = sa.solve(instance);
		System.out.println("Solution: " + toString(resultState.getVariables()));
		System.out.println("SumWeight: " + resultState.getSumWeight());

		

	}
	
	private static String toString(BitSet bs) {
        return Long.toString(bs.toLongArray()[0], 2);
    }

}
