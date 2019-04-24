public class DictionaryTest{

   public static void main(String[] args){
      
      String v;
      Dictionary A = new Dictionary();
      A.insert("a","apple");
      A.insert("b","bat");
      A.insert("c","cat");
      A.insert("d","dog");
      A.insert("e","elephant");
      A.insert("f","frog");
      A.insert("g","goat"); 
      System.out.println(A);


      v = A.lookup("a"); 
      System.out.println("key=a "+(v==null?"not found":("value="+v)));
      v = A.lookup("b");
      System.out.println("key=b "+(v==null?"not found":("value="+v)));
      v = A.lookup("g");
      System.out.println("key=g "+(v==null?"not found":("value="+v)));
      v = A.lookup("z"); 
      System.out.println("key=z "+(v==null?"not found":("value="+v)));
      System.out.println();
   
      // A.insert("a","ape");  // causes KeyCollisionException
       
       System.out.println();

       A.delete("e");
       A.delete("g");
       A.delete("b");
       System.out.println(A);

      // A.delete("h");  // causes KeyNotFoundException

       System.out.println(A.isEmpty());
       System.out.println(A.size());
       A.makeEmpty();
       System.out.println(A.isEmpty());
       System.out.println(A);

   }
}
