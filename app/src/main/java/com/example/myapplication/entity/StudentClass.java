package com.example.myapplication.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "class")
public class StudentClass {
    @PrimaryKey(autoGenerate = true)
    private int idStudentClass;
    private String classCode;
    private String className;

    public StudentClass() {
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getIdStudentClass() {
        return idStudentClass;
    }

    public void setIdStudentClass(int idStudentClass) {
        this.idStudentClass = idStudentClass;
    }

    @Override
    public String toString() {
        return className;
    }
}
