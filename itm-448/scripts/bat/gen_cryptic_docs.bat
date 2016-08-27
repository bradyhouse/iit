ECHO OFF
REM **************************************************************
REM COMMAND SCRIPT USED TO GENERATE THE CRYPTIC'S DOCUMENTATION
REM WEB USING JSDUCK
REM **************************************************************
ECHO ON

CALL env_profile.bat
SET APP=Cryptic
SET WORKSPACE_PATH=%WORKING_DIR%\web
SET APP_PATH=%WORKSPACE_PATH%\cryptic
SET EXT_PATH=%WORKSPACE_PATH%\ext
SET SCRIPT_PATH=%WORKING_DIR%\scripts\sencha

cd %WORKSPACE_PATH%

:_GENERATE_APP__
	jsduck --config jsduck-cryptic.json --output cryptic/docs

:_END__
	cd %SCRIPT_PATH%


