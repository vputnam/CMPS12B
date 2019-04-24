import java.util.*;

public class Queue implements QueueInterface {

   // private inner Node class
   private class Node {
      Object item;
      Node next;

      Node(Object x){
         this.item = x;  
         next = null;
      }
   }

   // Fields for the Dictionary class
   private Node head;     // reference to first Node in List
   private Node tail;     // refrence to last Node in list
   private int numItems;  // number of items in this Queue


   // Queue()
   // constructor for the Queue class
   public Queue(){
      head = null;
      tail = null; 
      numItems = 0;
   }
 
   // ADT operations ----------------------------------------------------------

   // isEmpty()
   // pre: none
   // post: returns true if this Queue is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // length()
   // pre: none
   // post: returns the number of items in this Dicitonary
   public int length() {
      return numItems;
   }

   // peek()
   // pre: 1 <= index <= size()
   // post: returns item at position index in this D
   public Object peek() throws QueueEmptyException{
      if( numItems==0 ){
         throw new QueueEmptyException("cannot peek() empty queue");
      }else{
      Node H = head; //do we want to peek at tail or head?
      return H.item;
   }
 }
   // enqueue()
   // inserts a new item to the Queue list
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), pairs to the right of the new pair are renumbered
   public void enqueue(Object x){ 
         Node H = head;
         Node T = tail;
      if(H == null){ //insert at tail
         head = new Node(x);
         numItems++;       
      }else{ 
         while(H.next != null){
            H = H.next;
         } 
         H.next = new Node(x);
         T = H.next;
         numItems++;
     }
   }
      

   // dequeue()
   // deletes an item from this Queue
   // pre: 1 <= index <= size()
   // post: pairs to the right of deleted pair are renumbered
    public Object dequeue()
      throws QueueEmptyException{
         Node N = head;
         Node T = tail; 
         if(N == null){ 
            throw new QueueEmptyException( "Queue Error: dequeue cannnot delete empty queue");
         } 
         if(N != null){
            if(numItems == 1){ //list only has one element
               head = null;
               tail = null;
               numItems--;
             }else{ //delete at head 
               N = N.next;
               head = N;
               numItems--;
    }
  }
  return null;
} 
   // dequeueAll()
   // pre: none
   // post: isEmpty()
   public void dequeueAll()
      throws QueueEmptyException{
         if(head == null){ 
            throw new QueueEmptyException( "Queue Error: dequeue cannnot delete empty queue");
         }else{ 

      head = null;
      tail = null;
      numItems = 0;
   }
}
  // toString()
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      String s = "";
      for(Node N = head; N != null; N=N.next){
         s += (N.item+ " ");
      }
      return s; 
   }
   
}
