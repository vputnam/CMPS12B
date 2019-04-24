public class QueueTest {

   public static void main(String[] args){
      Queue A = new Queue();
      A.enqueue((String)"cat");
      A.enqueue((int)3); 
      A.enqueue((int)9); 
      A.enqueue((int)7); 
      A.enqueue((String)"8");
      System.out.println(A.toString());
      System.out.println(A.peek());
      A.dequeue();
      A.dequeue();
      System.out.println(A.toString());
    
   }
}
