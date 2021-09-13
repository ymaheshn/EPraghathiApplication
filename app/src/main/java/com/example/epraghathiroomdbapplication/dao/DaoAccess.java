package com.example.epraghathiroomdbapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.epraghathiroomdbapplication.dto.EmployeeDataDTO;

import java.util.List;

@Dao
public interface DaoAccess {


    @Insert
    Long insertTask(EmployeeDataDTO dataDTO);


    @Query("SELECT * FROM EmployeeDataDTO ORDER BY created_at desc")
    LiveData<List<EmployeeDataDTO>> fetchAllTasks();


    @Query("SELECT * FROM EmployeeDataDTO WHERE id =:taskId")
    LiveData<EmployeeDataDTO> getTask(int taskId);


    @Update
    void updateTask(EmployeeDataDTO dataDTO);


    @Delete
    void deleteTask(EmployeeDataDTO dataDTO);
}
