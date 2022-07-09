package com.example.repository;

import java.util.Date;

public class EmpEntity {
    private int id;
    private String name;
    private String surname;
    private long phone;
    private int salary;
//    @SerializedName("createDate")
    private String  createdate;
    private boolean status;
    private int departament;

    public EmpEntity(int id, String name, String surname, long phone, int salary, String createdate, boolean status, int deparmentId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.salary = salary;
        this.createdate = createdate;
        this.status = status;
        this.departament = deparmentId;
    }

    public EmpEntity(String name, String surname, long phone, int salary, String createdate, boolean status, int deparmentId) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.salary = salary;
        this.createdate = createdate;
        this.status = status;
        this.departament = deparmentId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public long getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    public String getCreateDate() {
        return createdate;
    }

    public boolean isStatus() {
        return status;
    }

    public int getDeparmentId() {
        return departament;
    }
}
