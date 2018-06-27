// test program for 
import java.io.*;
import java.util.*;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.*;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadServletstop extends HttpServlet {
   
   boolean isMultipart;
   private String filePath;
   private int maxFileSize = 1024 * 10000;
   private int maxMemSize = 1024 * 10000;
   private File file ;

   public void init( ){
      // Get the file location where it would be stored.
      filePath = 
             getServletContext().getInitParameter("file-upload"); 
   }
   public void doPost(HttpServletRequest request, 
               HttpServletResponse response)
              throws ServletException, java.io.IOException {
	HttpSession session=request.getSession();
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
	//out.println("fil"+filePath);
	// Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
     // out.println("mul  is   "+isMultipart);
      if( !isMultipart ){
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("<p>No file uploaded</p>"); 
         out.println("</body>");
         out.println("</html>");
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
      List<FileItem> fileItems = upload.parseRequest(request);
	
      // Process the uploaded file items
      Iterator<FileItem> i = fileItems.iterator();

      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet up</title>");  
      out.println("</head>");
      out.println("<body>");
      while ( i.hasNext () ) 
      {
	
         FileItem fi = (FileItem)i.next();
	//out.println("if value is "+!fi.isFormField () );
         if (!fi.isFormField() )	
         {
		//out.println("inside ifffdWsd");
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
		out.println(fileName);
            String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
		//out.println("field name "+fieldName+" - fileName "+fileName+" -contenttype "+ contentType+" - inmemory  "+isInMemory+"---size ="+sizeInBytes);
            // Write the file  
		//out.println("----inside ifgbh  =="+fileName.substring(fileName.lastIndexOf("\\")+1)+"ENDDds");
            if( fileName.lastIndexOf("\\") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
               file = new File( filePath + 
               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }
	     out.println("FILENAME LAfdkj--->"+file);
            fi.write( file ) ;
            //out.println("Uploaded Filename: " + fileName + "<br>");
	session.setAttribute("fileName",fileName);
	response.sendRedirect("/sw/insertdbstop");
         }
      }
      out.println("</body>");
      out.println("</html>");
   }catch(Exception ex) {
       System.out.println(ex);
   }
   }
   public void doGet(HttpServletRequest request, 
                       HttpServletResponse response)
        throws ServletException, java.io.IOException {
        response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
	//out.println("sds");
        throw new ServletException("GET method used with " +
                getClass( ).getName( )+": POST method required.");
   } 
}