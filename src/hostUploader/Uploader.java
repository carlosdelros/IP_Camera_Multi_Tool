package hostUploader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import org.apache.commons.net.ftp.*;

import camReport.CamReportWriter;



public class Uploader extends SwingWorker<Integer, Integer>{


	String num;
	String sOctet;//starting Octet
	String eOctet;//ending Octet
	String uName;//user name
	char[] pass;//password
	JProgressBar progressBar;
	JButton runBtn;
	static boolean ping;

	static void ftp(String ip, String uname, String pass, WriteFile file) throws IOException{
		FTPClient con = new FTPClient();
		int reply;
		System.out.println(ip);
		try{
			con.connect(ip);
		}catch(ConnectException e){
			reply=con.getReplyCode();
			showServerReply(con);

			if(!FTPReply.isPositiveCompletion(reply)) {
				con.disconnect();
				System.err.println("Connection Timed Out");
				return;
			}

		}
		con.login(uname, pass);
		con.changeWorkingDirectory("../etc/conf.d");
		showServerReply(con);
		con.pwd();
		showServerReply(con);
		con.enterLocalPassiveMode();
		con.setFileType(FTP.BINARY_FILE_TYPE);
		String remoteFile = "hostname";
		InputStream inputStream = new FileInputStream(file.getFile());
		showServerReply(con);
		boolean done = con.storeFile(remoteFile, inputStream);
		inputStream.close();
		if (done) {
			System.out.println("The file was uploaded successfully.");
		}

		con.sendCommand(FTPCommand.SITE,"reboot");
		con.sendCommand(FTPCommand.SITE,"reboot");
		con.disconnect();

	}

	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				System.out.println("SERVER: " + aReply);
			}
		}
	}

	public static String hostName(String rack, String camName ){
		return rack+"-"+camName;
	}



	public Uploader(String path,String userName, char[] password,JButton runBtn) throws IOException{
		ReadFile rf= new ReadFile(path);
		String rack=rf.read();
		String uname=userName;
		char[] pass=password;
		String newLine=rf.read();
		String camInfo[]= new String[3];
		while(newLine!=null){
			WriteFile wf= new WriteFile();
			if(newLine.length()<2){
				System.out.println("break");
				break;
			}

			camInfo=newLine.split(",");
			//validateMac();
			wf.write(hostName(rack, camInfo[2]));
			ftp(camInfo[0],uname,pass,wf);
			newLine=rf.read();

		}
		rf.close();

	}

	@Override
	protected Integer doInBackground() throws Exception {
		runBtn.setEnabled(false);
		String[] outPut= new String[15];
		String Pass="";
		int n=0;
		while(n<pass.length){
			Pass+=pass[n];
			pass[n]=0;
			n++;
		}
		
		
		return 0;
	}
}
