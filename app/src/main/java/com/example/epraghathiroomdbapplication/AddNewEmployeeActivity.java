package com.example.epraghathiroomdbapplication;

import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_ASSIGNED;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_COMPLETED;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_DESIGNATION;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_EMPLOYEE_ID;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_LATITUDE;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_LONGITUDE;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_NAME;
import static com.example.epraghathiroomdbapplication.AppConstants.INTENT_TASK;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.epraghathiroomdbapplication.dto.EmployeeDataDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class AddNewEmployeeActivity extends AppCompatActivity implements View.OnClickListener {

    public TextInputEditText id, name, designation, longitude, latitude, assignedTasks, completedTasks;
    public MaterialButton buttonSubmit;
    private EmployeeDataDTO employeeDataDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_employee);
        id = findViewById(R.id.employee_id_ev);
        name = findViewById(R.id.employee_name_ev);
        designation = findViewById(R.id.employee_designation_ev);
        longitude = findViewById(R.id.longitude_ev);
        latitude = findViewById(R.id.latitude_ev);
        assignedTasks = findViewById(R.id.projects_assigned_ev);
        completedTasks = findViewById(R.id.projects_completed_ev);

        buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(this);

        employeeDataDTO = (EmployeeDataDTO) getIntent().getSerializableExtra(INTENT_TASK);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_submit) {
            Intent intent = new Intent();
            if (employeeDataDTO != null) {
                employeeDataDTO.setEmployeeId(id.getText().toString());
                employeeDataDTO.setName(name.getText().toString());
                employeeDataDTO.setDesignation(designation.getText().toString());
                employeeDataDTO.setLongitude(longitude.getText().toString());
                employeeDataDTO.setLongitude(latitude.getText().toString());
                employeeDataDTO.setLongitude(assignedTasks.getText().toString());
                employeeDataDTO.setLongitude(completedTasks.getText().toString());
                intent.putExtra(INTENT_TASK, employeeDataDTO);

            } else {
                intent.putExtra(INTENT_EMPLOYEE_ID, id.getText().toString());
                intent.putExtra(INTENT_NAME, name.getText().toString());
                intent.putExtra(INTENT_DESIGNATION, designation.getText());
                intent.putExtra(INTENT_ASSIGNED, longitude.getText().toString());
                intent.putExtra(INTENT_COMPLETED, latitude.getText().toString());
                intent.putExtra(INTENT_LONGITUDE, assignedTasks.getText().toString());
                intent.putExtra(INTENT_LATITUDE, completedTasks.getText().toString());
            }
            AlertDialog.Builder builderSaved = new AlertDialog.Builder(view.getContext(), R.style.MaterialAlertDialogStyle);
            builderSaved.setTitle("Status");
            builderSaved.setMessage("Details Successfully Saved");
            builderSaved.setPositiveButton("OK", (dialogInterface, i) -> {
          //      Objects.requireNonNull(getApplicationContext()).onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                setResult(1, intent);
                finish();
                getFragmentManager().popBackStack();

            });
            builderSaved.show();
            builderSaved.create();




        }
    }

}