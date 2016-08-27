#!/usr/bin/python

import MySQLdb

contact_list = [
      ['Siemens, Harper',  '323-4149'],
      ['Smith, Patti',  '239-1212'],
      ['Jackson, Janet',   '313-1352'],
      ['Manfredi, Ralph','872-2221'],
      ['Thompson, Bobby',   '365-2622'],
      ['James, Lebron',  '457-6223'],
      ['Ziegler, Zig',   '667-1101'],
      ['Robbins, Tony',     '329-2310']
    ]
index = 1
# Open database connection
db = MySQLdb.connect("127.0.0.1","testuser","test123","TESTDB" )

# prepare a cursor object using cursor() method
cursor = db.cursor()

for name,phone in contact_list :
    sql = "INSERT INTO JOHNSNOW(ID, NAME, PHONE) VALUES (" + str(index) + ", '"+ name +"', '" + phone + "')"
    try:
        db.cursor().execute(sql)
        db.commit()
        index+=1
    except:
        db.rollback()
# disconnect from server
db.close()
