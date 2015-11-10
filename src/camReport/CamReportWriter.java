package camReport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CamReportWriter {
	File file;
	FileWriter fw;
	static BufferedWriter bw;
	
	public CamReportWriter() throws IOException{
		
		file = new File("CamReport.csv");
		if(file.exists()){
		fw = new FileWriter("CamReport.csv",false); System.out.println("exists");}
		bw = new BufferedWriter(fw);
		bw.write("IP Address,Mac Address,Host Name,Serial,Heater Switch,Camera Model,Audio,Lens,IPv6,"
				+ "SNMP Trap Enabled,SNMP Trap Address,SNMP Trap Community,NTP,NTP Server, Camera Description");
		bw.newLine();
		bw.close();
	}
	
	public void writeFile(String[] content) throws IOException{
		bw=new BufferedWriter(new FileWriter("CamReport.csv", true));
		bw.write(content[0]+ ","+content[1]+ ","+content[2]+ ","+content[3] + ","+content[4]+","+content[5]+","+
				content[6]+","+content[7]+","+content[8]+","+content[9]+","+content[10]+","+content[11]+","+content[12]+","+
				content[13]+","+content[14]);
		bw.newLine(); 
		bw.close();
	}
}
