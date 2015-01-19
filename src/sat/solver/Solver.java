package sat.solver;

import java.util.BitSet;

import sat.annealing.SimulatedAnnealing;
import sat.bruteforce.BruteForce;
import sat.filehandle.FileLoader;
import sat.filehandle.ResultWriter;

public class Solver {

	public static void main(String[] args) {
		FileLoader loader = new FileLoader();
		ResultWriter rw = new ResultWriter();
		Instance instance = loader.loadInstance();
		SimulatedAnnealing sa = new SimulatedAnnealing();
		BruteForce bf = new BruteForce();
		
		State resultState0 = sa.solve(instance);
		State resultState = bf.solve(instance);
		System.out.println("Solution: " + toString(resultState0.getVariables()));
		System.out.println("SumWeight: " + resultState0.getSumWeight());
		System.out.println("Solution: " + toString(resultState.getVariables()));
		System.out.println("SumWeight: " + resultState.getSumWeight());
		rw.writeResultFile(instance, toString(resultState.getVariables()));

		

	}
	
	private static String toString(BitSet bs) {
        return Long.toString(bs.toLongArray()[0], 2);
    }

}
