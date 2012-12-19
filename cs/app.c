#include "stdio.h"

int main(int argc, const char *argv[])
{
	int ret = system("java -jar /home/lvjian/sts/mas-message/bin/jar/mas-message.0.1.0.jar -h");	
	printf("The help result: %d \n", ret);
	
	return 0;
}
