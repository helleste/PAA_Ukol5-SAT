package sat.filehandle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import sat.entities.Clause;
import sat.entities.Formula;
import sat.entities.Literal;
import sat.solver.Instance;


public class FileLoader {

	private String filename = "src/Files/uf20-0100.cnf";
	private Instance instance = new Instance();
	
	// Parse the file and create instance from the data
	public Instance loadInstance() {
		
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			Formula formula = new Formula();
			
			// Read till you reach EOF
			while ((strLine = br.readLine()) != null) {
				String[] lineData = strLine.split(" ");
				
				switch (lineData[0]) {
				case "c":
					break;
				case "p":
					parseHeader(lineData);
					instance.setWeights(generateWeights());
					break;
				case "0":
					break;
				case "%":
					break;
				case "":
					break;
				default:
					formula.addClause(parseData(lineData, instance.getWeights()));
					break;
				}
			}
			
			// Add Formula to instance
			this.instance.setFormula(formula);
			
			// Close the input stream
			in.close();
			
			// Returns an instance
			return this.instance;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	private void parseHeader(String[] lineData) {
		instance.setnVariables(Integer.parseInt(lineData[2]));
		instance.setnClauses(Integer.parseInt(lineData[3]));
	}
	
	private ArrayList<Integer> generateWeights() {
		ArrayList<Integer> weights = new ArrayList<Integer>(instance.getnVariables());
		weights.add(0,0);
		Random r = new Random();
		
		for (int i = 1; i <= instance.getnVariables(); i++) {
			int x = r.nextInt(100-1)+1;
			weights.add(i, x);
		}
		
		return weights;
	}
	
	private Clause parseData(String[] lineData, ArrayList<Integer> weights) {
		Clause clause = new Clause();
		
		for(int i = 0; !lineData[i].equals("0"); i++) {
			int lit = Integer.parseInt(lineData[i]);
			int id = Math.abs(lit);
			int weight = weights.get(id);
			Boolean negativity = (lit < 0) ? true : false;
			Literal literal = new Literal(id, weight, negativity);
			clause.addLiteral(literal);
		}
		
		return clause;
	}
}
