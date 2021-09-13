package com.example.epraghathiroomdbapplication.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.epraghathiroomdbapplication.dao.DaoAccess;
import com.example.epraghathiroomdbapplication.dto.EmployeeDataDTO;

@Database(entities = {EmployeeDataDTO.class}, version = 1, exportSchema = false)
public abstract class EmployeeDB extends RoomDatabase {

    public abstract DaoAccess daoAccess();


}
