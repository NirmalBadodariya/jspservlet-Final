package controller;

import java.io.IOException;

import java.io.PrintWriter;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserBean;
import service.LoginService;
import dao.Userdao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    Logger log = Logger.getLogger(Login.class.getName());
    private static final long serialVersionUID = 1L;
    private Userdao Userdao;
    private LoginService loginService;

    public void init() {

        BasicConfigurator.configure();
        loginService = new LoginService();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        
        String pass = request.getParameter("pass");

        UserBean newUser = new UserBean();
        newUser.setEmail(email);
        newUser.setPass(pass);
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
                
        String usertype = loginService.checkUser(newUser);
                
        if (usertype == "User") {
            response.sendRedirect("home.jsp");

        } else if (usertype == "Admin") {
            response.sendRedirect("adminHome.jsp");
        } else {
            request.setAttribute("error","Invalid email or Password");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        }
        
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }
}
