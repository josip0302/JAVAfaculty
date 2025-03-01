#include "myfactory.h"
#include <windows.h>
#include <stdio.h>
#include <string.h>
typedef char const* (*PTRFUN)(char const*);

void* myfactory(char const* libname, char const* ctorarg,char const* alloc){
	
	char dllfile[256];
	strcpy(dllfile,"./");
	strcat(dllfile,libname);
   strcat(dllfile,".dll");
 
	//printf("%s\n",dllfile);
    HINSTANCE hinstLib = LoadLibrary(libname);
	if (hinstLib == NULL) {
		
		return NULL;
	}

	PTRFUN function = (PTRFUN)GetProcAddress(hinstLib, "create")(ctorarg);
	if (function == NULL) {
	
		FreeLibrary(hinstLib);
		return NULL;
	}
	
	 return function;
	
}