package com.example.epraghathiroomdbapplication.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.epraghathiroomdbapplication.utils.TimestampConverter;

import java.io.Serializable;
import java.util.Date;

@Entity
public class EmployeeDataDTO implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "created_at")
    @TypeConverters({TimestampConverter.class})
    private Date createdAt;

    @ColumnInfo(name = "modified_at")
    @TypeConverters({TimestampConverter.class})
    private Date modifiedAt;

    private String employeeId;
    private String name;
    private String designation;
    private String assignedProjects;
    private String completedProjects;
    private String latitude;
    private String longitude;

    public EmployeeDataDTO(Parcel in) {
        employeeId = in.readString();
        name = in.readString();
        designation = in.readString();
        assignedProjects = in.readString();
        completedProjects = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }
    

    public EmployeeDataDTO() {

    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(String assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    public String getCompletedProjects() {
        return completedProjects;
    }

    public void setCompletedProjects(String completedProjects) {
        this.completedProjects = completedProjects;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
