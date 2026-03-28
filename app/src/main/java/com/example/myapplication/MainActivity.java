package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.DAO.StudentDAO;
import com.example.myapplication.DB.DBConection;
import com.example.myapplication.adapter.StudentAdapter;
import com.example.myapplication.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edCode;
    private EditText edName;
    private EditText edAge;
    private Button btnAdd;
    private Button btnUpdate;
    private RecyclerView rcvData;
    private DBConection dbConection;
    private StudentDAO studentDAO;
    private List<Student> studentList;
    private StudentAdapter studentAdapter;
    private Student selectedStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edCode = findViewById(R.id.ed_student_code);
        edName = findViewById(R.id.ed_student_name);
        edAge = findViewById(R.id.ed_student_age);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        rcvData = findViewById(R.id.rcv_main);
        dbConection = Room.databaseBuilder(this, DBConection.class, "Student.db")
                .allowMainThreadQueries()
                .build();
        studentDAO = dbConection.createStudentDAO();

        rcvData.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new StudentAdapter(new ArrayList<>());
        rcvData.setAdapter(studentAdapter);
        loadData();

        btnAdd.setOnClickListener(v -> {
            addStudent();
        });

        btnUpdate.setOnClickListener(v -> {
            updateStudent();
        });
        studentAdapter.setOnItemClickListener(student -> {
            selectedStudent = student;

            edCode.setText(student.getCode());
            edName.setText(student.getName());
            edAge.setText(String.valueOf(student.getAge()));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void updateStudent() {
        if (selectedStudent == null) {
            Toast.makeText(this, "Chọn sinh viên cần sửa", Toast.LENGTH_SHORT).show();
            return;
        }

        String code  = edCode.getText().toString().trim();
        String name = edName.getText().toString().trim();
        String ageStr = edAge.getText().toString().trim();

        if (code.isEmpty() || name.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (Exception e) {
            Toast.makeText(this, "Tuổi phải là số", Toast.LENGTH_SHORT).show();
            return;
        }

        selectedStudent.setCode(code);
        selectedStudent.setName(name);
        selectedStudent.setAge(age);

        studentDAO.update(selectedStudent);

        loadData();

        edCode.setText("");
        edName.setText("");
        edAge.setText("");

        selectedStudent = null;
    }

    private void addStudent() {
        String code  = edCode.getText().toString().trim();
        String name = edName.getText().toString().trim();
        String ageStr = edAge.getText().toString().trim();

        if (code.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mã sinh viên", Toast.LENGTH_SHORT).show();
            return;
        }

        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên sinh viên", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ageStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tuổi", Toast.LENGTH_SHORT).show();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age <= 0 || age > 100) {
                Toast.makeText(this, "Tuổi phải từ 1 đến 100", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Tuổi phải là số ", Toast.LENGTH_SHORT).show();
            return;
        }
        Student student = new Student(code, name, age);
        studentDAO.insert(student);
        loadData();

        edCode.setText("");
        edName.setText("");
        edAge.setText("");
    }
    
    

    private void loadData() {
        studentList = studentDAO.list();
        studentAdapter.setData(studentList);
    }

}