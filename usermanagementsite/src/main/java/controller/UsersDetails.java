package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.JsonAdapter;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import dao.Userdao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserBean;
import service.UserDetailsService;

@WebServlet("/UsersDetails")
public class UsersDetails extends HttpServlet {
    Logger log = Logger.getLogger(UsersDetails.class.getName());
    private static final long serialVersionUID = 1L;
    private UserDetailsService userDetailsService;

    public void init() {

        BasicConfigurator.configure();
        userDetailsService = new UserDetailsService();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ArrayList<UserBean> userDetails = userDetailsService.getUserDetails();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String JSONObject = gson.toJson(userDetails);
        
        response.setContentType("application/json");
        response.getWriter().print(JSONObject);
    }
}
