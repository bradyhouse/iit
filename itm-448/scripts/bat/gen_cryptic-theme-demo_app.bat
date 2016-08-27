ECHO OFF
REM **************************************************************
REM COMMAND SCRIPT USED TO GENERATE THE CRYPTIC-THEME DEMO APP
REM SCAFFOLDING USING SENCHA CMD V5 (BETA).  THIS DEMO APP
REM WILL BE USED TO TEST AND FINE TUNE THE CRYPTIC APPLICATION
REM THEME.
REM **************************************************************
ECHO ON

:_SETUP_ENV__
	CALL env_profile.bat
	IF NOT EXIST "%WORKING_DIR%" (
		ECHO THE WORKING DIRECTORY, %WORKING_DIR%, DOES NOT EXIST.  PLEASE UPDATE THE env_profile.bat SCRIPT.
		PAUSE
		GOTO _ABORTED__
	)
	SET APP=CrypticThemeDemo
	SET WORKSPACE_PATH=%WORKING_DIR%\web
	SET APP_PATH=%WORKSPACE_PATH%\cryptic-theme-demo
	SET EXT_PATH=%WORKSPACE_PATH%\ext
	SET SCRIPT_PATH=%WORKING_DIR%\scripts\sencha

cd %WORKSPACE_PATH%

:_GENERATE_THEME_DEMO_APP__
	sencha -sdk %EXT_PATH% generate app %APP% %APP_PATH%
	IF %ERRORLEVEL% NEQ 0 (
		PAUSE
		GOTO _ABORTED__
	)
:_BUILD_THEME_DEMO_APP__
	CD %APP_PATH%
	sencha app build
	IF %ERRORLEVEL% NEQ 0 (
		PAUSE
		GOTO _ABORTED__
	)
	GOTO _END__
	
:_ABORTED__
	exit /b
	
:_END__
	cd %SCRIPT_PATH%
	ECHO GENERATE COMPLETE
	PAUSE 