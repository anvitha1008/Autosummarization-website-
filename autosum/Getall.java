import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class Getall extends HttpServlet
{
	PreparedStatement ps,ps1,ps2;
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
		ps=c.prepareStatement("select word,num,linenum,paranum from summ order by num");
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
			
		try
		{
			String name;	
			String str;
			int num,inde,p,l;		
			ps.executeUpdate();
			ResultSet rs = ps.getResultSet();
			out.println("<html><body><table border=\"2\">");
			while(rs.next())
			{
				str=rs.getString(1);
				num=rs.getInt(2);
				l=rs.getInt(3);
				p=rs.getInt(4);
				out.println("<tr><td>"+num+"</td>");
				out.println("<td>"+str+"</td>");
				out.println("<td>"+str.length()+"</td>"); 
				out.println("<td>"+p+"</td>");
				out.println("<td>"+l+"</td></tr>");
			}
			out.println("</table></body></html>");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

