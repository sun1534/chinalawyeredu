#cd G:\dzjx\Dzjc_Send_Sms
#set JAVA_HOME=C:\Program Files\Java\jre6
set path=%JAVA_HOME%\bin
set WEBPATH=e:\telcome\webapps\axis2\WEB-INF
set Java_Cmd=java -Djava.ext.dirs=%WEBPATH%\lib
set CLASSPATH=%WEBPATH%\classes;%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\lib\dt.jar;
%Java_Cmd% main.DxSendMain