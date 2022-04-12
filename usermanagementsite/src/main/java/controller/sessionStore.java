package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SessionStore")
public class SessionStore  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String fromAdmin = "FromAdmin";
        
       HttpSession session = request.getSession(); 
       session.setAttribute("fromAdmin", fromAdmin);
       response.sendRedirect("register.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String email =  request.getParameter("email");
        System.out.println("in session store"+email);
        String fromAdmin = "FromAdmin";
        
       HttpSession session = request.getSession(); 
       session.setAttribute("email",email);
       session.setAttribute("fromAdmin", fromAdmin);
       response.sendRedirect("register.jsp");
       
    }
}
