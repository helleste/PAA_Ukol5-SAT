package sat.solver;

import java.util.BitSet;

import sat.annealing.SimulatedAnnealing;
import sat.bruteforce.BruteForce;
import sat.filehandle.FileLoader;
import sat.filehandle.ResultWriter;
import sat.timemeasure.CPUTiming;

public class Solver {

	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.out.println("Usage: Solver.java literals clauses");
			return;
		}
		
		int solutionFound = 0;
//		float relativeError, totalRelativeError = 0;
//		long startTime = CPUTiming.getCpuTime();
		for (int i = 1; i < 100; i++) {
			FileLoader loader = new FileLoader(Integer.parseInt(args[0]), Integer.parseInt(args[1]), i);
			ResultWriter rw = new ResultWriter();
			Instance instance = loader.loadInstance();
			SimulatedAnnealing sa = new SimulatedAnnealing();
			// Load brute force solver
//			BruteForce bf = new BruteForce();
			
			// Solve by SA
			State resultState0 = sa.solve(instance);
			// Was solution found?
			if (resultState0.getSumWeight() != 0){ 
				solutionFound++;
				//State resultState = bf.solve(instance);
				//int optWeight = resultState.getSumWeight();
				//int appWeight = resultState0.getSumWeight();
				//relativeError = relativeError(optWeight, appWeight);
				//totalRelativeError += relativeError;
			}
			System.out.println("Solution: " + toString(resultState0.getVariables()));
			System.out.println("SumWeight: " + resultState0.getSumWeight());
//			System.out.println("Solution: " + toString(resultState.getVariables()));
//			System.out.println("SumWeight: " + resultState.getSumWeight());
			
			// Write a result to a file
			rw.writeResultFile(instance, toString(resultState0.getVariables()),i);
		}
//		long cpuTime = CPUTiming.getCpuTime() - startTime;
//		System.out.println((float)cpuTime/(float) 99);
//		System.out.println("total relative error :"  + totalRelativeError/99);
//		System.out.println((double) solutionFound / (double) 99 + " % of solutions found,");
	}
	
	private static String toString(BitSet bs) {
        return Long.toString(bs.toLongArray()[0], 2);
    }
	
	// Compute the relative error
	private static float relativeError(int optWeight, int appWeight) {
		return Math.abs(((float)optWeight - (float)appWeight))/Math.abs((float)optWeight);
	}

}
