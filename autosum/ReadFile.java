import java.io.*;
public class ReadFile
{
    public static void main(String[] args)
    {
      			//String speacials[]=new String[30];
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
			String str="!abc";
			for(int i=0;i<29;i++)
			{
			if(str.contains(speacials[i]))
				System.out.println("yes");
			}
	}
}