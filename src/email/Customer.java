package email;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Customer")
public class Customer extends HttpServlet {
	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// reads form fields
		
		
		
		String email = request.getParameter("email");
		String subject="Verification code";
		double p=Math.random();
		String p1=Double.toString(p);
		String passs=p1.substring(2,8);
		String pass1="Pick My Kart OTP is- ";
		String OTP=pass1.concat(passs);
		
		   
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
			String sql = "select * from customer where email='" +email+ "' ";
		   Statement stmt = con.createStatement();
		   ResultSet rs=  stmt.executeQuery(sql);
		   
		  
		   
		   while(rs.next())
		   {
			   String message ="Oop's Already registrer ";
			   
			   HttpSession session = request.getSession();
			   session.setAttribute("email1",message);
			   response.sendRedirect("Customer/Csignup.jsp");
		
		   }
		   
		   
		   
		   
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		//session.setAttribute("mobile", mobile);
		session.setAttribute("open", passs);
		
		      
			   EmailUtility.sendEmail(host,port,user,pass,email,subject,OTP);
			   response.sendRedirect("SellerWizard/OTP.jsp");
			  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
			
		   
		
		
		
		/*
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
			String sql = "select * from CUSTOMER where email='" +email+ "' ";
		   Statement stmt = con.createStatement();
		   ResultSet rs=  stmt.executeQuery(sql);
		   
		  
		   
		   while(rs.next())
		   {
			   String message ="Oop's Already Register Please Login";
			   
			   HttpSession se = request.getSession();
			   se.setAttribute("message1",message);
			   response.sendRedirect("Customer/Csignup.jsp");
		
		   }
		   
		   
		   
		   
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		session.setAttribute("open", passs);
		
		      
			   EmailUtility.sendEmail(host,port,user,pass,email,subject,OTP);
			   response.sendRedirect("Customer/OTPCsignup.jsp");
			  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
			
		   
		
		*/
		
		