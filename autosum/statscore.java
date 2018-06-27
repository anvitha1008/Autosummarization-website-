import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class statscore extends HttpServlet
{
	PreparedStatement ps,ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8;
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
		ps=c.prepareStatement("select word,num,linenum from summ order by num");
		ps1=c.prepareStatement("select count(word) from summ where linenum=?");
		ps2=c.prepareStatement("select count(word) from summ where word=? and linenum!=?");
		ps3=c.prepareStatement("insert into stat (num,word,linenum,score) values(?,?,?,?)");
		ps4=c.prepareStatement("insert into total(tscore,linenum)  select sum(score),linenum from stat group by linenum");
		ps5=c.prepareStatement("select postag,linenum from sentence order by linenum");
		ps6=c.prepareStatement("select tscore from total where linenum=?");
		ps7=c.prepareStatement("update total set tscore=? where linenum=?");
		ps8=c.prepareStatement("select min(linenum),paranum from summ group by paranum");
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
			
		try
		{
			String word,xxx;	
			int num=0,linenum=0,countdoc=0,score=0;
			int countline=0;
			ps.executeUpdate();
			ResultSet rs = ps.getResultSet();
			//out.println("1");
			out.println("<html> <head> <link rel=\"stylesheet\" type=\"text/css\" href=\"styleit.css\" />   </head> <body> </h3>");
			out.println(" <form name=score action=\"/new/summa.html\"> ");
			//out.println("<div style=\"font-family: \'Oswald\', sans-serif;color: #707070;font-weight: lighter;\">");
			out.println("<input type=\"submit\"  value=\"Summary\" class=\"button\"> </td>");
			out.println(" <table border=\"2\"><th>Word</th><th>Score</th>");
			while(rs.next())
			{
				//out.println("2");
				word=rs.getString(1);
				num=rs.getInt(2);
				linenum=rs.getInt(3);
				ps1.setInt(1,linenum);
				ps1.executeUpdate();
				ResultSet rs1 = ps1.getResultSet();
				//out.println("etrt");
				while(rs1.next())
					countline=rs1.getInt(1);
				//out.println("countline "+countline);
				if(countline>2)
					score++;
				ps2.setString(1,word);
				ps2.setInt(2,linenum);
				ps2.executeUpdate();
				//out.println("4");
				ResultSet rs2 = ps2.getResultSet();
				while(rs2.next())
					countdoc=rs2.getInt(1);
				//out.println("countdoc  "+countdoc);
				score=score+countdoc;
				ps3.setInt(1,num);
				ps3.setString(2,word);
				ps3.setInt(3,linenum);
				ps3.setInt(4,score);
				//out.println("6");
				ps3.executeUpdate();
				out.println("<h4> <tr> <td>"+word+" </td><td>"+score+" </td></tr></h4> ");
				score=0;
				countline=0;
				countdoc=0;
			}
			ps4.executeUpdate();
			ps5.executeUpdate();
			ResultSet rs5 = ps5.getResultSet();
			String pos;
			int scre=0;
			//out.println("aaaa");
			CharSequence[] speacials = new CharSequence[13];
			speacials[0]="_JJ";
			speacials[1]="_JJR";
			speacials[2]="_JJS";
			speacials[3]="_NN";
			speacials[4]="_NNS";
			speacials[5]="_NNP";
			speacials[6]="_NNPS";
			speacials[7]="_PRP";
			speacials[8]="_PRP$";
			speacials[9]="_RBR";
			speacials[10]="_RBS";
			speacials[11]="_WP";
			speacials[12]="_WP$";
			while(rs5.next())
			{
				pos=rs5.getString(1);
				//out.println("pos");
				int ln=rs5.getInt(2);
					int gg=0;
				if(pos.contains(speacials[0]))
					gg+=3;
				if(pos.contains(speacials[1]))
					gg+=3;
				if(pos.contains(speacials[2]))
					gg+=3;
				if(pos.contains(speacials[9]))
					gg+=2;
				if(pos.contains(speacials[10]))
					gg+=2;
				out.println("gg is"+gg+"linum"+ln);
					ps6.setInt(1,ln);
					ps6.executeUpdate();
			
					ResultSet rs6 = ps6.getResultSet();		
					//out.println("after rs");
					while(rs6.next())
					scre=rs6.getInt(1);
					scre=scre+gg;
					//out.println("score of line "+ln+ " is :" +scre);
					ps7.setInt(1,scre);
					ps7.setInt(2,ln);
					ps7.executeUpdate();
			}
			ps8.executeUpdate();
			//int scre=0;
			ResultSet rs8 = ps8.getResultSet();
			while(rs8.next())
			{
				int yy=rs8.getInt(1);
				int ll=rs.getInt(2);
				ps6.setInt(1,yy);
				ps6.executeUpdate();
				ResultSet rs6 = ps6.getResultSet();
				while(rs6.next())	
				scre=rs6.getInt(1);
				scre=scre+4;
				//out.println("score of line "+yy+ " is :" +scre);
				ps7.setInt(1,scre);
				ps7.setInt(2,yy);
				ps7.executeUpdate();
			}
			//out.println("</div>");
			
			out.println("</form> </h3>  </body> </html>");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

