#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types --------------------------------------------------------------

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
	//N->key = calloc(strlen(x),sizeof(char));
	//sprintf(N->key, "%s\0", x);
	//N->value = calloc(strlen(y),sizeof(char)); 
	//sprintf(N->value, "%s\0", y);
	N->next = NULL;
	return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
	if( pN!=NULL && *pN!=NULL ){
		Node N = *pN;
		//free(N->key);
		//free(N->value);
		free(N);
		*pN = NULL;
	}
}

// DicitonaryObj
typedef struct DictionaryObj{
	Node head;
	int numItems;
} DictionaryObj;

// public functions -----------------------------------------------------------

Node findKey(Dictionary D, char* x){
	Node N = D->head;
	while(N != NULL){
		if(strcmp(N->key, x) == 0){
			return N;
		}else{
			N = N->next;
		}
	}
	return NULL;    
}


// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D!=NULL);
	D->head = NULL;
	D->numItems = 0;
	return D;
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
	return(D->numItems==0);

}

// lookup()
// returns (key value) specified from D
// pre: !isEmpty(S)
char* lookup(Dictionary D, char* x){
	if(D == NULL){
		fprintf(stderr, "Dictionary Error: calling lookup() on  NULL Dictionary refrence\n"); 
		exit(EXIT_FAILURE);
	}
	Node N = findKey(D,x);
	if( N !=NULL ){
		return N->value;
	}else{
		return NULL;
	} 
}


//insert()
void insert(Dictionary D, char* k, char* v){
   Node curr = NULL;
   Node new = newNode(k,v);

   if( D==NULL ){
                fprintf(stderr,
                "Dictionary Error: calling insert() on NULL Dictionary reference\n");
                exit(EXIT_FAILURE);
    } 
    

    Node temp = D->head;
    while(temp != NULL){
       if(k == temp->key){
               fprintf(stderr, 
               "Dictionary Error: cannot instert duplicate keys\n" );
                exit(EXIT_FAILURE);
       } 
      temp = temp->next;
   }
   if(isEmpty(D)){
     D->head=new;
   }else{
   for(curr=D->head; curr->next!=NULL; curr = curr->next ){
    }
       curr->next = new;
       }

   D->numItems++;
 return;
}



//delete 
void delete(Dictionary D, char* k){
	Node N;
	if(D==NULL){
		fprintf(stderr, "Dictionary Error: cannot delete on NULL Dictionary refrence.\n");
		exit(EXIT_FAILURE);
	}
	if(D->numItems==0){
		fprintf(stderr,"Dictionary Error: cannot delete empty dictionary.\n");
		exit(EXIT_FAILURE);
	}

        if(lookup(D, k) == NULL){
                fprintf(stderr,"Dictionary Error: cannot delete non-existant key.\n");
                exit(EXIT_FAILURE);
        }

	if(lookup(D, k) != NULL){
		if(D->numItems == 1){
			N = D->head;
			D->head = NULL;
			freeNode(&N);
			D->numItems--;
			return;
		}
		N = D->head;
		D->numItems--;
		if(strcmp(N->key,k) == 0){
			D->head = N->next;
			freeNode(&N);
		}else{
			N = D->head->next;
			Node P = D->head;
			while(N != NULL){
				if(strcmp(N->key,k) == 0){
					P->next = N->next;
					freeNode(&N);
					return;
				}else{ 
					P = N;
					N = N->next;
				}
			}
		}
	}
}

// printDictionary
// prints a text representation of S to the file pointed by out
// pre: none
void printDictionary(FILE* out, Dictionary D){
	Node N;
	if( D==NULL ){
		fprintf(stderr, 
				"Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	for(N=D->head; N!=NULL; N=N->next) fprintf(out, "%s %s\n", N->key, N->value);
	//fprintf(out, "\n");
}

//deleteAll
//deletes all pairs of string and value in dictionary
void makeEmpty(Dictionary D){
	if( D==NULL ){
		fprintf(stderr, 
				"Stack Error: calling popAll() on NULL Stack reference\n");
		exit(EXIT_FAILURE);
	}
	if( D->numItems==0 ){
		fprintf(stderr, "Stack Error: calling popAll() on empty Stack\n");
		exit(EXIT_FAILURE);
	}
	Node N = D->head;
	Node P;
	while(N != NULL){
		P = N;
		N = N->next;
		delete(D, P->key);
		//freeNode(&N);      
	}  
	D->numItems=0;
	//freeNode(&N);
	

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

// size()
// returns the number of pairs in Dictionary
int size(Dictionary D){
	return D->numItems;
}




