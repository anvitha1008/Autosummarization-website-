
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
public class testdb extends HttpServlet
{
  	PreparedStatement ps;
  	Connection c;
  	public void init(ServletConfig config)
	 {
  
 		
	 }
	  public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  	  {
		res.setContentType("text/html");
   		 PrintWriter out=res.getWriter();
		HttpSession session=req.getSession();
		   try
		  { 
		   try
   		   {
    		    Class.forName("oracle.jdbc.driver.OracleDriver");
    		    c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
  		    ps=c.prepareStatement("INSERT INTO TEST (WORD) VALUES(?)");
			out.println("1");
		
  		  }
		  catch(Exception e)
  		  {
      		   e.printStackTrace();
  		  }
	 	
		ps.setString(1,"abc");
	 	ps.executeUpdate();
        	             }
		
		
	 catch(Exception e)
	 {
	        	e.printStackTrace();
	 }	

     }
}






















  	