import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class pos1 extends HttpServlet
{
	PreparedStatement ps;
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
		ps=c.prepareStatement("select line from sentence order by linenum ");
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
			
		try
		{
			String line ;
			out.println("1");		
			ps.executeUpdate();
			ResultSet rs = ps.getResultSet();
			out.println("2");
			//out.println("<html><body> <h3>");
			//out.println(" <form name=sentence action=\"/new/Display\"> ");
			//out.println("<div style=\"font-family: \'Oswald\', sans-serif;color: #707070;font-weight: lighter;\">");
			while(rs.next())
			{
				line=rs.getString(1);
				//out.println("3");
				//ps1.setInt(1,linenum);
				//out.println("-3-");
				//ps1.executeUpdate();
				//out.println("33");
				//ResultSet rs1 = ps1.getResultSet();
				//out.println("333");
				//out.println("4");
				out.println(line);
				//out.println(linenum);
				//ps2.setString(1,line);
				//out.println("41");
				//ps2.setInt(2,linenum);
				//out.println("42");
				//ps2.executeUpdate();	
				//out.println("5");
				//line="";
			}
			//out.println("<br>");
			//out.println(" </div>");
			//out.println("<input type=\"submit\"  value=\"Special Words\"> </td>");
			//out.println("</p> </form> </h3> </body> </html>");			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

