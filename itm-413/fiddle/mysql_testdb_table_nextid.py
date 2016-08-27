#!/usr/bin/python

import MySQLdb

# Open database connection
db = MySQLdb.connect("127.0.0.1","testuser","test123","TESTDB" )

# prepare a cursor object using cursor() method
cursor = db.cursor()
next_id = 0
sql = "SELECT MAX(ID)+1 'NEXT_ID' FROM JOHNSNOW"
cursor.execute(sql)
result = cursor.fetchone()
next_id = int(result[0])
print next_id
