package sat.filehandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import sat.solver.Instance;

public class ResultWriter {
	
	private String filename = "src/Files/uf20-0100.cnf";
	
	// Parse the file and create instance from the data
	public void writeResultFile(Instance instance, String result) {
		
		try {
			File newFile = new File(filename.replace("cnf", "txt"));
			
			// if file doesnt exists, then create it
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String strLine;
			
			// Read till you reach EOF
			while ((strLine = br.readLine()) != null) {
				String[] lineData = strLine.split(" ");
				
				switch (lineData[0]) {
				case "c":
					bw.write(strLine + "\n");
					break;
				case "p":
					bw.write(strLine + "\n");
					writeWeights(bw, instance.getWeights());
					bw.write("s " + result + "\n");
					break;
				default:
					bw.write(strLine + "\n");
					break;
				}
			}
			
			bw.close();
			
			// Close the input stream
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeWeights(BufferedWriter bw, ArrayList<Integer> weights) throws IOException {
		bw.write("w ");
		for (Integer weight : weights) {
			bw.write(Integer.toString(weight) + " ");
		}
		bw.write("0\n");
	}

}
