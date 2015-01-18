package sat.annealing;

import java.util.BitSet;
import java.util.Random;

import sat.solver.Instance;

public class SimulatedAnnealing {
	
	private Instance instance;
	private State state;
	private State bestState;
	private double temperature = 0;
	private double COOLING_FACTOR;
	private double ENDING_TEMPERATURE;
	private int M; 
	
	public State solve(Instance instance) {
		this.instance = instance;
		state = new State(0);
		bestState = new State(0);
		//M = 10 * instance.getnVariables(); // TODO Find a correct parameter
		M = 2500;
		
		setInitState();
		temperature = setInitTemperature();
		ENDING_TEMPERATURE = setEndingTemperature();
		COOLING_FACTOR = setCoolingFactor();
		//System.out.println(COOLING_FACTOR);
		
		while (temperature > ENDING_TEMPERATURE) {
			for (int m = 0; m < M; m++) {
				state = getNextState();
				System.out.println(state.toString());
				
				if ((state.getSumWeight() > bestState.getSumWeight()) && state.getFormulaSatisfied())
					bestState = state;
			}
			
			cool();
		}
		
		return bestState;
	}
	
	private void setInitState() {
		//Random r = new Random();
		//int x = r.nextInt(instance.getnVariables());
		//int x = r.nextInt((int) (Math.pow(2, instance.getnVariables()) - 1) - (int) (Math.pow(2, instance.getnVariables()) - 1)/2) + (int) (Math.pow(2, instance.getnVariables()) - 1)/2;
		//state.setVariables(x);
		//state.setSumWeight(state.countSumWeight(instance.getWeights()));
		//state.setFormulaSatisfied(instance.getFormula().isSatisfied(state.getVariables()));
	}
	
	private State getNextState() {
		State newState = new State(0);
		newState = getNeighbour();
		
		/*do {
			newState = getNeighbour();
		} while (!newState.getFormulaSatisfied());*/
		
		int delta = newState.getSumWeight() - state.getSumWeight();
		
		if (delta > 0)
			return newState;
		else {
			double x = Math.random();
			if (x < Math.exp(delta/temperature))
				return newState;
			else
				return state;
		}
	}
	
	private State getNeighbour() {
		State newState = new State(state);
		Random r = new Random();
		int x = r.nextInt(instance.getnVariables());
		
		newState.getVariables().flip(x);
		newState.setSumWeight(newState.countSumWeight(instance.getWeights()));
		newState.setFormulaSatisfied(instance.getFormula().isSatisfied(newState.getVariables()));
		
		return newState;
	}
	
	// TODO find a correct parameter
	private double setInitTemperature() {
		return 2000;
	}
	
	// TODO find a correct parameter
	private double setEndingTemperature() {
		return 50;
		
	}
	
	// TODO find a correct parameter
	private double setCoolingFactor() {
		return 0.9;
			
	}
	
	private void cool() {
		temperature = temperature * COOLING_FACTOR;
	}
}
