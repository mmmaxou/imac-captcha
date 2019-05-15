# Imac'ge Captcha

## Windows Install

Not tested

### 1. Compile java Files

  ```bash
  for /f %i in ('forfiles /s /m *.java /c "cmd /c echo @relpath"') do @echo %~i >> sources.txt
  javac @sources.txt -d bin
  ```

### 2. Copy Ressources
  
  ```bash
  xcopy .\src\* .\bin\ /S /I /C /Exclude:sources.txt
  ```

### 3. Run

  ```bash
  java -cp bin fr.upem.captcha.Main  
  ```

* __In one command__

```bash
for /f %i in ('forfiles /s /m *.java /c "cmd /c echo @relpath"') do @echo %~i >> sources.txt && javac @sources.txt -d bin && xcopy .\src\* .\bin\ /S /I /C /Exclude:sources.txt && java -cp bin fr.upem.captcha.Main
```

or

```bash
./windows_startup.bat
```

## Linux Install

### 1. Compile java files

  ```bash
  find . -name "*.java" -print | xargs javac -d bin
  ```

### 2. Copy ressources

  ```bash
  rsync -avz --exclude '*.java' ./src/ ./bin/
  ```

### 3.  Run

  ```bash
  java -cp bin fr.upem.captcha.Main
  ```

* __In one command__

```bash
rsync -avz --exclude '*.java' ./src/ ./bin/ && find . -name "*.java" -print | xargs javac -d bin && java -cp bin fr.upem.captcha.Main
```

or

```bash
./linux_startup.sh
```
