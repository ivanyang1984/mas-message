#mas-message

北京台MAS短信接口客户端。

##使用环境:

* JDK: 1.6 or later

##开发环境

* JDK: 1.6 or later
* Ant: > 1.8.x
* Bash(Optional): for linux

##使用

###Linux环境

	./run -h	
	
###windows环境

	java -jar bin/jar/jar mas-message.0.1.0.jar -h
	
具体使用参考帮助输出.

###程序返回值说明

* 400: 参数错误
* 404: 未找到Mas服务器
* 500: API调用出错
* 1 or none: 程序运行成功


 
