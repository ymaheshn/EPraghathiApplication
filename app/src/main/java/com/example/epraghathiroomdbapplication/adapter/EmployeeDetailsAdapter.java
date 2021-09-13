package com.example.epraghathiroomdbapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epraghathiroomdbapplication.R;
import com.example.epraghathiroomdbapplication.dto.EmployeeDataDTO;
import com.example.epraghathiroomdbapplication.utils.EmployeeDiffUtil;

import java.util.List;

public class EmployeeDetailsAdapter extends RecyclerView.Adapter<EmployeeDetailsAdapter.MyViewHolder> {
    private List<EmployeeDataDTO> employeeDataDTOS;

    public EmployeeDetailsAdapter(List<EmployeeDataDTO> employeeDataDTOS) {
        this.employeeDataDTOS = employeeDataDTOS;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(employeeDataDTOS.get(position).getEmployeeId());
        holder.name.setText(employeeDataDTOS.get(position).getName());
        holder.designation.setText(employeeDataDTOS.get(position).getDesignation());
        holder.assignedTasks.setText(employeeDataDTOS.get(position).getAssignedProjects());
        holder.completedTasks.setText(employeeDataDTOS.get(position).getCompletedProjects());
        holder.longitude.setText(employeeDataDTOS.get(position).getLongitude());
        holder.latitude.setText(employeeDataDTOS.get(position).getLatitude());
    }

    public void addTasks(List<EmployeeDataDTO> newEmployee) {
        EmployeeDiffUtil employeeDiffUtil = new EmployeeDiffUtil(employeeDataDTOS, newEmployee);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(employeeDiffUtil);
        employeeDataDTOS.clear();
        employeeDataDTOS.addAll(newEmployee);
    }

    @Override
    public int getItemCount() {
        return employeeDataDTOS.size();
    }

    public EmployeeDataDTO getItem(int position) {
        return employeeDataDTOS.get(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final AppCompatTextView id;
        public final AppCompatTextView name;
        public final AppCompatTextView designation;
        public final AppCompatTextView completedTasks;
        public final AppCompatTextView assignedTasks;
        public final AppCompatTextView latitude;
        public final AppCompatTextView longitude;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_text);
            name = itemView.findViewById(R.id.employee_name);
            designation = itemView.findViewById(R.id.designation);
            completedTasks = itemView.findViewById(R.id.assigned_projects);
            assignedTasks = itemView.findViewById(R.id.completed_projects);
            latitude = itemView.findViewById(R.id.latitude);
            longitude = itemView.findViewById(R.id.longitude);
        }
    }
}
