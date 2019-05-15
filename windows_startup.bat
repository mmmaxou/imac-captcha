FOR /f %i IN ('forfiles /s /m *.java /c "cmd /c ECHO @relpath"') DO @echo %~i >> sources.txt
JAVAC @sources.txt -d bin
XCOPY .\src\* .\bin\ /S /I /C /Exclude:sources.txt 
JAVA -cp bin fr.upem.captcha.Main