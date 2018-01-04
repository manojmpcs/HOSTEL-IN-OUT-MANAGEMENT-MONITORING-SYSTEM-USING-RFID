
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.time.LocalDate;
import com.mysql.jdbc.Statement;


public class Rfid_Out extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try
		{
			boolean a=true;
			while(a)
			{	
			LocalTime time=LocalTime.now();
		    int h=time.getHour();
		    int m=time.getMinute();
		    int s=time.getSecond();
		    int ms=time.getNano();
		if(h==14&&m==37&&s==50&&ms==00)
		{		
      yes1();
      yes2();
  	String str,str1;
  	Connection con=null;
  	BufferedWriter br=null;
  	FileWriter fr=null;
  	BufferedWriter br1=null;
  	FileWriter fr1=null;
  	BufferedWriter br2=null;
  	FileWriter fr2=null;
  	try
  	{
  	int k=0,i=0;
  	Class.forName("com.mysql.jdbc.Driver");
   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/major project","root","");
  java.sql.Statement st=con.createStatement();		
  ResultSet r=st.executeQuery("SELECT * from rfid");
  res.setContentType("text/html");
    PrintWriter out = res.getWriter();
   /* out.println("OUT ENTRIES");
    out.println();
    out.println();
    out.println();
    out.println();*/
  LocalDate date= LocalDate.now();
  fr=new FileWriter("D:\\processing-3.3.6\\"+date+"  IN OUT ENTRIES"+".txt");
  br=new BufferedWriter(fr);
  fr1=new FileWriter("D:\\processing-3.3.6\\"+date+"Late Comers"+".txt");
  br1=new BufferedWriter(fr1);
  fr2=new FileWriter("D:\\processing-3.3.6\\read1.txt");
  br2=new BufferedWriter(fr2);
   br.write("ROLL NO"+"                     "+"OUT Frequency"+"                    "+"IN Frequency"+"\r\n");
   br.write("\r\n");
   br.write("\r\n");
 while(r.next())
 {
 String s3=r.getString("rollno");
 int a1=r.getInt("out1");
 int a2=r.getInt("in1");
 if(a2<a1)
 {	 
 br1.write(s3+",");	
 br2.write(s3+",");
 }
 else if(a2>a1)
 {
System.out.println("Wrong Entries");	 
 }
 br.write(s3+"                       "+a1+"                                "+a2+"\r\n");
 br.write("\r\n");
 }
 
  	/* res.setContentType("text/html");
  	    PrintWriter out = res.getWriter();

  	    out.println("<html>");
  	    out.println("<head>");
  	    out.println("<title>Todays's Hostel In Out Entries</title>");
  	    out.println("</head>");
  	    out.println("<body bgcolor=\"white\">");
  	    out.println("</body>");
  	    out.println("</html>");*/
  	}
  	catch(Exception e)
  	{
  	e.printStackTrace();	
  	}
  	finally
  	{
  		br.close();
  		br1.close();
  		br2.close();
  		con.close();	
  	}
  	 yes4();
		}
			}
		}
			catch(Exception e)
			{
			System.out.println(e);	
			}
		}
	
	
	
	
	
	
	void yes1()throws Exception
	{
		String str,str1;
		Connection con=null;
		FileReader fr;
		BufferedReader br=null;
		try
		{
		int k=0;
		Class.forName("com.mysql.jdbc.Driver");
	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/major project","root","");
	java.sql.Statement st=con.createStatement();
    fr=new FileReader("D:\\processing-3.3.6\\RFID1.txt");
    br=new BufferedReader(fr);
    while((str=br.readLine())!=null)
    {
    String jio[]=str.split(",");
    for(int i=0;i<jio.length;i++)
    {	
    String s1=jio[i];	
    ResultSet r=st.executeQuery("SELECT out1 from rfid where rollno="+s1);
    if(r.next())
    {	
    int a=r.getInt("out1");	
    a++;	
    String s="update rfid set out1='"+a+"' where rollno='"+s1+"'";
    int y=st.executeUpdate(s);
    System.out.println(y+"Updated Successfully");
    }
    else
    {	
    String s="insert into rfid values('"+s1+"','"+"1"+"','"+"0"+"')";
		int y=st.executeUpdate(s);
		System.out.println(y+"inserted");		
    }
    }
    }
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		finally
		{
			  br.close();
			    con.close();
		}
	}
	
	
	
	
	
	
void yes2()throws Exception
{
	String str,str1;
	Connection con=null;
	FileReader fr1=null;
	BufferedReader br1=null;
	try
	{
	int k=0;
	Class.forName("com.mysql.jdbc.Driver");
 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/major project","root","");
java.sql.Statement st=con.createStatement();
 fr1=new FileReader("D:\\processing-3.3.6\\RFID2.txt");
 br1=new BufferedReader(fr1);
while((str=br1.readLine())!=null)
{	
String jio[]=str.split(",");
for(int i=0;i<jio.length;i++)
{	
String s1=jio[i];		
ResultSet r=st.executeQuery("SELECT out1,in1 from rfid where rollno="+s1);
if(r.next())
{	
int a=r.getInt("out1");	
int a1=r.getInt("in1");
a1++;
if(a1<=a)
{	
String s="update rfid set in1='"+a+"' where rollno='"+s1+"'";
int y=st.executeUpdate(s);
System.out.println(y+"Updated Successfully");
}
}
}
}
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	finally
	{
		br1.close();
		con.close();	
	}
}

void yes4()throws Exception
{
	String str,str1;
	Connection con=null;
	FileReader fr;
	BufferedReader br=null;
	BufferedWriter br1=null;
  	FileWriter fr1=null;
	try
	{
	int k=0;
	Class.forName("com.mysql.jdbc.Driver");
 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/major project","root","");
java.sql.Statement st=con.createStatement();
LocalDate date= LocalDate.now();
fr=new FileReader("D:\\processing-3.3.6\\read1.txt");
br=new BufferedReader(fr);
fr1=new FileWriter("D:\\processing-3.3.6\\send_data.txt");
br1=new BufferedWriter(fr1);
while((str=br.readLine())!=null)
{
String jio[]=str.split(",");
System.out.println("manoj");
for(int i=0;i<jio.length;i++)
{	
String s1=jio[i];
System.out.println("manoj");
ResultSet r=st.executeQuery("SELECT fmno from registartion where rollno="+s1);
if(r.next())
{	
String e1=r.getString("fmno");
System.out.println(e1);
br1.write(e1+",");
}
}
}
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	finally
	{
		  br.close();
		  br1.close();
		    con.close();
	}
}
}


