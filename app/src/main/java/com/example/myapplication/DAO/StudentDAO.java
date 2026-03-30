package com.example.myapplication.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.entity.Student;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert
     void insert(Student student);
    @Query("SELECT * FROM student")
    List<Student> list();
    @Update
    void update(Student selectedStudent);
    @Query("SELECT * FROM student WHERE name LIKE '%' || :keyword || '%'")
    List<Student> search(String keyword);
}
