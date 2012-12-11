#include "stdio.h"

int main(int argc, const char *argv[])
{
	int ret = system("../run -h");	
	printf("The result: %d \n", ret);
	return 0;
}
