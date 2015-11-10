package camReport;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

	public class CustomAuthenticator extends Authenticator {
		
		String username;
		String password;
		
		
		public CustomAuthenticator(String uName, String pass) {
			username = uName;
			password = pass;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			// Return the information (a data holder that is used by Authenticator)
			return new PasswordAuthentication(username, password.toCharArray());
		}
	}