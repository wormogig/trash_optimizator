//package com.example;
package servlet.front;

import com.google.gson.Gson;
import servlet.front.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/rest")
public class RestServlet extends HttpServlet {

//    Model model;
    List<Model> list = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("Login");
        String password = request.getParameter("Password");
        long count = Long.parseLong(request.getParameter("Count"));
        list.add(new Model(login, password, count));

//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        list.add( new Model("a", "b", 3));
//        list.add( new Model(2, "box", 50) );
//        list.add( new Model(3, "bad", 70) );
//        String json = new Gson().toJson(list);
        String json = new Gson().toJson(list);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
