package com.example.epraghathiroomdbapplication.utils;


import androidx.recyclerview.widget.DiffUtil;

import com.example.epraghathiroomdbapplication.dto.EmployeeDataDTO;

import java.util.List;

public class EmployeeDiffUtil extends DiffUtil.Callback {

    List<EmployeeDataDTO> oldEmployeeList;
    List<EmployeeDataDTO> newEmployeeList;

    public EmployeeDiffUtil(List<EmployeeDataDTO> oldEmployeeList, List<EmployeeDataDTO> newEmployeeList) {
        this.oldEmployeeList = oldEmployeeList;
        this.newEmployeeList = newEmployeeList;
    }

    @Override
    public int getOldListSize() {
        return oldEmployeeList.size();
    }

    @Override
    public int getNewListSize() {
        return newEmployeeList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldEmployeeList.get(oldItemPosition).getId() == newEmployeeList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldEmployeeList.get(oldItemPosition).equals(newEmployeeList.get(newItemPosition));
    }

    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
