package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.StudentClass;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder>{
    private List<StudentClass> classList;
    public interface OnItemClickListener{
        void onClick(StudentClass studentClass);
    }
    private OnItemClickListener listener;

    public ClassAdapter(List<StudentClass> classList) {
        this.classList = classList;
    }
    public void setData(List<StudentClass> list) {
        this.classList = list;
        notifyDataSetChanged();
    }

    public List<StudentClass> getClassList() {
        return classList;
    }

    public void setClassList(List<StudentClass> classList) {
        this.classList = classList;
        notifyDataSetChanged();
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentClass studentClass = classList.get(position);
        holder.txtClassCode.setText(studentClass.getClassCode());
        holder.txtClassName.setText(studentClass.getClassName());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(studentClass);
            }
        });
    }

    @Override
    public int getItemCount() {
        if ((classList != null)){
            return classList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       private TextView txtClassCode;
       private TextView txtClassName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtClassCode = itemView.findViewById(R.id.txt_class_code);
            txtClassName = itemView.findViewById(R.id.txt_class_name);
        }
    }

}
