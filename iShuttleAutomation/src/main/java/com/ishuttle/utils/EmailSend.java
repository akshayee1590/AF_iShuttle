package com.ishuttle.utils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.Test;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.mail.BodyPart;



public class EmailSend {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DSN_URL = "jdbc:mysql://localhost/QA";
	
	static final String USER = "root";
	static final String PASSWORD = "";
	
	String pass;
	
	private static int createNewBatchId() 
	{
		DateFormat df = new SimpleDateFormat("yyMMddHHmm");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		System.out.println("New Batch " + reportDate);
		return Integer.parseInt(reportDate);
	}

	private static java.sql.Timestamp getCurrentTimeStamp() 
	{
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime()); 
	}
	
	@Test
	public void SendMail() throws Exception 
	{
		// Recipient's email ID needs to be mentioned. ranjit.patil@icastx.com
		String to = "akshayee.sawant@gmail.com,madhuri.sangle@icastx.com,ranjit.patil@icastx.com";

		// Sender's email ID needs to be mentioned
		String from = "akshayee.sawant@icastx.com";
		Connection conn = null;
		Statement stmt = null;
		int batchNo = createNewBatchId();
		try 
		{
			// load the driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
		      
			// get the connection from the driver
			conn = DriverManager.getConnection(DSN_URL,USER,PASSWORD);
			stmt = conn.createStatement();
			String name="Akshayee";
			String sql = ("SELECT password FROM login_credentials where name='Akshayee'");
		      
			// run the query
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
		      
			// Iterate through the data in the result set and display it. 
			while (rs.next()) 
			{
				//Print one row          
				for(int i = 1 ; i <= columnsNumber; i++)
				{
					pass=rs.getString(i);
				}
	
				System.out.println();//Move to the next line to print the next row.           

			}
		    
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
		
		final String username = "akshayee.sawant@icastx.com";
		final String password = pass;
		
		// Assuming you are sending email through relay.jangosmtp.net smtp.gmail.com
		String host = "smtp.rediffmailpro.com";
  
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

      	// Get the Session object.
		Session session = Session.getInstance(props,
        new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication() 
			{
               return new PasswordAuthentication(username, password);
            }
         });

      try 
      {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Testing Report");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("Please find emailable report attached.");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "/home/akshayee/Applications/AutomationFramework/iShuttleAutomation/test-output/extentreport.html";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");
  
      } 
      catch (MessagingException e) 
      {
         throw new RuntimeException(e);
      }
   }
}