
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
public class inserttest extends HttpServlet
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
  		    //ps=c.prepareStatement("INSERT INTO TEST (WORD) VALUES(?)");
			ps=c.prepareStatement("INSERT INTO SUMM (WORD,PARANUM,LINENUM) VALUES(?,?,?)");
			//ps=c.prepareStatement("INSERT INTO SUMM (WORD) VALUES(?)");
  		  }
		  catch(Exception e)
  		  {
      		   e.printStackTrace();
  		  }
	 	 File file = null;
        		 WordExtractor extractor = null;
       		 try
       		 {
		  String namef=(String)session.getAttribute("fileName");
		  String path="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\new\\";
		  path=path.concat(namef);
		  file = new File(path);
            		  FileInputStream fis = new FileInputStream(file.getAbsolutePath());
           		  HWPFDocument document = new HWPFDocument(fis);
         		  extractor = new WordExtractor(document);
            		  String[] fileData = extractor.getParagraphText();
		int l=0,k=0;
		String a="q";
           		  for (int i = 0; i < fileData.length; i++)
            		  {
			//k=i+1;
			String t=fileData[i];
			//out.println("--------->"+t+"<-------");
			//if((k==2)||(k==4)||(k==5))
			//out.println("--------->"+t+"<-------");
			//if(k==2)
			//	out.println("QQQQQ");
			if(t==null)
			{
				out.println("1234567890");
				continue;
			}
			String[] b1=t.split("\\s");
              		for(int j=0;j<b1.length;j++)
			{
				if(j==0)
					k=k+1;
				String abc=b1[j];
				if(abc.endsWith( "." ))
					l++;
				ps.setString(1,abc);
				ps.setInt(2,k);
				ps.setInt(3,l+1);
				ps.executeUpdate();
				//out.println(b1[j]);
			}
           		 }
        	             }
        	            catch (Exception exep)
      	            {
           			 exep.printStackTrace();
       	            }
	          
	  }
		
	 catch(Exception e)
	 {
	        	e.printStackTrace();
	 }	

     }
}