ECHO OFF
REM **************************************************************
REM COMMAND SCRIPT USED TO GENERATE THE "WEB" WORKSPACE 
REM DIRECTORY USING SENCHA CMD 5.0 (BETA).
REM **************************************************************
ECHO ON

:_SETUP_ENV__
	CALL env_profile.bat
	IF NOT EXIST "%WORKING_DIR%" (
		ECHO THE WORKING DIRECTORY, %WORKING_DIR%, DOES NOT EXIST.  PLEASE UPDATE THE env_profile.bat SCRIPT.
		PAUSE
		GOTO _ABORTED__
	)
	
SET APP=Cryptic
SET APP_PATH=itm448\cryptic
SET EXT_PATH=C:\Users\Brady\bin\ext
SET SCRIPT_PATH=%WORKING_DIR%\scripts\sencha

cd %WORKING_DIR%

:_GENERATE_WORKSPACE__
	sencha -sdk %EXT_PATH% generate workspace web
	IF %ERRORLEVEL% NEQ 0 (
		PAUSE
		GOTO _ABORTED__
	)
	GOTO _END__
	
:_ABORTED__
	exit /b
	
:_END__
	ECHO WORKSPACE GEN COMPLETE
	cd %SCRIPT_PATH%
	PAUSE 
