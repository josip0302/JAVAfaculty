#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef char const* (*PTRFUN)();

struct Parrot
{ PTRFUN* vtable;
   char * ime;
  
};
char const* name(struct Parrot * t){
  return t->ime;
}
char const* greet(void){
  return "bok bok!";
}
char const* menu(void){
  return "sjmeneke suncokreta";
}

PTRFUN vtable[3]={
   name,
   greet,
   menu
};

void constructParrot(struct Parrot *papiga, char * ime){
  papiga->ime=(char*)malloc(sizeof(*ime));
    papiga->ime=ime;
    papiga->vtable=vtable;
}
struct Parrot* create(char*ime){  
  struct Parrot* papiga=(struct Parrot*)malloc(sizeof(struct Parrot));;
  constructParrot(papiga,ime);
  return papiga;
}