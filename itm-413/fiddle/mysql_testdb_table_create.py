#!/usr/bin/python

import MySQLdb

# Open database connection
db = MySQLdb.connect("127.0.0.1","testuser","test123","TESTDB" )

# prepare a cursor object using cursor() method
cursor = db.cursor()

# Drop table if it already exist using execute() method.
cursor.execute("DROP TABLE IF EXISTS JOHNSNOW")

# Create table as per requirement
sql = "CREATE TABLE JOHNSNOW  (ID INT NOT NULL, NAME  CHAR(100) NOT NULL, PHONE CHAR(10), UNIQUE(ID) )"

cursor.execute(sql)

# disconnect from server
db.close()
