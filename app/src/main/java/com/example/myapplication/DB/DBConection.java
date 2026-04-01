package com.example.myapplication.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.DAO.ClassDAO;
import com.example.myapplication.DAO.StudentDAO;
import com.example.myapplication.entity.Student;
import com.example.myapplication.entity.StudentClass;

@Database(entities = {Student.class, StudentClass.class}, version = 2)
public abstract class DBConection extends RoomDatabase {
     public abstract StudentDAO createStudentDAO();
     public abstract ClassDAO createClassDAO();
}
