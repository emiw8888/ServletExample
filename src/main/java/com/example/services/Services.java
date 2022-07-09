package com.example.services;

import com.example.repository.DepEntity;
import com.example.repository.EmpEntity;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Services {
    Connection c;
    private java.util.Date date;

    public Services(Connection c) {
        this.c = c;
    }

    public List<DepEntity> getAllActiveDepartments() throws SQLException {
        List<DepEntity> depEntities = new ArrayList<>();
        Statement stmt = c.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * from department where status = true");
        while (resultSet.next()) {
           depEntities.add(new DepEntity(resultSet.getString("department_name"),
                   resultSet.getDate("create_date"),
                   resultSet.getBoolean("status")));
        }
        return depEntities;
    }

    public List<EmpEntity> getAllActiveEmployersByDeparmentId(int id) throws SQLException {
        List<EmpEntity> emp = new ArrayList<>();
        PreparedStatement stmt = c.prepareStatement("SELECT * from employer where status = true and department_id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            emp.add(new EmpEntity(resultSet.getInt("id"),
                    resultSet.getString("emp_name"),
                    resultSet.getString("emp_surname"),
                    resultSet.getLong("phone"),
                    resultSet.getInt("salary"),
                    resultSet.getString("create_date"),
                    resultSet.getBoolean("status"),
                    resultSet.getInt("department_id")));
        }
        return emp;
    }

    public void updateEmployerById(EmpEntity emp, int id) throws SQLException, ParseException {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        PreparedStatement stmt = c.prepareStatement("update employer set emp_name = ?," +
                "emp_surname = ?,phone = ?,salary = ?,create_date = ?,status = ?,department_id = ?" +
                "where id = ?");
        stmt.setString(1, emp.getName());
        stmt.setString(2, emp.getSurname());
        stmt.setLong(3, emp.getPhone());
        stmt.setInt(4, emp.getSalary());
        date = myFormat.parse(emp.getCreateDate());
        stmt.setDate(5, new Date(date.getTime()));
        stmt.setBoolean(6, emp.isStatus());
        stmt.setInt(7, emp.getDeparmentId());
        stmt.setInt(8,id);
        stmt.executeUpdate();
        System.out.println("updated succesfully");

    }

    public Map<String, Integer> averageSalaryForEachDepartment() throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("select avg(e.salary) average,d.department_name departmentName from employer e " +
                "join department d" +
                " on e.department_id = d.id " +
                "group by d.department_name  ");
        while (rs.next()) {
            map.put(rs.getString("departmentName"), rs.getInt("average"));
        }
        return map;
    }

//    public void insertEmployee(List<EmpEntity> e) throws SQLException {
//        PreparedStatement stmt = null;
//        for (EmpEntity emp : e) {
//            stmt = c.prepareStatement("insert into employer " +
//                    "(emp_name,emp_surname,phone,salary,create_date,status,department_id) values (?,?,?,?,?,?,?);");
//            stmt.setString(1, emp.getName());
//            stmt.setString(2, emp.getSurname());
//            stmt.setLong(3, emp.getPhone());
//            stmt.setInt(4, emp.getSalary());
//            stmt.setDate(5, (Date) emp.getCreateDate());
//            stmt.setBoolean(6, emp.isStatus());
//            stmt.setInt(7, emp.getDeparmentId());
//            stmt.executeUpdate();
//        }
//        System.out.println("inserted succesfully");
//    }

    public void updateDepartmentByDepartmentName(DepEntity dep , String departmentName) throws SQLException {
        PreparedStatement stmt = c.prepareStatement("update department set department_name = ?," +
                "create_date = ?,status = ? where department_name = ?");
        stmt.setString(1, dep.getDepName());
        stmt.setDate(2, (Date) dep.getCreateDate());
        stmt.setBoolean(3, dep.isStatus());
        stmt.setString(4, departmentName);
        stmt.executeUpdate();

        System.out.println("updated succesfully");
    }
}
