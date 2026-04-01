package com.example.myapplication.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.entity.StudentClass;

import java.util.List;
@Dao
public interface ClassDAO {
    @Insert
    void insert(StudentClass studentClass);
    @Query("SELECT * FROM class")
    List<StudentClass> getAll();
    @Update
    void update(StudentClass studentClass);
    @Query("DELETE FROM class WHERE idStudentClass = :id")
    void delete(int id);
}
