package com.example.epraghathiroomdbapplication.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.epraghathiroomdbapplication.db.EmployeeDB;
import com.example.epraghathiroomdbapplication.dto.EmployeeDataDTO;

import java.io.StringReader;
import java.util.List;

public class EmployeeRepository {

    private String DB_NAME = "db_employee";

    private EmployeeDB employeeDB;

    public EmployeeRepository(Context context) {
        employeeDB = Room.databaseBuilder(context, EmployeeDB.class, DB_NAME).build();
    }

    public void insertTask(String employeeId, String name, String designation, String assignProjects,
                           String completedProjects, String longitudeProjects, String latitudeProjects) {


        insertTaskN(employeeId, name, designation, assignProjects, completedProjects, longitudeProjects, latitudeProjects);

    }

    public void insertTaskN(String employeeId, String name, String designation, String assignProjects,
                            String completedProjects, String longitudeProjects, String latitudeProjects) {

        EmployeeDataDTO employeeDetails = new EmployeeDataDTO();
        employeeDetails.setEmployeeId(employeeId);
        employeeDetails.setName(name);
        employeeDetails.setDesignation(designation);
        employeeDetails.setAssignedProjects(assignProjects);
        employeeDetails.setCompletedProjects(completedProjects);
        employeeDetails.setLongitude(longitudeProjects);
        employeeDetails.setLatitude(latitudeProjects);

        insertTask(employeeDetails);
    }

    public void insertTask(final EmployeeDataDTO employeeDataDTO) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                employeeDB.daoAccess().insertTask(employeeDataDTO);
                return null;
            }
        }.execute();
    }

    public void updateTask(final EmployeeDataDTO note) {


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                employeeDB.daoAccess().updateTask(note);
                return null;
            }
        }.execute();
    }

    public void deleteTask(final int id) {
        final LiveData<EmployeeDataDTO> task = getTask(id);
        if (task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    employeeDB.daoAccess().deleteTask(task.getValue());
                    return null;
                }
            }.execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteTask(final EmployeeDataDTO employeeDataDTO) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                employeeDB.daoAccess().deleteTask(employeeDataDTO);
                return null;
            }
        }.execute();
    }

    public LiveData<EmployeeDataDTO> getTask(int id) {
        return employeeDB.daoAccess().getTask(id);
    }

    public LiveData<List<EmployeeDataDTO>> getTasks() {
        return employeeDB.daoAccess().fetchAllTasks();
    }


}
