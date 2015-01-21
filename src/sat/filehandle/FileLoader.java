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

	private String filename;
	private Instance instance = new Instance();
	private int literals;
	private int clauses;
	
	public FileLoader(int literals, int clauses) {
		this.literals = literals;
		this.clauses = clauses;
		this.filename = "src/Files/uf" + literals +"-01.cnf";
//		System.out.println(this.filename);
	}
	
	public FileLoader(int literals, int clauses, int filenumber) {
		this.literals = literals;
		this.clauses = clauses;
		this.filename = "src/Files/uf" + literals +"-0" + filenumber + ".cnf";
//		System.out.println(this.filename);
	}
	
	// Parse the file and create instance from the data
	public Instance loadInstance() {
		
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			boolean headerJustParsed = false;
			int clausesParsed = 0;
			Formula formula = new Formula();
			
			// Read till you reach EOF
			while ((strLine = br.readLine()) != null) {
				String[] lineData = strLine.split(" ");
				
				switch (lineData[0]) {
				case "c":
					break;
				case "p":
					parseHeader(lineData);
					headerJustParsed = true;
					instance.setWeights(generateWeights());
					break;
				case "0":
					break;
				case "%":
					break;
				case "":
					if (headerJustParsed) {
						lineData[0] = lineData[1];
						lineData[1] = lineData[2];
						lineData[2] = lineData[3];
						lineData[3] = "0";
						formula.addClause(parseData(lineData, instance.getWeights()));
						headerJustParsed = false;
						clausesParsed++;
					}
					break;
				default:
					formula.addClause(parseData(lineData, instance.getWeights()));
					clausesParsed++;
					if (clausesParsed == clauses) {
						// Add Formula to instance
						this.instance.setFormula(formula);
						
						// Close the input stream
						in.close();
						
						// Returns an instance
						return this.instance;
					}
					break;
				}
			}
			
			return null;
			
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	private void parseHeader(String[] lineData) {
		/*instance.setnVariables(Integer.parseInt(lineData[2]));
		instance.setnClauses(Integer.parseInt(lineData[4]));*/
		instance.setnVariables(literals);
		instance.setnClauses(clauses);
	}
	
	// TODO move to Instance?
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
