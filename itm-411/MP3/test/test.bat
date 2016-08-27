
IF EXIST ..\output\mp3out.txt DEL ..\output\mp3out.txt 

java -jar "..\dist\mp3.jar" ..\data\stateTimeZoneMap.csv ..\output 1>> ..\output\mp3out.txt  2> ..\output\mp3err.txt

TYPE ..\output\mp3err.txt >> ..\output\mp3out.txt

DEL ..\output\mp3err.txt

WRITE ..\output\mp3out.txt

