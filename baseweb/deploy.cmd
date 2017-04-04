set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
call mvn clean
call mvn -DskipTest=true -P self-war package 
pause