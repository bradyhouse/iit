#webscraping


import urllib
import re

'''
#Ex 1

htmltext = urllib.urlopen("http://www.google.com").read()
print htmltext

'''

''' 
#Ex 2

htmlfile = urllib.urlopen("http://finance.yahoo.com/q?s=aapl&q1=1")

htmltext = htmlfile.read()

regex = '<span id="yfs_l84_aapl">(.+?)</span>' 

pattern = re.compile(regex)

price = re.findall(pattern, htmltext)

print price
'''
 
'''
#Ex 3

name = raw_input(">")

htmlfile = urllib.urlopen("http://finance.yahoo.com/q?s=%s" % name)

htmltext = htmlfile.read()

regex = '<span id="yfs_l84_%s">(.+?)</span>' % name

pattern = re.compile(regex)

price = re.findall(pattern, htmltext)

print price
'''

'''
#Ex 4

# select parameters
symbolslist = ["goog","amzn","aapl","yhoo","dell.mx","msft"]


i=0
while i<len(symbolslist):
    url="http://finance.yahoo.com/q?s=" +symbolslist[i] +"&q1=1"
    htmlfile = urllib.urlopen(url)
    htmltext = htmlfile.read()
    regex = '<span id="yfs_l84_' + symbolslist[i] +'">(.+?)</span>' 
    pattern = re.compile(regex)
    price = re.findall(pattern, htmltext)
    print "The price of", symbolslist[i], " is ",price
    i+=1
'''    
    
