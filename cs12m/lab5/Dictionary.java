public class Dictionary implements DictionaryInterface {

   // private inner Node class
   private class Node {
      String key;
      String value;
      Node next;

      Node(String x, String y){
         key = x;
         value = y;
         next = null;
      }
   }

   // Fields for the Dictionary class
   private Node head;     // reference to first Node in List
   private int numItems;  // number of pairs in this Dicitonary

   // Dicitonary()
   // constructor for the Dicitonary class
   public Dictionary(){
      head = null;
      numItems = 0;
   }


   // private helper function -------------------------------------------------

   // findKey()
   // returns a reference to the Node at position index in this Dicitonary
   private Node findKey(String key){
      Node N = head;   
      for(int i=1; i<= numItems; i++){
         if( N.key.compareTo(key) == 0){ 
         return N;
         }else{
         N= N.next;
      }
    }
    return null;
  }
   // ADT operations ----------------------------------------------------------

   // isEmpty()
   // pre: none
   // post: returns true if this Dictionary is empty, false otherwise
     // StringBuffer sb = new StringBuffer();
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // size()
   // pre: none
   // post: returns the number of pairs in this Dicitonary
   public int size() {
      return numItems;
   }

   // lookup()
   // pre: 1 <= index <= size()
   // post: returns item at position index in this Dicitonary
   public String lookup(String x){
      Node temp = findKey(x);
      if( temp != null){ 
         return temp.value;
      }else{   
         return null;
      }
   } 

   // insert()
   // inserts a new (key, vaule) to the Dictonary list
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), pairs to the right of the new pair are renumbered
   public void insert(String x, String y) 
      throws KeyCollisionException{
         Node temp = head;
         while(temp != null){
            if( x == temp.key){ 
               throw new KeyCollisionException(
               "Dictionary Error: cannot instert duplicate keys" );
         }
      temp = temp.next;
      }
      
      if(lookup(x) == null){
      if(isEmpty()){
         Node N = new Node(x,y);
         head = N;
         head.next = null;
         numItems++;
      }else{ 
         Node N = head;
         for(; N.next != null; N = N.next);
            N.next = new Node(x,y);
            N.next.next = null; 
            numItems++;
            }
         }
      }

   // delete()
   // deletes a (key, value) from this Dicitonary
   // pre: 1 <= index <= size()
   // post: pairs to the right of deleted pair are renumbered
    public void delete(String x)
      throws KeyNotFoundException{ 
         if(lookup(x) == null){ 
            throw new KeyNotFoundException( "Dictionary Error: cannot delete non-existant key");
         }
         if(lookup(x) != null){ 
            if(numItems == 1){ 
               Node N = head;     
               head = null;     
            } 
            Node H = head;
            if (H.key.compareTo(x) == 0){
               H = H.next;
               head = H;
            }else{ 
               Node N = head;
               Node P = null;
               while(N != null){
                  if(N.key == x){
                     P.next = N.next;
                     N = null;
                  }else{
                     P = N;
                     N = N.next;
                  }
               }
            }
        }
         numItems--;
      }


   // removeAll()
   // pre: none
   // post: isEmpty()
   public void makeEmpty(){
      head = null;
      numItems = 0;
   }

   // toString()
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node N = head;

      for( ; N!=null; N=N.next){
      sb.append(N.key+" "+N.value+"\n");
      }
      return new String(sb); 
   }

   
}
