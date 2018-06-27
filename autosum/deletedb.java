import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class deletedb extends HttpServlet
{
	PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6;
        public void init(ServletConfig config)
        {
                
        }
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();
		try
                {
			Connection c;
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        c=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:XE","system","manager");
		ps1=c.prepareStatement("delete from summ ");
		ps2=c.prepareStatement("delete from summ2 ");
		ps3=c.prepareStatement("delete from total ");
		ps4=c.prepareStatement("delete from sentence ");
		ps5=c.prepareStatement("delete from total ");
		ps6=c.prepareStatement("delete from summary ");
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
			
		try
		{
			String line ;	
			ps1.executeUpdate();
			ps2.executeUpdate();
			ps3.executeUpdate();
			ps4.executeUpdate();
			ps5.executeUpdate();
			ps6.executeUpdate();
			
						
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

