# coding=utf-8
################################################################
# PROJECT:
# Final Lab - Working with Tkinter (part 2) & a MySQL database
# -------------------------------------------------------------
# OBJECTIVE:
# To modify your lab 6 program to work with a MySQL
# database you will create.
# -------------------------------------------------------------
# DESCRIPTION:
# 1.  Adjust any functions you see fit from your tkContacts.py
# script, so that any updates, deletes, loads, or adds are
# performed by the said operations defined in your
# myDatabasefile.py script. Note here you don’t really need a
# ‘Save’ button, unless you deem it worthy to have it somehow,
# so just delete it from the GUI and any respective callback
# function defined that’s glued to it.
# You see when the user presses your button, your callback
# function should automatically get passed the right contact
# information selected by the user which in turn will make the
# necessary changes to the particular contact record on the
# server immediately!
#
# 2. Keep any remaining functions you deem necessary to
# have the correct running app, such as makeWindow(),
# setSelect() and whichSelected(). Your program should
# ultimately load in all the records your inserted in
# step 1 into the listbox, similarly to how you had
# the records load into the listbox from our contacts.py
# file in lab 6.
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

import os
from Tkinter import *
import tkMessageBox
from connection import *
from myDatabasefile import *

app_title="My Contact List"

db = myDatabaseFile(db_host_name, db_name, db_user, db_password, db_table_name)

app_contact_id = -1

def clearContactId () :
    global app_contact_id
    app_contact_id = -1

def setContactId (name) :
    global app_contact_id
    app_contact_id = db.read_table_max_id(name)

def getContactId () :
    global app_contact_id
    return app_contact_id

def selection () :
    try:
        return int(select.curselection()[0])
    except:
        return -1


def onAddContact () :
    if(nameVar.get() == ""):
        showError("Please enter a valid name for the new contact")
    else:
        addContact()


def addContact () :
    if (db.insert_table(nameVar.get(), phoneVar.get())):
        setList ()
    else:
        showError("Please make sure the name that does not already exist.")


def showError (msg) :
    global app_title
    tkMessageBox.showerror(title=app_title + ":~ Error", \
    message=msg)


def onUpdateContact () :
    name = nameVar.get()
    phone = phoneVar.get()
    if (name == "" ):
        showError("The name field cannot be left blank. Please enter a value.")
    else:
        updateContact()


def updateContact () :
    if (db.update_table(getContactId(), nameVar.get(), phoneVar.get())):
        setList ()
    else:
        showError("Failed to update database.  Please try again.")


def onDeleteContact () :
    if(selection() > 0):
        name, phone = contactlist[selection()]
        deleteContact(name)
    elif(nameVar.get()!=""):
        deleteContact(nameVar.get())
    else:
        showError("Please select or load a contact before pressing delete.")


def deleteContact (name) :
    global app_title
    if (tkMessageBox.askokcancel(title=app_title, \
        message="Are you sure want to delete " + name +"?") == 1):
        if (db.delete_table(getContactId())):
            nameVar.set("")
            phoneVar.set("")
            clearContactId()
            setList ()
        else:
            showError("Failed to update database.  Please try again.")


def loadContact  () :
    name, phone = contactlist[selection()]
    setContactId(name)
    nameVar.set(name)
    phoneVar.set(phone)


def confirmExit () :
    global app_title
    if (tkMessageBox.askokcancel(title=app_title, \
        message="Are you want to exit?") == 1):
            os._exit(1)


def buildFrame () :
    global nameVar, phoneVar, select, app_title
    root = Tk()

    frame1 = Frame(root)
    frame1.master.title(app_title)
    frame1.pack()

    Label(frame1, text="Name:").grid(row=0, column=0, sticky=W)
    nameVar = StringVar()
    name = Entry(frame1, textvariable=nameVar)
    name.grid(row=0, column=1, sticky=W)

    Label(frame1, text="Phone:").grid(row=1, column=0, sticky=W)
    phoneVar= StringVar()
    phone= Entry(frame1, textvariable=phoneVar)
    phone.grid(row=1, column=1, sticky=W)

    frame1 = Frame(root)       # add a row of buttons
    frame1.pack()
    btn1 = Button(frame1,text=" Add  ",command=onAddContact)
    btn2 = Button(frame1,text="Update",command=onUpdateContact)
    btn3 = Button(frame1,text="Delete",command=onDeleteContact)
    btn4 = Button(frame1,text=" Load ",command=loadContact)
    btn1.pack(side=LEFT); btn2.pack(side=LEFT)
    btn3.pack(side=LEFT); btn4.pack(side=LEFT)

    frame1 = Frame(root)       # allow for selection of names
    frame1.pack()
    scroll = Scrollbar(frame1, orient=VERTICAL)
    select = Listbox(frame1, yscrollcommand=scroll.set, height=7)
    scroll.config (command=select.yview)
    scroll.pack(side=RIGHT, fill=Y)
    select.pack(side=LEFT,  fill=BOTH)

    frame1 = Frame(root)    # add an Exit button at the bottom
    frame1.pack()
    btn6 = Button(frame1,text=" Exit ",command=confirmExit)
    btn6.pack(side=BOTTOM)

    return root


def setList () :
    global contactlist
    contactlist = db.read_table()
    select.delete(0,END)
    for name,phone in contactlist :
        select.insert (END, name)


root = buildFrame()
db.create_table()
setList ()
root.mainloop()
os._exit(1)