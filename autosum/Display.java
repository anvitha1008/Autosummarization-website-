import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class Display extends HttpServlet
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
		ps2=c.prepareStatement("select word,num from summ");
		ps1=c.prepareStatement("update summ set word=? where num=?");
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
			
		try
		{
			String name;
			CharSequence[] speacials = new CharSequence[31];
			speacials[0]="!";
			speacials[1]=",";
			speacials[2]="\"";
			speacials[3]=";";
			speacials[4]="=";
			speacials[5]="&";
			speacials[6]="/";
			speacials[7]="$";
			speacials[8]=":";
			speacials[9]="|";
			speacials[10]="%";
			speacials[11]="(";
			speacials[12]=")";
			speacials[13]="[";
			speacials[14]="]";
			speacials[15]="\"";
			speacials[16]="”";
			speacials[17]="“";
			speacials[18]="'";
			speacials[19]="-";
			speacials[20]="_";
			speacials[21]="#";
			speacials[22]="^";
			speacials[23]="*";
			speacials[24]="+";
			speacials[25]="{";
			speacials[26]="}";
			speacials[27]="`";
			speacials[28]=">";
			speacials[29]="<";
			speacials[30]=".";
			String str;
			int num,inde;		
			ps.executeUpdate();
			ResultSet rs = ps.getResultSet();
			out.println("<html> <head> <link rel=\"stylesheet\" type=\"text/css\" href=\"styleit.css\" />   </head> <body> <h3> <table border=\"2\">");
			out.println(" <form name=sentence action=\"/sw/filewrite\"> ");
			out.println("<div style=\"font-family: \'Oswald\', sans-serif;color: #707070;font-weight: lighter;\">");
			out.println("<th>Previous</th><th>SpecialWord</th><th>NewWord</th>");
			while(rs.next())
			{
				//out.println("[[]]]][]");
				str=rs.getString(1);
				num=rs.getInt(2);
				//out.println(str+"+==="+str.length());
				for(int i=0;i<=30;i++)
				{
					//out.println("%%%%%");
					if(str.contains(speacials[i]))
					{
					String sc=(String)speacials[i];
					  out.println("<tr><td>"+str+"</td><td>"+speacials[i]+"</td>");
					  if(str.endsWith(sc)) 
					  {
					    inde=str.indexOf(sc);
					   // out.println("<td>"+inde+"</td>");
					    str=str.substring(0,inde);
					    
					    ps1.setString(1,str);
					    ps1.setInt(2,num);
					    ps1.executeUpdate(); 
					  }
					  else if(str.startsWith(sc))
					  {
					    inde=str.length();
					    //out.println("<td>"+inde+"</td>");
					    str=str.substring(1,inde);
					   // num=rs.getInt(2);
					    ps1.setString(1,str);
					    ps1.setInt(2,num);
					    ps1.executeUpdate();
					  }
					  out.println("<td>"+str+"</td>");
					  out.println("</tr>");
					}
				}
			}
			out.println("</div>");
			out.println("<input type=\"submit\"  value=\"Stemming\" class=\"button\"> </td>");
			out.println("</table> </form> </h3> </body> </html>");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

