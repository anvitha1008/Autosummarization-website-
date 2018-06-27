import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class Summary extends HttpServlet
{
	PreparedStatement ps,ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9,ps10,ps11,ps12,ps13;
	Connection c;
        public void init(ServletConfig config)
        {
                
        }
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
			HttpSession session=req.getSession();
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();
			String per=req.getParameter("pert");
			//out.println("ppp"+per);
			int p=Integer.parseInt(per);
			//int p=30;
			
		try
                {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        c=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:XE","system","manager");
		//ps=c.prepareStatement("insert into total(tscore,linenum)  select sum(score),linenum from stat group by linenum");
		ps1=c.prepareStatement("select linenum from total order by tscore");
		ps4=c.prepareStatement("insert into summary(linenum) values(?)");
		ps2=c.prepareStatement("select line from sentence where linenum=?");
		ps5=c.prepareStatement("select linenum from summary order by linenum");
		ps3=c.prepareStatement("select max(linenum) from summ");
		ps6=c.prepareStatement("delete from summ");
		ps7=c.prepareStatement("delete from summ2");
		ps8=c.prepareStatement("delete from total");
		ps9=c.prepareStatement("delete from sentence");
		ps10=c.prepareStatement("delete from total");
		ps11=c.prepareStatement("delete from summary");
		ps12=c.prepareStatement("delete from stat");
		ps13=c.prepareStatement("delete from stem");
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
			
		try
		{
			//out.println("1");
			//ps.executeUpdate();
			String lines;
			int linenum,maxline=0,tlines,lr,countl=0,cc=0;
			ps3.executeUpdate();
			ResultSet rs3 = ps3.getResultSet();
			out.println("<html> <head> <link rel=\"stylesheet\" type=\"text/css\" href=\"styleit.css\" />   </head> <body> <h3> ");
			//out.println("<div style=\"font-family: \'Oswald\', sans-serif;color: #707070;font-weight: lighter;\">");
			while(rs3.next())
			maxline=rs3.getInt(1);
			tlines=Math.round((maxline*p)/100);
			lr=maxline-tlines;
			lr++;
			out.println("lr is: "+lr+" maxline: "+maxline+" tlines: "+tlines);
			out.println(" </h3> <br> <h2>Summary</h2> <h3><br>");
			ps1.executeUpdate();
			ResultSet rs1 = ps1.getResultSet();
			while(rs1.next())
			{
				linenum=rs1.getInt(1);
				countl++;
				if(countl>=lr)
				{
					ps4.setInt(1,linenum);
					ps4.executeUpdate();
				}
			}
			ps5.executeUpdate();
			ResultSet rs5 = ps5.getResultSet();
			while(rs5.next())
			{
					int lnum=rs5.getInt(1);
					ps2.setInt(1,lnum);
					ps2.executeUpdate();
					ResultSet rs2 = ps2.getResultSet();
					if(rs2.next())
					{
					
						out.println(rs2.getString(1));
						//out.println("<br>");
						//out.println(lnum);
						
					}
				
			}
			ps6.executeUpdate();
			ps7.executeUpdate();
			ps8.executeUpdate();
			ps9.executeUpdate();
			ps10.executeUpdate();
			ps11.executeUpdate();
			ps12.executeUpdate();
			ps13.executeUpdate();
			out.println("</h3> </body> </html>");
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

