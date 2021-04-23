#include <stdio.h>     
#include <stdlib.h>
#include <stdbool.h>

bool reconnaitRec_3(char *m){
    if(strlen(m) == 0){
        return true;
    }
    else{
        char c = m[0];
        
        if(c>= '0' && c<='9'){
            reconnaitRec_3();
        }
    }
}

int main()
{

}