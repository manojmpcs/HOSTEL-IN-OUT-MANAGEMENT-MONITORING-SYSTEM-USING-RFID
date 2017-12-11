

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	String rollno=req.getParameter("rollno");
		String name=req.getParameter("name");
		String branch=req.getParameter("branch");
		String year=(req.getParameter("year"));
		String sem=(req.getParameter("sem"));
		String dob=req.getParameter("dob");
		String mno=(req.getParameter("mno"));
		String fname=req.getParameter("fname");
		String fmno=(req.getParameter("fmno"));
		String email=req.getParameter("email");
		String aemail=req.getParameter("aemail");
		String hostel=req.getParameter("hostel");
		String hroomno=req.getParameter("hroomno");
		String address=req.getParameter("address");
	   String a="7018427394";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/major project","root","");
			System.out.print("dfdfdhvfhd");
			PreparedStatement pst= con.prepareStatement("insert into registartion values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		System.out.println("jdhjfdhfjd");
			pst.setString(1, rollno);	
		pst.setString(2, name);	
		pst.setString(3, branch);	
		pst.setString(4, year);
		pst.setString(5, sem);
		pst.setString(6, dob);	
		pst.setString(7, mno);
		pst.setString(8, fname);	
		pst.setString(9,fmno);
		pst.setString(10,email);	
		pst.setString(11,aemail);	
		pst.setString(12,hostel);	
		pst.setString(13,hroomno);	
		pst.setString(14,address);	
	   pst.setString(15,a);
	   pst.executeUpdate();
	   res.sendRedirect("Registered Successfully");
		}
		catch(Exception e)
		{
		System.out.println(e);	
		}
	}

	
}
