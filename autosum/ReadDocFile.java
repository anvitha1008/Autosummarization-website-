import java.io.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class ReadDocFile
{
    public static void main(String[] args)
    {
        File file = null;
        WordExtractor extractor = null;
        try
        {

            file = new File("E:\\autosum\\samplesmall.doc");
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument document = new HWPFDocument(fis);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            for (int i = 0; i < fileData.length; i++)
            {
	String t=fileData[i];
	String delimiter=" ";
	String[] b1=t.split("\\. ");
                for(int j=0;j<b1.length;j++)
	{
		
		System.out.println(b1[j]);

	}
	System.out.println("NEXT---------------------------");
            }
	 
        }
        catch (Exception exep)
        {
            exep.printStackTrace();
        }
    }
}