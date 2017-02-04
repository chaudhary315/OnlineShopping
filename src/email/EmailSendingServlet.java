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


@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
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
		String mobile = request.getParameter("mobile");
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
			String sql = "select * from userd where email='" +email+ "' or mobile='" + mobile+ "'";
		   Statement stmt = con.createStatement();
		   ResultSet rs=  stmt.executeQuery(sql);
		   
		  
		   
		   while(rs.next())
		   {
			   String message ="Oop's Already registrer ";
			   String message1 ="Enter New Email Address";
			   String message2 ="Enter New Mobile Number";
			   
			   HttpSession session = request.getSession();
			   session.setAttribute("emailmessage",message);
			   session.setAttribute("emailmessage1",message1);
			   session.setAttribute("emailmessage2",message2);
			   response.sendRedirect("SellerWizard/Home.jsp");
		
		   }
		   
		   
		   
		   
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		session.setAttribute("mobile", mobile);
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
			
		   
		
		
		
		