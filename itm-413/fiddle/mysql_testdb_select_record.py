#!/usr/bin/python

import MySQLdb

# Open database connection
db = MySQLdb.connect("127.0.0.1","testuser","test123","TESTDB" )

# prepare a cursor object using cursor() method
cursor = db.cursor()

# Prepare SQL query to INSERT a record into the database.
sql = "SELECT * FROM bradyhouse ORDER BY NAME ASC"
try:
   # Execute the SQL command
   cursor.execute(sql)
   # Fetch all the rows in a list of lists.
   results = cursor.fetchall()
   for row in results:
      name = row[0]
      phone = row[1]
      # Now print fetched result
      print "name=%s,phone=%s" % \
             (name, phone )
except:
   print "Error: unable to fecth data"

# disconnect from server
db.close()
