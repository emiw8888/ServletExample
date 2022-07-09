package com.example.servletexample;

import com.example.connection.ConnectionToDb;
import com.example.repository.EmpEntity;
import com.example.services.Services;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/updateEmployer")
public class UpdateEmployer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String readLine = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd")
                .create();
        EmpEntity emp = gson.fromJson(readLine,EmpEntity.class);
        resp.setContentType("application/JSON");
        resp.setStatus(200);
        Connection connection = ConnectionToDb.connect();
        Services services = new Services(connection);
        try (connection){
            services.updateEmployerById(emp,emp.getId());
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
    }
}
