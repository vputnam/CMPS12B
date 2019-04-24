#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* char_a;    // string holding all alphabetic chars
   char* char_d;    // string holding all digit chars
   char* char_p;    // string holding all punctuation chars
   char* char_w;    // string holding all whitespace chars
   int n=1 ;        // number of lines in input file

   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alpha_num on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   char_a = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   char_d = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   char_p = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   char_w = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   assert( line!=NULL && char_a!=NULL && char_d!=NULL && char_p!=NULL && char_w!=NULL );

   // read each line in input file, extract alpha-numeric characters 
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      extract_chars(line, char_a, char_d, char_p, char_w);
      fprintf(out, "line %d contains:\n", n);
      if((int)strlen(char_a) >= 2){
         fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(char_a), char_a);
      }else{ 
         fprintf(out, "%d alphabetic character: %s\n", (int)strlen(char_a), char_a);
      }if((int)strlen(char_d) >= 2){
          fprintf(out, "%d numeric characters: %s\n",(int)strlen(char_d), char_d);
      }else{ 
          fprintf(out, "%d numeric character: %s\n",(int)strlen(char_d), char_d);
      }if((int)strlen(char_p) >= 2){
          fprintf(out, "%d punctuation characters: %s\n", (int)strlen(char_p), char_p);
      }else{
          fprintf(out, "%d punctuation character: %s\n", (int)strlen(char_p), char_p);
      }if((int)strlen(char_w) >= 2){
          fprintf(out, "%d whitespace characters: %s\n", (int)strlen(char_w), char_w);
      }else{ 
          fprintf(out, "%d whitespace character: %s\n", (int)strlen(char_w), char_w);
      }
      n++;
   } 

   // free heap memory 
   free(line);
   free(char_a);
   free(char_d);
   free(char_p);
   free(char_w);

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

// function definition 
void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0, j=0, k=0, l=0, m=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
      if( isalpha( (int)s[i]) ){
         a[j++] = s[i];
         i++;
      } if( isdigit( (int)s[i]) ){
         d[k++] = s[i];
         i++;
      } if( ispunct( (int)s[i]) ){
         p[l++] = s[i];
         i++;
      } if( isspace( (int)s[i]) ){
         w[m++] = s[i];
         i++;
   }
   a[j] = '\0';
   d[k] = '\0';
   p[l] = '\0';
   w[m] = '\0';  
  }

}






