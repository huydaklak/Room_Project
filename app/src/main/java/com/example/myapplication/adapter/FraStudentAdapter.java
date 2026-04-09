package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Student;

import java.util.List;

public class FraStudentAdapter extends RecyclerView.Adapter<FraStudentAdapter.ViewHolder> {
    private List<Student> studentList;
    public interface OnItemClickListerner{
        void onClick(Student student);
    }

    private OnItemClickListerner listerner;
    public FraStudentAdapter(List<Student> studentList, OnItemClickListerner listerner) {
        this.studentList = studentList;
        this.listerner = listerner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_fra, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(studentList.get(position).getName());
        holder.itemView.setOnClickListener(v -> {
            listerner.onClick(studentList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txtName);
        }
    }

}
