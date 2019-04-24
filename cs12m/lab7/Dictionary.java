public class Dictionary implements DictionaryInterface{

// NodeObj
private class Node{
   String key;
   String value;
   Node left;
   Node right;

// newNode()
// constructor for private Node type
Node (String k, String v) {
   key = k;
   value = v;
   left = null;
   right = null;
   }
}

private Node root;
private int numPairs;

// DictionaryObj
Dictionary(){
   Node root = null;
   int numPairs = 0;
} 

// findKey()
// returns the Node containing key k in the subtree rooted at R, or returns
// NULL if no such Node exists
private Node findKey(Node R, String k){
   if(R== null || k == R.key) 
      return R;
   if(k.compareTo(R.key) < 0 ){ 
      return findKey(R.left, k);
   }else{
      return findKey(R.right, k);
   } 
}

// findParent()
// returns the parent of N in the subtree rooted at R, or returns NULL 
// if N is equal to R. (pre: R != NULL)
private Node findParent(Node N, Node R){
   Node P=null;
   if( N!=R ){
      P = R;
      while( P.left!=N && P.right!=N ){
         if(N.key.compareTo(P.key) <0)
            P = P.left;
         else
            P = P.right;
      }
   }
   return P;
}

// findLeftmost()
// returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
private Node findLeftmost(Node R){
   Node L = R;
   if( L!=null ) for( ; L.left!=null; L=L.left) ;
   return L;
}

// printInOrder()
// prints the (key, value) pairs belonging to the subtree rooted at R in order
// of increasing keys to file pointed to by out.
private String printInOrder(Node R){
   String s = "";
   if( R!=null ){
      s = printInOrder(R.left)+R.key+" "+ R.value+"\n"+printInOrder(R.right);
   }
   return s; 
}

// deleteAll()
// deletes all Nodes in the subtree rooted at N.
private void deleteAll(Node N){
   if( N!=null ){
      deleteAll(N.left);
      deleteAll(N.right);
   }
}

// public functions -----------------------------------------------------------

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
public boolean isEmpty(){
   return(numPairs==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
public int size(){
   return numPairs;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
public String lookup(String key){
   Node N=findKey(root,key);
   if( N != null ){
     return N.value;
   }else{
     return null;
  } 
}
// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
public void insert(String k, String v) throws KeyCollisionException{
   Node N, A, B;
   if( findKey(root, k)!=null){
      throw new KeyCollisionException("Dictionary error: Cannot insert duplcate keys.");  
   }
   N = new Node(k, v);
   B = null;
   A = root;
   while( A!=null ){
      B = A;
      if(k.compareTo(A.key) <0) A = A.left;
      else A = A.right;
   }
   if( B==null ) root = N;
   else if(k.compareTo(B.key) <0) B.left = N;
   else B.right = N;
   numPairs++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
public void delete(String k) throws KeyNotFoundException{
   Node N, P, S;
   N = findKey(root, k);
   if( N== null ){
         throw new KeyCollisionException("Dictionary Error: cannot delete() non-existent key: ");
   }
   if( N.left==null && N.right==null ){
      if( N==root ){
         root = null;
      }else{
         P = findParent(N, root);
         if( P.right==N ) P.right = null;
         else P.left = null;
      }
   }else if( N.right== null){
      if( N==root ){
         root = N.left;
      }else{
         P = findParent(N, root);
         if( P.right==N ) P.right = N.left;
         else P.left = N.left;
      }
   }else if( N.left==null ){
      if( N==root ){
         root = N.right;
      }else{
         P = findParent(N,root);
         if( P.right==N ) P.right = N.right;
         else P.left = N.right;
      }
   }else{  // N->left!=NULL && N->right!=NULL
      S = findLeftmost(N.right);
      N.key = S.key;
      N.value = S.value;
      P = findParent(S, N);
      if( P.right==S ) P.right = S.right;
      else P.left = S.right;
   }
   numPairs--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
public void makeEmpty(){
   root = null;
   numPairs = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
public String toString(){
   String s = printInOrder(root);
   return s;
   }
}
