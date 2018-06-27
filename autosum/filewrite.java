import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class filewrite extends HttpServlet {
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
		ps=c.prepareStatement("select word from summ order by num");
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }

        String fileName = "temp.txt";

        try {
	File file = null;
	 String path="E:\\temp.txt";
	file = new File(path);
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	ps.executeUpdate();
	ResultSet rs = ps.getResultSet();
	out.println("cdsc");
	while(rs.next())
	{
		String qw=rs.getString(1);
		out.println(qw);
           	 bufferedWriter.write(qw);
           	 bufferedWriter.newLine();
	}
            bufferedWriter.close();
	out.println("99");
	res.sendRedirect("/sw/Stemmer");
        }
        catch(Exception ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
        }
    }
}