import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
public class insertdb extends HttpServlet
{
  	PreparedStatement ps,ps1;
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
			ps=c.prepareStatement("INSERT INTO SUMM (WORD,PARANUM,LINENUM,NUM) VALUES(?,?,?,?)");
			ps1=c.prepareStatement("INSERT INTO SUMM2(WORD,PARANUM,LINENUM,NUM) VALUES(?,?,?,?)");
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
		  out.println(session.getAttribute("percentage"));
		  String path="E:";
		  path=path.concat(namef);
		  file = new File(path);
            		  FileInputStream fis = new FileInputStream(file.getAbsolutePath());
           		  HWPFDocument document = new HWPFDocument(fis);
         		  extractor = new WordExtractor(document);
            		  String[] fileData = extractor.getParagraphText();
		  int l=0,k=0,n=1,skip=0,y=1;
		  String abc;
           		  for (int i = 0; i < fileData.length; i++)
            		  {
			String t=fileData[i];
			String[] b1=t.split("\\s");
              		for(int j=0;j<b1.length;j++)
			{
				if(b1[j].equals("HYPERLINK")){
					out.println("+++");
					skip=2;
					continue;}
				if(skip>0){
					out.println("===");
					skip--;
					continue;
					}
				if(j==0)
					k=k+1;
				if(b1[j].length()<3 || b1[j].equals("he") || b1[j].equals("it"))
				{
					abc=b1[j];
					ps1.setString(1,abc);
					ps1.setInt(2,k);
					ps1.setInt(3,l+1);
					ps1.setInt(4,y);
					ps1.executeUpdate();
					n++;
					y++;
					continue;
				}
					
				
				abc=b1[j];
				
				ps.setString(1,abc);
				ps.setInt(2,k);
				ps.setInt(3,l+1);
				ps.setInt(4,n);
				ps1.setString(1,abc);
				ps1.setInt(2,k);
				ps1.setInt(3,l+1);
				ps1.setInt(4,y);
				y++;
				if(abc.endsWith( "." )){
					l++;
					//y++;
				}
				n++;
				ps.executeUpdate();
				ps1.executeUpdate();
				//if((n==146)||(n==402))
					//out.println("+++==="+b1[j]);
			}
           		 }
			res.sendRedirect("/new/sentence");
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
