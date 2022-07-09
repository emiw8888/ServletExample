package com.example.repository;

import java.util.Date;

public class DepEntity {
    private int departmentId;
    private final String depName;
    private final Date createDate;
    private final boolean status;

    public DepEntity(int departmentId, String depName, Date createDate, boolean status) {
        this.departmentId = departmentId;
        this.depName = depName;
        this.createDate = createDate;
        this.status = status;
    }

    public DepEntity(String depName, Date createDate, boolean status) {
        this.depName = depName;
        this.createDate = createDate;
        this.status = status;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepName() {
        return depName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public boolean isStatus() {
        return status;
    }
}
