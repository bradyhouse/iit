ECHO OFF
REM **************************************************************
REM COMMAND SCRIPT USED TO GENERATE THE INITIAL APPLICATION
REM SCAFFOLDING FOR THE CRYPTIC APP USING SENCHA CMD 5.0 (BETA)
REM **************************************************************
ECHO ON

CALL env_profile.bat
SET APP=Cryptic
SET WORKSPACE_PATH=%WORKING_DIR%\web
SET APP_PATH=%WORKSPACE_PATH%\cryptic
SET EXT_PATH=%WORKSPACE_PATH%\ext
SET SCRIPT_PATH=%WORKING_DIR%\scripts\sencha

cd %WORKING_DIR%

:_GENERATE_APP__
	sencha -sdk %EXT_PATH% generate app %APP% %APP_PATH%
	cd %APP_PATH%
	
:_GENERATE_CONTROLLERS__
	sencha generate controller header.BreadCrumbsController
	sencha generate controller footer.FooterController
	sencha generate controller sidebar.SidebarController
	
	sencha generate controller content.platform.PlatformController
	sencha generate controller content.platform.mixin.Platform
	sencha generate controller content.platform.mixin.Local
	sencha generate controller content.platform.mixin.Cloud
	
	sencha generate controller content.processes.ProcessesController

	sencha generate controller content.processes.mixin.Processes
	sencha generate controller content.processes.mixin.selecttype.SelectType
	sencha generate controller content.processes.mixin.selecttype.Encrypt
	sencha generate controller content.processes.mixin.selecttype.Decrypt
	
	sencha generate controller content.processes.mixin.pick.encrypt.PickLocal
	sencha generate controller content.processes.mixin.pick.encrypt.Local
	sencha generate controller content.processes.mixin.pick.encrypt.Continue
	sencha generate controller content.processes.mixin.pick.encrypt.GoogleDrive
	
	sencha generate controller content.processes.mixin.pick.decrypt.PickLocal
	sencha generate controller content.processes.mixin.pick.decrypt.Local
	sencha generate controller content.processes.mixin.pick.decrypt.Continue
	sencha generate controller content.processes.mixin.pick.decrypt.GoogleDrive
	
	sencha generate controller content.processes.mixin.Encrypt
	sencha generate controller content.processes.mixin.Decrypt
	sencha generate controller content.processes.mixin.Decrypt
	
	sencha generate controller content.processes.mixin.pick.Local
	sencha generate controller content.processes.mixin.place.Local
	
	sencha generate controller content.provider.ProviderController
	sencha generate controller content.provider.mixin.Provider 
	sencha generate controller content.provider.mixin.GoogleDrive
	sencha generate controller content.provider.mixin.Other
	
	sencha generate controller content.passphrases.PassPhrasesController
	sencha generate controller content.passphrases.mixin.PassPhrases
	sencha generate controller content.passphrases.mixin.encrypt.EncryptPassPhrase
	sencha generate controller content.passphrases.mixin.encrypt.Encrypt
	sencha generate controller content.passphrases.mixin.encrypt.PassPhrase
	sencha generate controller content.passphrases.mixin.decrypt.DecryptPassPhrase
	sencha generate controller content.passphrases.mixin.decrypt.Decrypt
	sencha generate controller content.passphrases.mixin.decrypt.PassPhrase
	
	sencha generate controller content.postprocesses.PostProcessController
	sencha generate controller content.postprocesses.mixin.PostProcess
	sencha generate controller content.postprocesses.mixin.Download
	sencha generate controller content.postprocesses.mixin.NextFile
	

:_GENERATE_VIEWS__
	sencha generate view north.BreadCrumbs
	sencha generate view center.Content
	sencha generate view east.Sidebar
	sencha generate view south.Footer
	REM WIREFRAME 1
	sencha generate view center.platform.Platform
	REM WIREFRAME 2, 3, 4
	sencha generate view center.provider.Provider
	sencha generate view center.provider.GoogleDrive
	sencha generate view center.provider.Other
	REM WIREFRAME 5
	sencha generate view center.processes.Processes
	sencha generate view center.processes.selecttype.Encrypt
	sencha generate view center.processes.selecttype.Decrypt
	sencha generate view center.processes.selecttype.SelectType
	REM WIREFRAME 6
	sencha generate view center.platform.Local
	REM WIREFRAME 2, 3, 4
	sencha generate view center.platform.Cloud
	REM WIREFRAME 5, 6, 8
	sencha generate view center.processes.Encrypt
	REM WIREFRAME 7, 9, 10,11
	sencha generate view center.processes.pick.encrypt.Local
	sencha generate view center.processes.pick.encrypt.Continue
	sencha generate view center.processes.pick.encrypt.PickFile
	sencha generate view center.processes.pick.encrypt.GoogleDrive
	sencha generate view center.processes.pick.decrypt.GoogleDrive
	sencha generate view center.processes.pick.decrypt.PickFile
	sencha generate view center.processes.pick.decrypt.Local
	sencha generate view center.processes.pick.decrypt.Continue
	REM WIREFRAME 12
	sencha generate view center.postprocesses.PostProcess
	sencha generate view center.postprocesses.Download
	sencha generate view center.postprocesses.NextFile
	sencha generate view center.postprocesses.Subtitle
	
	sencha generate view center.passphrases.encrypt.FilePath
	
:_REFRESH_APP__
	sencha app refresh

:_END__
	cd %SCRIPT_PATH%
