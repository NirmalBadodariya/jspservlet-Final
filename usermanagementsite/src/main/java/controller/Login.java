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
import dao.Userdao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Userdao Userdao;

    public void init() {
        Userdao = new Userdao();
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
        // HttpSession session = request.getSession();

        boolean check = Userdao.checkUser(newUser);

        
        System.out.println("checkincncccccccccc:      " + check);
        if (check == true) {
            response.sendRedirect("home.jsp");

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
