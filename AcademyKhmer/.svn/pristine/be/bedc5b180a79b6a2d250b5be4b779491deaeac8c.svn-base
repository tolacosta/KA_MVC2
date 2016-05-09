package ultilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMailSSL {
	
//	final String username = "koreasoftwarehrdcenter@gmail.com";
	private String username = "autosend.kshrd@yahoo.com";
	private String password = "this is my password";
	
	public SendMailSSL(String infoFilePath) throws Exception{
		String sCurrentLine;
		String arr[] = new String[2];
		int i=0;
		BufferedReader br = new BufferedReader(new FileReader(infoFilePath));
		while ((sCurrentLine = br.readLine()) != null) {
			arr[i] = sCurrentLine;
			i++;
		}
	//	username = Secret.decrypt(arr[0]);
	//	password = Secret.decrypt(arr[1]);
		if (br != null)br.close();
	}
	
	public SendMailSSL(){super();}
	
	public void send(String rec, String subject, String msg) {
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
		
		Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.mail.yahoo.com");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.stmp.user", username);
	    props.put("mail.smtp.password", password);
 
		Session session = Session.getDefaultInstance(props,	//getinstance
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});

		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(rec));
			message.setSubject(subject);
			message.setContent(msg, "text/html");
 
			Transport.send(message);
 
			System.out.println("Done SSL");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public boolean sendBCC(String rec, String subject, String messageBody) {
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
		System.out.println(username+" "+password);
		Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.mail.yahoo.com");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.stmp.user", username);
	    props.put("mail.smtp.password", password);
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.addRecipients(Message.RecipientType.BCC,
					InternetAddress.parse(rec));
			System.out.println("REC"+rec);
			message.setSubject(subject);
			message.setContent(messageBody, "text/html");
 
			Transport.send(message);
 
			return true;
 
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		new SendMailSSL().sendBCC("picponreayly@gmail.com", "from laptop", "LAPTOP");
	}
	
}