package com.Controller;

import java.io.IOException;
import java.util.ArrayList;
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

import org.apache.tomcat.util.codec.binary.Base64;

import com.DatabaseDao.DatabaseHelper;
import com.Model.CreateAccount;
import com.Model.LoginAccount;


/**
 * Servlet implementation class CreateAccountController
 */
@WebServlet("/CreateAccountController")
public class CreateAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count1=0, count2=0;
		String Firstname=request.getParameter("fname");
		String Lastname=request.getParameter("lname");
		String Gender=request.getParameter("gender");
		String DateOfBirth=request.getParameter("dob");
		String City=request.getParameter("city");
		String State=request.getParameter("state");
		String Nationality=request.getParameter("nationality");
		long Mobileno=Long.parseLong(request.getParameter("mono"));
		String EmailId=request.getParameter("email");
		String catofuser=request.getParameter("catofuser");
		String Username=request.getParameter("username");
		DatabaseHelper DbObj=new DatabaseHelper();
		
		int pass=DbObj.generatePassword();
		String password=Integer.toString(pass);
		byte[] encode=Base64.encodeBase64(password.getBytes());
		String pass1=new String(encode);
		
		CreateAccount createAccountObj=new CreateAccount();
		
		createAccountObj.setFirstName(Firstname);
		createAccountObj.setLastName(Lastname);
		createAccountObj.setGender(Gender);
		createAccountObj.setDateOfBirth(DateOfBirth);
		createAccountObj.setState(State);
		createAccountObj.setCity(City);
		createAccountObj.setNationality(Nationality);
		createAccountObj.setMobileNo(Mobileno);
		createAccountObj.setEmailId(EmailId);
		createAccountObj.setUserId(Username);
		createAccountObj.setCatofuser(catofuser);
		
		LoginAccount log=new LoginAccount();
		log.setUname(Username);
		log.setPass(pass1);
		List<LoginAccount> logacc=new ArrayList<LoginAccount>();
		logacc.add(log);
		
		List<CreateAccount> Accountlist=new ArrayList<CreateAccount>();
		Accountlist.add(createAccountObj);
		
		if(catofuser.equals("Farmer") ){
			long farmid=0;
			farmid=DbObj.createAccountFarm(Accountlist);
			
			if(farmid > 0){
				count1=DbObj.createLoginAccountFarm(logacc, farmid);
			}
			
			
		}
		else{
			long  dealid=DbObj.createAccountDealer(Accountlist);
			
			if(dealid > 0){
				count2=DbObj.createLoginAccountDeal(logacc, dealid);
			}
			
		}
		
		
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
             message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(EmailId));
             message.setSubject("Mail from Farmer's produce selling portal");
             message.setText("Hello, dear user...Your account is created. Please checkout the following details");
             message.setText("Your account is created. Please checkout the following details .Account    Password : "+pass+"     Username : "+Username);

             Transport.send(message);

             System.out.println("Mail send..!");

         } 
         catch (MessagingException e) 
         {
             e.printStackTrace();
         }
         if((count1 >0 )||( count2>0))
			{
				response.sendRedirect("registermsg.jsp");
			}
			else
			{
				response.sendRedirect("CreateAccount.jsp");
			}
         
	}

	


}
