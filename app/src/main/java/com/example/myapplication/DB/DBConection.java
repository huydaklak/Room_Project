package com.example.myapplication.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.DAO.StudentDAO;
import com.example.myapplication.entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class DBConection extends RoomDatabase {
     public abstract StudentDAO createStudentDAO();
}
