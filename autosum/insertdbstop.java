import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
public class insertdbstop extends HttpServlet
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
			ps=c.prepareStatement("INSERT INTO STOP (WORD) VALUES(?)");
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
		  String path="E:";
		  path=path.concat(namef);
		  file = new File(path);
            		  FileInputStream fis = new FileInputStream(file.getAbsolutePath());
           		  HWPFDocument document = new HWPFDocument(fis);
         		  extractor = new WordExtractor(document);
            		  String[] fileData = extractor.getParagraphText();
		  int l=0,k=0,n=1,skip=0;
           		  for (int i = 0; i < fileData.length; i++)
            		  {
			String t=fileData[i];
			String[] b1=t.split("\\s");
              		for(int j=0;j<b1.length;j++)
			{
				
				String abc=b1[j];
	
				ps.setString(1,abc);
				
				ps.executeUpdate();
				//if((n==146)||(n==402))
					//out.println("+++==="+b1[j]);
			}
           		 }
			//res.sendRedirect("/sw/Getallstop");
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

  	