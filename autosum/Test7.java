public class Test7 {

   public static void main(String args[]) {
      String Str1 = new String("abc");
      //String Str2 = new String("abc");
      //String Str3 = new String("ABC");
      String str[]=new String[3];
      str[1]="abc";
      str[2]="ABC";
      str[3]="ede";
      boolean retVal;
      
      while(true)
      {
        for(int i=0;i<str.length;i++)
        {
            retVal = Str1.equals( str[i] );
            System.out.println("Returned Value = " + retVal );
        }
      }
   }
}