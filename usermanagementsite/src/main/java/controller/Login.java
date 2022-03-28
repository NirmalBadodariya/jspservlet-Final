package controller;

import java.io.IOException;

import java.io.PrintWriter;

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
    private static final long serialVersionUID = 1L;
    private Userdao Userdao;
    private LoginService loginService;

    public void init() {
        Userdao = new dao.Userdao();

        loginService = new LoginService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        UserBean newUser = new UserBean();
        newUser.setEmail(email);
        newUser.setPass(pass);
        HttpSession session = request.getSession();
        session.setAttribute("email", email);

        // boolean check = Userdao.checkUser(newUser);
        int usertype = loginService.checkUser(newUser);
        System.out.println("usertyoppe" + usertype);
        if (usertype == 1) {
            response.sendRedirect("home.jsp");

        } else if (usertype == 2) {
            response.sendRedirect("adminHome.jsp");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
            out.print("Not A Valid Input");
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }
}
