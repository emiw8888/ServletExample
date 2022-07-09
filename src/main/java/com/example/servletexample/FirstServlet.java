package com.example.servletexample;

import com.example.connection.ConnectionToDb;
import com.example.repository.DepEntity;
import com.example.services.Services;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/JSON");
        resp.setStatus(200);
        Gson gson  = new Gson();
        Connection connection =ConnectionToDb.connect();
        Services services = new Services(connection);
        try (connection){
            for(DepEntity entity : services.getAllActiveDepartments()){
                resp.getOutputStream().print(gson.toJson(entity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
