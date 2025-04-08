@REM @echo off
set API_NAME=ETU003777
set BUILD_DIR=build
set LIB_DIR=lib
set SRC_DIR=src\main\Java
set WEB_DIR=src\main\WebApp
set TOMCAT_DIR=D:\Etude\S4\tomcat10128
set WORKSPACE_DIR=D:\Etude\S4\MrNaina\ETU003777

rem Construire le CLASSPATH avec tous les JAR du dossier lib
set CLASSPATH=
for %%i in (%LIB_DIR%\*.jar) do set CLASSPATH=!CLASSPATH!;%%i

rem Supprimer le répertoire build s'il existe
if exist %BUILD_DIR% rmdir /s /q %BUILD_DIR%

rem Créer le répertoire des classes
mkdir %BUILD_DIR%\WEB-INF\classes
mkdir %BUILD_DIR%\WEB-INF\lib

rem Lister tous les fichiers .java dans src et ses sous-répertoires
dir /s /b %SRC_DIR%\*.java > sources.txt

rem Compiler les fichiers Java
javac -cp "%CLASSPATH%" -d %BUILD_DIR%\WEB-INF\classes @sources.txt

rem Supprimer le fichier sources.txt après la compilation
del sources.txt

rem Copier tous les fichiers HTML du workspace vers build
dir /s /b %WORKSPACE_DIR%\*.html > html.txt
for /f "delims=" %%f in (html.txt) do xcopy "%%f" "%BUILD_DIR%\" /Y
del html.txt

rem Copier tous les fichiers JSP du workspace vers build
dir /s /b %WORKSPACE_DIR%\*.jsp > jsp.txt
for /f "delims=" %%f in (jsp.txt) do xcopy "%%f" "%BUILD_DIR%\" /Y
del jsp.txt

rem Copier le contenu de WEB_DIR dans BUILD_DIR
xcopy %WEB_DIR%\* %BUILD_DIR%\ /E /I /Y

rem Copier les fichiers JAR dans WEB-INF/lib
xcopy %LIB_DIR%\*.jar %BUILD_DIR%\WEB-INF\lib\ /Y

rem Générer le fichier WAR
cd %BUILD_DIR%
jar -cvf ../%API_NAME%.war *
cd ..

rem Supprimer l'ancien répertoire déployé s'il existe
if exist %TOMCAT_DIR%\webapps\%API_NAME% rmdir /s /q %TOMCAT_DIR%\webapps\%API_NAME%

rem Copier le fichier WAR vers le répertoire webapps de Tomcat
xcopy %API_NAME%.war %TOMCAT_DIR%\webapps\ /Y

rem Créer un répertoire pour décompresser le WAR
mkdir %TOMCAT_DIR%\webapps\%API_NAME%

rem Décompresser le WAR dans le répertoire
cd %TOMCAT_DIR%\webapps\%API_NAME%
jar -xvf ..\%API_NAME%.war
cd ..

rem Supprimer uniquement le fichier WAR créé


pause
