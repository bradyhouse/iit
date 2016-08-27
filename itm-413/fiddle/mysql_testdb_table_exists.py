#!/usr/bin/python

import MySQLdb

# Open database connection
db = MySQLdb.connect("127.0.0.1","testuser","test123","TESTDB" )

# prepare a cursor object using cursor() method
cursor = db.cursor()

sql = "SHOW TABLES LIKE 'JOHNSNOW'"
cursor.execute(sql)
result = cursor.fetchone()
if not result:
    sql = "CREATE TABLE JOHNSNOW (FIRST_NAME  CHAR(20) NOT NULL,LAST_NAME  CHAR(20),AGE INT,SEX CHAR(1),INCOME FLOAT )"
    cursor.execute(sql)
else:
    print 'THE TABLE ALREADY EXISTS'
