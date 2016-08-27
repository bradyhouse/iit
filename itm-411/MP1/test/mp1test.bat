
java -jar "..\dist\mp1.jar" 1> mp1out.txt 2> mp1err.txt

type mp1err.txt >> mp1out.txt

del mp1err.txt

write mp1out.txt
