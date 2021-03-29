package com.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DatabaseDao.DatabaseHelper;
import com.Model.DisplayAllData;

/**
 * Servlet implementation class MailTofarmController
 */
@WebServlet("/MailTofarmController")
public class MailTofarmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailTofarmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Farmid=request.getParameter("farmid");
		String mobno=request.getParameter("mobno");
		DatabaseHelper db = new DatabaseHelper();
		String email=db.getMailIdByFarmid(Farmid);
		String str="no";
		
		if(email.equals("no")){
			
			response.sendRedirect("WrongFarmerId.jsp");
		}
		else{
			final String username = "shubhamiit91@gmail.com";
	        final String password1 = "otaqnkdmsvoilfmq";  
	
	        Properties prop = new Properties();
	 		prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	         
	        Session session = Session.getInstance(prop,new javax.mail.Authenticator()
	        {
	             protected PasswordAuthentication getPasswordAuthentication()
	             {
	                 return new PasswordAuthentication(username, password1);
	             }
	        });
	
	         try 
	         {
	             Message message = new MimeMessage(session);
	             message.setFrom(new InternetAddress(username));
	             message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
	             message.setSubject("Mail By Dealer");
	             message.setText("Hello, dear farmer. Please checkout the following details");
	             message.setText("Contact on this mobile no"+mobno+"dealer wants to buy your crop ");
	
	             Transport.send(message);
	
	             System.out.println("Mail send..!");
	
	         } 
	         catch (MessagingException e) 
	         {
	             e.printStackTrace();
	         }
	         response.sendRedirect("Dealer.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
