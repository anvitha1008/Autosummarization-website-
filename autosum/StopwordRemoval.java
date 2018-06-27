import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class StopwordRemoval extends HttpServlet
{
	PreparedStatement ps,ps1,ps2;
	Connection c;
        public void init(ServletConfig config)
        {
                
        }
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();
		try
                {
			
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        c=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:XE","system","manager");
		ps=c.prepareStatement("select word,num from summ order by num");
		ps2=c.prepareStatement("delete from summ where word=? ");
		ps1=c.prepareStatement("select distinct(word) from stop");
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
			
		try
		{
			String stop[]=new String[713];
			int n1=0;		
			ps1.executeUpdate();
			ResultSet rs1 = ps1.getResultSet();
			out.println("<html> <head> <link rel=\"stylesheet\" type=\"text/css\" href=\"styleit.css\" />   </head> <body> <h3>");
			out.println(" <form name=StopWR  action=\"/new/statscore\"> ");
			while(rs1.next())
			{
				stop[n1++]=rs1.getString(1);
			}
			//for(int i=0;i<344;i++)
			//{
				//out.println(stop[i]);
			//}
			

			String str,retVal;
			ps.executeUpdate();
			ResultSet rs = ps.getResultSet();
			while(rs.next())
			{
				str=rs.getString(1);
				for(int j=0;j<344;j++)
				{
					if(str.equals(stop[j]))
					{
						//out.println(str);
						ps2.setString(1,str);
						ps2.executeUpdate();
					}
				}
				
			}
			out.println("<input type=\"submit\"  value=\"Scoring\" class=\"button\"> </td>");
			out.println("</form> </h3> </body> </html>");			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

