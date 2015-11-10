package hostUploader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ReadFile {

	FileReader fileReader;
	BufferedReader bufferedReader;
	
	public ReadFile(String path){

		try{
			bufferedReader = new BufferedReader(new FileReader(new File(path)));
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "No valid File was selected or file format is incorrect. Please Try again");
		}

	}
	
	public String read() throws IOException{
		String line="";
		
		line=bufferedReader.readLine();
		
		return line;
	}
	
	public void close() throws IOException{
		bufferedReader.close();
	}
}
