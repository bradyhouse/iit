ECHO OFF
REM **************************************************************
REM COMMAND SCRIPT USED TO START-UP THE PROTOTYPES APP TESTING BUILD
REM USING SENCHA CMD 5.0 (BETA).
REM **************************************************************
ECHO ON

:_SETUP_ENV__
	CALL env_profile.bat
	IF NOT EXIST "%WORKING_DIR%" (
		ECHO THE WORKING DIRECTORY, %WORKING_DIR%, DOES NOT EXIST.  PLEASE UPDATE THE env_profile.bat SCRIPT.
		PAUSE
		GOTO _ABORTED__
	)
	SET APP=Prototypes
	SET BUILD=testing
	SET WORKSPACE_PATH=%WORKING_DIR%\web
	SET APP_PATH=%WORKSPACE_PATH%\build\%BUILD%\%APP%

:_START_APP__
	cd %APP_PATH%
	sencha web start
	IF %ERRORLEVEL% NEQ 0 (
		PAUSE
		GOTO _ABORTED__
	)
	GOTO _END__
	
:_ABORTED__
	exit /b
	
:_END__
