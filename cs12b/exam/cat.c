#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

char* cat(char* s1, char* s2){
   int len1=0, len2=0, i;
   for(i=0; s1[i]!='\0'; i++) len1++;
   for(i=0; s2[i]!='\0'; i++) len2++;

   char* ans = calloc((len1+len2+1),sizeof(char));
   for(i=0; i<=len1; i++){
      ans[i]=s1[i];
   }
   for(i=len1; i<=len2+len1; i++){
      ans[i]=s2[i-len1];
   }
   ans[len1+len2+1] = '\0';
   for(i=0; i<=(len1+len2+1); i++){
      printf("%c", ans[i]);
   }
   return ans;
}

char main(int argc, char** argv){

   char str1[] = "Hello";
   char str2[] = "World";
   cat(str1,str2); 




return(EXIT_SUCCESS);
}

