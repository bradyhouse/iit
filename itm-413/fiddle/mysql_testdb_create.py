#!/usr/bin/python

import MySQLdb as db
db1 = db.connect(host="localhost",user="root")
cur = con.cursor()
cur.execute('CREATE DATABASE HOUSEDB;')


