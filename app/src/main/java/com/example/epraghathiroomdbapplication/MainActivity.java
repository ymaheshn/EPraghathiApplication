package com.example.epraghathiroomdbapplication;

import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_ASSIGNED;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_COMPLETED;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_DELETE;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_DESIGNATION;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_EMPLOYEE_ID;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_LATITUDE;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_LONGITUDE;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_NAME;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_TASK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epraghathiroomdbapplication.adapter.EmployeeDetailsAdapter;
import com.example.epraghathiroomdbapplication.dto.EmployeeDataDTO;
import com.example.epraghathiroomdbapplication.repository.EmployeeRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public FloatingActionButton floatingActionButton;
    public static final int REQUEST_CODE = 1;
    public RecyclerView recyclerView;
    private AppCompatTextView textView;
    private EmployeeRepository employeeRepository;
    private EmployeeDetailsAdapter employeeDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_employee_list);
        textView = findViewById(R.id.add_data_text);
        textView.setVisibility(View.GONE);

        employeeRepository = new EmployeeRepository(getApplicationContext());
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

        updateTaskList();
    }

    private void updateTaskList() {
        employeeRepository.getTasks().observe(this, employeeDataDTOS -> {
            if (employeeDataDTOS.size() > 0) {
                textView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                if (employeeDetailsAdapter == null) {
                    employeeDetailsAdapter = new EmployeeDetailsAdapter(employeeDataDTOS);
                    recyclerView.setAdapter(employeeDetailsAdapter);

                } else employeeDetailsAdapter.addTasks(employeeDataDTOS);
            } else updateEmptyView();
        });
    }

    private void updateEmptyView() {
        textView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            Intent intent = new Intent(MainActivity.this, AddNewEmployeeActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (data.hasExtra(INTENT_TASK)) {
                if (data.hasExtra(INTENT_DELETE)) {
                    employeeRepository.deleteTask((EmployeeDataDTO) data.getSerializableExtra(INTENT_TASK));
                } else {
                    employeeRepository.updateTask((EmployeeDataDTO) data.getSerializableExtra(INTENT_TASK));
                }
            } else {
                String employeeId = data.getStringExtra(INTENT_EMPLOYEE_ID);
                String name = data.getStringExtra(INTENT_NAME);
                String designation = data.getStringExtra(INTENT_DESIGNATION);
                String assignProjects = data.getStringExtra(INTENT_ASSIGNED);
                String completedProjects = data.getStringExtra(INTENT_COMPLETED);
                String longitudeProjects = data.getStringExtra(INTENT_LONGITUDE);
                String latitudeProjects = data.getStringExtra(INTENT_LATITUDE);

                employeeRepository.insertTask(employeeId,
                        name,
                        designation,
                        assignProjects,
                        completedProjects,
                        longitudeProjects,
                        latitudeProjects);
            }
            updateTaskList();
        }
    }
}