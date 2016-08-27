ECHO OFF
REM **************************************************************
REM COMMAND SCRIPT USED TO INIT THE REPOSITORY NAME AND EMAIL
REM ATTRIBUTES USING SENCHA CMD V5 (BETA).  THIS IS NECESSARY FOR 
REM PACKAGES TO BE CONSUMED (OR REQUIRED) BY APPS DEFINED IN 
REM THE WEB WORKSPACE.
REM **************************************************************
ECHO ON

:_SETUP_ENV__
	CALL env_profile.bat
	IF NOT EXIST "%WORKING_DIR%" (
		ECHO THE WORKING DIRECTORY, %WORKING_DIR%, DOES NOT EXIST.  PLEASE UPDATE THE env_profile.bat SCRIPT.
		PAUSE
		GOTO _ABORTED__
	)
	IF %NAME% EQU "<REPO CREATOR'S NAME>" (
		ECHO THE NAME ENVIRONMENT VARIABLE HAS NOT BEEN SET.  PLEASE UPDATE THE env_profile.bat SCRIPT.
		PAUSE
		GOTO _ABORTED__
	)
	IF %EMAIL% EQU "<REPO CREATOR'S EMAIL>" (
		ECHO THE EMAIL ENVIRONMENT VARIABLE HAS NOT BEEN SET.  PLEASE UPDATE THE env_profile.bat SCRIPT.
		PAUSE
		GOTO _ABORTED__
	)
	
	SET PACKAGE=cryptic-theme
	SET WORKSPACE_PATH=%WORKING_DIR%\web
	SET PACKAGE_PATH=%WORKSPACE_PATH%\BUILD\%PACKAGE%
	SET EXT_PATH=%WORKSPACE_PATH%\ext
	SET SCRIPT_PATH=%WORKING_DIR%\scripts\sencha

cd %WORKSPACE_PATH%

:_BUILD_THE_PACKAGE__
	sencha package repo init -name %NAME% -email %EMAIL%
	IF %ERRORLEVEL% NEQ 0 (
		PAUSE
		GOTO _ABORTED__
	)
	GOTO _END__
	
:_ABORTED__
	exit /b
	
:_END__
	cd %SCRIPT_PATH%
	ECHO PACKAGE ADDED
	PAUSE 