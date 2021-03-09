package com.example.sqlitesample

import android.content.ContentValues

class PersonDao {

    fun insertPerson(database:DBHelper,person_name:String,person_phone:String){
        val db=database.writableDatabase

        val values=ContentValues()
        values.put("person_name",person_name)
        values.put("person_phone",person_phone)

        db.insertOrThrow("person",null,values)
        db.close()
    }

    fun updatePerson(database:DBHelper,person_id:Int,person_name:String,person_phone:String){
        val db=database.writableDatabase

        val values=ContentValues()
        values.put("person_name",person_name)
        values.put("person_phone",person_phone)

        db.update("person",values,"person_id=?", arrayOf(person_id.toString()))
        db.close()
    }

    fun deletePerson(database: DBHelper,person_id: Int){
        val db=database.writableDatabase
        db.delete("person","person_id=?", arrayOf(person_id.toString()))
        db.close()
    }

    fun getAllPerson(database:DBHelper):ArrayList<Person>{
        val personList=ArrayList<Person>()
        val db=database.writableDatabase
        val cursor=db.rawQuery("SELECT * FROM person",null)

        while(cursor.moveToNext()){
            val person=Person(cursor.getInt(cursor.getColumnIndex("person_id"))
                    ,cursor.getString(cursor.getColumnIndex("person_name"))
                    ,cursor.getString(cursor.getColumnIndex("person_phone")))

            personList.add(person)
        }
        return personList
    }
}