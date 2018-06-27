public class Test{

   public static void main(String args[]){
      String Str = new String("This is really not immutable!!");
      boolean retVal;

      retVal = Str.endsWith( "!" );
      System.out.println("Returned Value = " + retVal );

      retVal = Str.endsWith( "immu" );
      System.out.println("Returned Value = " + retVal );
   }
}