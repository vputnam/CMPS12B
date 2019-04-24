#include <stdio.h>

void probeSeq(int H[], int len){
     int i, k, probe;
     int table = 11;
     for(k = 0; k < len; k++){ 
	for(i = 0; i < table; i++){
		probe = ((H[k]%11) + i*(1+H[k]%10))%11;
		printf("%d ", probe);
       }
    printf("\n");
    }
}

int main(){
	int H[] = {34, 25, 79, 56, 6};
        probeSeq(H,5);
}
