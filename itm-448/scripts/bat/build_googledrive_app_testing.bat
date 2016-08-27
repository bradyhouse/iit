ECHO OFF
REM **************************************************************
REM COMMAND SCRIPT USED TO REBUILD THE GOOGLE DRIVE PROTOTYPE 
REM APP USING SENCHA CMD. THIS APP WAS USED TO FIGURE OUT HOW
REM TO USE THE GOOGLE DRIVE API IN CONCERT WITH THE EXTJS 5
REM FRAMEWORK.
REM **************************************************************
ECHO ON

:_SETUP_ENV__
	CALL env_profile.bat
	IF NOT EXIST "%WORKING_DIR%" (
		ECHO THE WORKING DIRECTORY, %WORKING_DIR%, DOES NOT EXIST.  PLEASE UPDATE THE env_profile.bat SCRIPT.
		PAUSE
		GOTO _ABORTED__
	)
	SET APP=GoogleDrive
	SET WORKSPACE_PATH="%WORKING_DIR%"\web
	SET APP_PATH=%WORKSPACE_PATH%\googledrive
	SET EXT_PATH=%WORKSPACE_PATH%\ext
	IF EXIST "%WORKSPACE_PATH%\build\testing\GoogleDrive" (
		FOR /D %%p IN ("%WORKSPACE_PATH%\build\testing\GoogleDrive\*.*") DO RMDIR "%%p" /s /q
	)

:_BUILD_APP__
	cd %APP_PATH%
	sencha app build testing
	IF %ERRORLEVEL% NEQ 0 (
		PAUSE
		GOTO _ABORTED__
	)
	GOTO _END__
	
:_ABORTED__
	exit /b
	
:_END__
	cd %SCRIPT_PATH%
	ECHO BUILD COMPLETE
	PAUSE 