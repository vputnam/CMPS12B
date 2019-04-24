#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types --------------------------------------------------------------

const int tableSize=101;

// NodeObj
typedef struct NodeObj{
        char* key;
	char* value;
	struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* x, char* y){
	Node N = malloc(sizeof(NodeObj));
	assert(N!=NULL);
	N->key = x;
	N->value = y;
	N->next = NULL;
	return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
	if( pN!=NULL && *pN!=NULL ){
		Node N = *pN;
		free(N);
		*pN = NULL;
	}
}

// DicitonaryObj
typedef struct DictionaryObj{
	Node* table;
	int size;
}DictionaryObj;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
        int sizeInBits = 8*sizeof(unsigned int);
        shift = shift & (sizeInBits - 1);
        if ( shift == 0 )
return value;
return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
 unsigned int pre_hash(char* input) {
        unsigned int result = 0xBAE86554;
        while (*input) {
           result ^= *input++;
           result = rotate_left(result, 5);
        }
        return result;
     }

// hash()
// turns a string into an int in the range 0 to tableSize-1 int hash(char* key){
int hash(char* key){
     return pre_hash(key)%tableSize;
     }

// public functions -----------------------------------------------------------


// newDictionary()
// constructor for the Dictionary type

Dictionary newDictionary(void){
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D!=NULL);
	D->table = calloc(tableSize,sizeof(Node));
        D->size = 0;

	return D;
}

// freeDictionary()
// destructor for the Stack type
void freeDictionary(Dictionary* pD){
        if( pD!=NULL && *pD!=NULL ){
                if( !isEmpty(*pD) ) makeEmpty(*pD);
                free(*pD);
                *pD = NULL;
        }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
	if( D==NULL ){
		fprintf(stderr, 
				"Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	return(D->size==0);

}

// size()
// returns the number of pairs in Dictionary
int size(Dictionary D){
        return D->size;
}

// lookup()
// returns (key value) specified from D
// pre: !isEmpty(S)
char* lookup(Dictionary D, char* k){
	if(D == NULL){
		fprintf(stderr, "Dictionary Error: calling lookup() on  NULL Dictionary refrence\n"); 
		exit(EXIT_FAILURE);
	}
        int h = hash(k);
	Node N = D->table[h];
        while(N!=NULL){
	   if( N->key == k){
		return N->value;
	} 
        N = N->next;
    }
    return NULL;
}

//insert()
void insert(Dictionary D, char* k, char* v){
   int h = hash(k);
   Node P = newNode(k,v);

   if( D==NULL ){
                fprintf(stderr,"Dictionary Error: calling insert() on NULL Dictionary reference\n");
                exit(EXIT_FAILURE);
    } 
   if(lookup(D,k) != NULL){
                fprintf(stderr,"Cannot insert() duplicate key\n");
		exit(EXIT_FAILURE);
    }
    if( lookup(D,k) == NULL){
		D->table[h] = P;
    }else{
	P->next = D->table[h];
	D->table[h] = P;
    }
    D->size++;

}



//delete 
void delete(Dictionary D, char* k){
        int h = hash(k);
	Node N = D->table[h];
	Node P = NULL; 

	if(D==NULL){
		fprintf(stderr, "Dictionary Error: cannot delete on NULL Dictionary refrence.\n");
		exit(EXIT_FAILURE);
	}
	if(D->size==0){
		fprintf(stderr,"Dictionary Error: cannot delete empty dictionary.\n");
		exit(EXIT_FAILURE);
	}

        if(lookup(D, k) == NULL){
                fprintf(stderr,"Dictionary Error: cannot delete non-existant key.\n");
                exit(EXIT_FAILURE);
        }
        if( D->size == 1){
		freeNode(&N);
                freeNode(&P);
       //}else if(N->key == k){
         }else if(strcmp(N->key,k)==0){
		P = N;
		D->table[h] = N->next;
		freeNode(&P);
	}else{
	//	while(N->next->key != k){
                while(strcmp(N->next->key,k) !=0){
			N = N->next;
		}
		P = N->next;
		N->next = P->next;
		freeNode(&P);
	   }
	D->size--;
}


// printDictionary
// prints a text representation of S to the file pointed by out
// pre: none
void printDictionary(FILE* out, Dictionary D){
	Node N;
	if( D==NULL ){
		fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	for(int i=0; i<tableSize; i++){
           N = D->table[i];
           while(N != NULL){
              fprintf(out, "%s %s\n", N->key, N->value);
              N= N->next;
              }
        }
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
        int i = 0;
        if(D==NULL) {
                fprintf(stderr, "calling makeEmpty() on NULL Stack Dictionary\n");
        exit(EXIT_FAILURE);
        }
        if(D->size == 0){
                fprintf(stderr, "calling makeEmpty() on empty Dictionary\n");
                exit(EXIT_FAILURE);
        }
        Node P;
        for(i =0; i<tableSize; i++){
                 Node N = D->table[i];
                while(N!=NULL){
                P=N;
                N=N->next;
                delete(D,P->key);
                }
        }
        D->size = 0;
        free(&D->table[0]);

}






