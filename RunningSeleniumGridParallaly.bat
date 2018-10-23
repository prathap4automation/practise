set projectLocation=E:\\selenium\\class_programs
cd %projectLocation%
set classpath=%projectLocation%\lib\*;%projectLocation%\bin
java org.testng.TestNG %projectLocation%\gridTest.xml
pause