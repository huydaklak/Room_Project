package com.example.myapplication.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.entity.Student;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert
     void insert(Student student);
    @Query("SELECT * FROM student")
    List<Student> list();
}
