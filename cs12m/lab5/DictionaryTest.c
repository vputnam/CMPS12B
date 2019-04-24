#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){

      char* v;
      Dictionary A = newDictionary();
      insert(A,"a","apple");
      insert(A,"b","bat");
      insert(A,"c","cat");
      insert(A,"d","dog");
      insert(A,"e","elephant");
      insert(A,"f","frog");
      insert(A,"g","goat");
      printDictionary(stdout, A);


      v=lookup(A,"a");
      printf("key=\"%s\" %s\"%s\"\n", "a", (v==NULL?"not found ":"value="), "apple");
      v=lookup(A,"g");
      printf("key=\"%s\" %s\"%s\"\n", "g", (v==NULL?"not found ":"value="), "goat");

      // A.insert(A,"a","ape");  // causes KeyCollisionException

      // A.delete(A,"h");  // causes KeyNotFoundException

      delete(A, "a");
      delete(A, "c");
      delete(A, "g");

      printDictionary(stdout, A);

      v=lookup(A,"a");
      printf("key=\"%s\" %s\"%s\"\n", "a", (v==NULL?"not found ":"value="), "apple");
      v=lookup(A,"z");
      printf("key=\"%s\" %s\"%s\"\n", "g", (v==NULL?"not found ":"value="), "goat");


   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));
   makeEmpty(A);
   printf("%s\n", (isEmpty(A)?"true":"false"));
   freeDictionary(&A);

   return(EXIT_SUCCESS);
}
