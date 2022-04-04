package controller;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserDetailsService;

@WebServlet("/EditUserDetails")
public class EditUserDetails extends HttpServlet {
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

            }

        }
