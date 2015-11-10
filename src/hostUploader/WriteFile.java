package hostUploader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

	File file;
	FileWriter fw;
	static BufferedWriter bw;
	
	public WriteFile() throws IOException{
		file = new File("hostname");
		fw = new FileWriter("hostname",false);
		bw = new BufferedWriter(fw);
	}
	
	public void write(String hostName) throws IOException{
		bw.write("HOSTNAME=\""+hostName+"\"");
		bw.close();
	}
	
	public File getFile(){
		return file;
	}
	
	public void close() throws IOException{
		bw.close();
		
	}
}
