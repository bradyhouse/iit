ECHO OFF
REM **************************************************************
REM COMMAND SCRIPT USED TO START A WEB SERVER FROM THE PROTOTYPES
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
	SET STARTUP_PATH=%WORKING_DIR%\prototypes

:_START_APP__
	cd %STARTUP_PATH%
	sencha web start
	IF %ERRORLEVEL% NEQ 0 (
		PAUSE
		GOTO _ABORTED__
	)
	GOTO _END__
	
:_ABORTED__
	exit /b
	
:_END__
