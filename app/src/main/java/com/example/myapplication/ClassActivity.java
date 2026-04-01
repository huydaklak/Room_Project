package com.example.myapplication;

import android.os.Bundle;
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

import com.example.myapplication.DAO.ClassDAO;
import com.example.myapplication.DB.DBConection;
import com.example.myapplication.adapter.ClassAdapter;
import com.example.myapplication.entity.StudentClass;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity {
    private EditText edCodeClass;
    private EditText edNameClass;
    private Button btnAddClass;
    private RecyclerView rcvClass;
    private Button btnBack;
    private Button btnUpdate;
    private Button btnDelete;
    private DBConection dbConection;
    private ClassDAO classDAO;
    private List<StudentClass> list;
    private StudentClass selectedClass;
    private ClassAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_class);

        edCodeClass = findViewById(R.id.ed_class_code);
        edNameClass = findViewById(R.id.ed_class_name);
        btnAddClass = findViewById(R.id.btn_add_class);
        btnBack = findViewById(R.id.btn_back);
        rcvClass = findViewById(R.id.rcv_class);
        btnUpdate = findViewById(R.id.btn_update_class);
        btnDelete = findViewById(R.id.btn_delete_class);

        dbConection = Room.databaseBuilder(this, DBConection.class, "Student.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        classDAO = dbConection.createClassDAO();
        rcvClass.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClassAdapter(new ArrayList<>());
        rcvClass.setAdapter(adapter);

        loadData();

        btnAddClass.setOnClickListener(v -> {
            String code = edCodeClass.getText().toString().trim();
            String name = edNameClass.getText().toString().trim();

            if (code.isEmpty() || name.isEmpty()){
                Toast.makeText(this, "Khong dc de trong", Toast.LENGTH_SHORT).show();
                return;
            }
            StudentClass studentClass = new StudentClass();
            studentClass.setClassCode(code);
            studentClass.setClassName(name);

            classDAO.insert(studentClass);
            loadData();

            edCodeClass.setText("");
            edNameClass.setText("");
        });

        adapter.setOnItemClickListener(c ->{
            selectedClass = c;
            edCodeClass.setText(c.getClassCode());
            edNameClass.setText(c.getClassName());
        });

        btnUpdate.setOnClickListener(v -> {
            if (selectedClass == null) return;

            selectedClass.setClassCode(edCodeClass.getText().toString());
            selectedClass.setClassName(edNameClass.getText().toString());

            classDAO.update(selectedClass);
            loadData();

            selectedClass = null;
        });

        btnDelete.setOnClickListener(v -> {
            if (selectedClass == null) return;

            classDAO.delete(selectedClass.getIdStudentClass());
            loadData();

            selectedClass = null;
        });

        btnBack.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadData() {
        list = classDAO.getAll();
        adapter.setData(list);
    }
}