package camReport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;


public class Connect extends SwingWorker<Integer, Integer>{

	String num;
	String sOctet;//starting Octet
	String eOctet;//ending Octet
	String uName;//user name
	char[] pass;//password
	JProgressBar progressBar;
	JButton runBtn;
	static boolean ping;

	public Connect(String num,String sRange, String eRange, String uName, char[] pass, JProgressBar progressBar, JButton camReportBtn, boolean ping) {
		this.progressBar=progressBar;
		this.num=num;
		this.sOctet=sRange;
		this.eOctet=eRange;
		this.uName=uName;
		this.pass=pass;
		runBtn=camReportBtn;
	}

	static boolean ping(String num, int n){

		if(ping){		
			try {
				Process p = Runtime.getRuntime().exec("ping -n 1 " + num+"."+n);
				BufferedReader inputStream = new BufferedReader(
						new InputStreamReader(p.getInputStream()));

				String s = "";
				String out="";
				// reading output stream of the command
				while ((s = inputStream.readLine()) != null) {
					out+=s;
					if(out.contains("TTL=") ){ 
						return true;
					}
					else if(out.contains("timed out"))
						return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	protected void process(List<Integer> chunks) {
		for (int number : chunks)
			progressBar.setValue(number);
		
	}

	@Override
	protected Integer doInBackground() throws Exception {
		runBtn.setEnabled(false);
		CamReportWriter crw = new CamReportWriter();
		String[] outPut= new String[15];
		String Pass="";
		int n=0;
		while(n<pass.length){
			Pass+=pass[n];
			pass[n]=0;
			n++;
		}

		for(int i=(Integer.parseInt(sOctet));i<=(Integer.parseInt(eOctet));i++){
			publish(i);
			
			try {

				for(int j=outPut.length-1;j>=0;j--)
					outPut[j]="";

				outPut[0]= ""+num+"."+i;
				System.out.println(outPut[0]);
				if(ping(num,i)){
					System.setProperty("http.maxRedirects", "20");
					Authenticator.setDefault(new CustomAuthenticator(uName,Pass));
					URL url = new URL("http://"+num+"."+i+"/axis-cgi/view/param.cgi?camera=1&action=list");
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

					String line;

					while ((line = in.readLine()) != null) {

						String x = "";
						String[] info;
						if (line.endsWith("="))
						{
							info = new String [2];
							x =line.substring(0, line.length()-1);
							info[1] = "Not Set";
						}
						else
						{
							info = line.split("=");
							x =info[0];
						}
						switch (x) {
						case "root.Network.eth0.MACAddress":
							progressBar.setString(num+"."+i+": checking MacAddress");
							outPut[1]=info[1];
							break;

						case "root.Network.VolatileHostName.HostName":
							progressBar.setString(num+"."+i+": Checking HostName");
							outPut[2]=info[1];		    			
							break;

						case "root.Properties.System.SerialNumber":
							progressBar.setString(num+"."+i+": Checking SerialNumber");
							outPut[3]=info[1];		    			
							break;

						case "root.Properties.TemperatureSensor.Heater":
							progressBar.setString(num+"."+i+": Checking Heater Sensor");

							outPut[4]=info[1];
							break;

						case "root.Brand.ProdNbr":
							progressBar.setString(num+"."+i+": Checking Product Number");
							outPut[5]=info[1];
							break;

						case "root.Audio.A0.Enabled":
							progressBar.setString(num+"."+i+": Checking Audio");
							outPut[6]=info[1];
							break;

						case "root.Brand.ProdVariant":
							progressBar.setString(num+"."+i+": Checking Product variant");
							outPut[7]=info[1];
							break;

						case "root.Network.IPv6.Enabled":
							progressBar.setString(num+"."+i+": Checking IPv6");
							outPut[8]=info[1];
							break;

						case "root.SNMP.Trap.Enabled":
							progressBar.setString(num+"."+i+": Checking SNMP Trap");
							outPut[9]=info[1];
							break;

						case "root.SNMP.Trap.T0.Address":
							progressBar.setString(num+"."+i+": Checking SNMP Trap Address");
							outPut[10]=info[1];
							break;

						case "root.SNMP.Trap.T0.Community":
							progressBar.setString(num+"."+i+": Checking Trap T0 Community");
							outPut[11]=info[1];
							break;

						case "root.Time.SyncSource":
							outPut[12]=info[1];
							progressBar.setString(num+"."+i+": Checking Time Sync");
							if(outPut[12].contentEquals("NTP"))
								outPut[12]="Yes";
							else
								outPut[12]="No";
							break;

						case "root.Time.NTP.Server":
							progressBar.setString(num+"."+i+": Checking NTP Server");
							outPut[13]=info[1];
							break;

						case "root.ImageSource.I0.Name":
							progressBar.setString(num+"."+i+": Checking Name");
							outPut[14]=info[1];
							break;

						}
					}
					in.close();
				}
				crw.writeFile(outPut);
			}

			catch (MalformedURLException e) {
				progressBar.setString(num+"."+i+": Malformed URL");
				System.out.println("Malformed URL: " + e.getMessage());
			}
			catch (ProtocolException e) {
				progressBar.setString(num+"."+i+": Wrong Password");
				System.out.println("Wrong Password: " + e.getMessage());
				outPut[1]="Wrong Password";
				crw.writeFile(outPut);
			}
			catch (IOException e) {
				progressBar.setString(num+"."+i+": I/O Error");
				System.out.println("I/O Error: " + e.getMessage());
			}
			
		}
		progressBar.setString("Done!");
		JOptionPane.showMessageDialog(null, "Complete","Done", JOptionPane.INFORMATION_MESSAGE);
		runBtn.setEnabled(true);
		return Integer.parseInt(eOctet);
	}
}
