package com.example.myapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "student",
        foreignKeys  = @ForeignKey(entity = StudentClass.class,
            parentColumns = "idStudentClass",
        childColumns = "classId",
        onDelete = ForeignKey.CASCADE
    )
)
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String code;

    private String name;

    private int age;
    private int classId;

    public Student() {
    }

    public Student(String code, String name, int age, int classId) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
