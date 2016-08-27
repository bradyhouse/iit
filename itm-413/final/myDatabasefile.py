# coding=utf-8
################################################################
# PROJECT:
# Working with Tkinter (part 2) & a MySQL database
# -------------------------------------------------------------
# OBJECTIVE:
# To modify your lab 6 program to work with a MySQL
# database you will create.
# -------------------------------------------------------------
# DESCRIPTION:
# This project will have you create a myDatabase.py file
# to interact with your existing tkContacts.py file you
# modified for lab 6. Your myDatabasefile.py will include
# the following functions to perform the following CRUD
# operations:
# a.	Create a table
# b.	Read from the table
# c.	Update the table
# d.	Delete from the table
# e.	Insert into the table
# -------------------------------------------------------------
# AUTHOR:       Brady Houseknecht
# BORN ON:      07/27/2014
# DUE ON:       07/28/2014 6 PM
# REVISION:     1.0
#
# -------------------------------------------------------------
# REVISION HISTORY:
# 1.0 Baseline - BH
#
###############################################################

import MySQLdb
from contacts import *









class myDatabaseFile:

    def __init__(self, host, db, user, password, table_name) :
        self.db_host_name = host
        self.db_name = db
        self.db_user = user
        self.db_password = password
        self.db_table_name = table_name


    def create_connection (self) :
        db = MySQLdb.connect( self.db_host_name,self.db_user,self.db_password,self.db_name )
        return db


    def create_table (self) :
        db = self.create_connection()
        cursor = db.cursor()
        sql = "SHOW TABLES LIKE '" + self.db_table_name + "'"
        print sql
        cursor.execute(sql)
        result = cursor.fetchone()
        if not result:
            sql = "CREATE TABLE " + self.db_table_name + " (ID INT NOT NULL, NAME  CHAR(100) NOT NULL, PHONE CHAR(100), UNIQUE(ID) )"
            print sql
            try:
                cursor.execute(sql)
                self.load_table(db)
            except:
                db.rollback()
        db.close()


    def load_table (self, db) :
        global contact_list
        index = 1
        for name,phone in contact_list :
            sql = "INSERT INTO " + self.db_table_name + "(ID, NAME, PHONE) VALUES (" + str(index) + ", '"+ name +"', '" + phone + "')"
            index+=1
            print sql
            try:
                db.cursor().execute(sql)
                db.commit()
            except:
                db.rollback()


    def read_table (self) :
        contacts = []
        db = self.create_connection()
        cursor = db.cursor()
        sql = "SELECT NAME, PHONE FROM " + self.db_table_name + " ORDER BY NAME ASC"
        print sql
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            for row in results:
                name = row[0]
                phone = row[1]
                contacts.append([name, phone])

        except:
            print "Error: unable to read the " + self.db_table_name + " table."

        db.close()
        return contacts


    def read_table_next_id (self) :
        next_id = 0
        db = self.create_connection()
        cursor = db.cursor()
        sql = "SELECT MAX(ID)+1 'NEXT_ID' FROM " + self.db_table_name
        print sql
        try:
            cursor.execute(sql)
            result = cursor.fetchone()
            next_id = int(result[0])
        except:
            db.rollback()
        db.close()
        return next_id


    def read_table_valid_id (self, id) :
        rc = True
        db = self.create_connection()
        cursor = db.cursor()
        sql = "SELECT COUNT(*) 'MATCHES' FROM " + self.db_table_name + " WHERE ID = " + str(id)
        print sql
        try:
            cursor.execute(sql)
            result = cursor.fetchone()
            if (int(result[0]) == 0):
                rc = False
        except:
            db.rollback()
            rc = False
        db.close()
        return rc


    def read_table_max_id (self, name) :
        max_id = 0
        db = self.create_connection()
        cursor = db.cursor()
        sql = "SELECT MAX(ID) 'ID' FROM " + self.db_table_name + " WHERE NAME='" + name + "'"
        print sql
        try:
            cursor.execute(sql)
            result = cursor.fetchone()
            max_id = int(result[0])
        except:
            db.rollback()
        db.close()
        return max_id


    def insert_table (self, name, phone) :
        rc = True
        next_id = self.read_table_next_id()
        existing_id = self.read_table_max_id(name)
        if(next_id > 0 & existing_id == 0):
            db = self.create_connection()
            cursor = db.cursor()
            sql = "INSERT INTO " + self.db_table_name + "(ID, NAME, PHONE) VALUES ("+ str(next_id) +", '"+ name +"', '" + phone + "')"
            print sql
            try:
                db.cursor().execute(sql)
                db.commit()
            except:
                db.rollback()
                rc = False
            db.close()
        else:
            rc = False
        return rc


    def update_table (self, id, name, phone) :
        rc = True
        if(self.read_table_valid_id(id)):
            db = self.create_connection()
            cursor = db.cursor()
            sql = "UPDATE " + self.db_table_name + " SET NAME ='" + name + "', PHONE = '" + phone + "' WHERE ID = " + str(id)
            print sql
            try:
                db.cursor().execute(sql)
                db.commit()
            except:
                db.rollback()
                rc = False
            db.close()
        else:
            rc = False
        return rc


    def delete_table (self, id) :
        rc = True
        if(self.read_table_valid_id(id)):
            db = self.create_connection()
            cursor = db.cursor()
            sql = "DELETE FROM " + self.db_table_name + " WHERE ID = " + str(id)
            print sql
            try:
                db.cursor().execute(sql)
                db.commit()
            except:
                db.rollback()
                rc = False
            db.close()
        else:
            rc = False
        return rc
