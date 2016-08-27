gECHO OFF
REM **************************************************************
REM COMMAND SCRIPT USED TO REBUILD THE CRYPTIC APP USING
REM SENCHA CMD.
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
	SET WORKSPACE_PATH=%WORKING_DIR%\web
	SET APP_PATH=%WORKSPACE_PATH%\cryptic
	SET EXT_PATH=%WORKSPACE_PATH%\ext
	SET PUBLISH_PATH=%XAMPP_HTDOCS_DIR%\%APP%
	IF EXIST "%WORKSPACE_PATH%\build\testing\Cryptic" (
		FOR /D %%p IN ("%WORKSPACE_PATH%\build\testing\Cryptic\*.*") DO RMDIR "%%p" /s /q
	)

:_BUILD_APP__
	cd %APP_PATH%
	sencha app build testing
	IF %ERRORLEVEL% NEQ 0 (
		PAUSE
		GOTO _ABORTED__
	)

:_COPY_BUILD_DIR_TO_XAMPP_HTDOCS__
	IF EXIST "%PUBLISH_PATH%" (
		FOR /D %%p IN ("%PUBLISH_PATH%\*.*") DO RMDIR "%%p" /s /q
	)
	MKDIR "%PUBLISH_PATH%"
	XCOPY "%WORKSPACE_PATH%\build\testing\Cryptic" "%PUBLISH_PATH%" /e /y
	GOTO _END__
	
:_ABORTED__
	exit /b
	
:_END__
	cd %SCRIPT_PATH%
	ECHO BUILD COMPLETE
	PAUSE 