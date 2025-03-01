#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef char const* (*PTRFUN)();

struct Tiger
{ PTRFUN* vtable;
   char * ime;
  
};
char const* name(struct Tiger * t){
  return t->ime;
}
char const* greet(void){
  return "grrr!";
}
char const* menu(void){
  return "Jelene";
}

PTRFUN vtable[3]={
   name,
   greet,
   menu
};

void constructTiger(struct Tiger *tigar, char * ime, char * alloc){
  tigar->ime=(char*)malloc(sizeof(*ime));
   if(strcmp(alloc,"stack")){
    tigar->ime=(char*)alloca(sizeof(*ime));
    
  }else if (strcmp(alloc,"heap"))
  {
   tigar->ime=(char*)malloc(sizeof(*ime));
     
  }
    tigar->ime=ime;
    tigar->vtable=vtable;
}
struct Tiger* create(char*ime,char* alloc){ 
  struct Tiger* tigar; 
  if(strcmp(alloc,"stack")){
    tigar=(struct Tiger*)alloca(sizeof(struct Tiger));;
    
  }else if (strcmp(alloc,"heap"))
  {
   tigar=(struct Tiger*)malloc(sizeof(struct Tiger));;
     
  }
  
  constructTiger(tigar,ime,alloc);  
  return tigar;
} 

size_t size_of(struct Tiger* tigar){
  return sizeof(tigar);
}