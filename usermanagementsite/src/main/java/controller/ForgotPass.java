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

@WebServlet("/ForgotPass")
public class ForgotPass extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private dao.Userdao Userdao;

    public void init() {
        Userdao = new dao.Userdao();
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
        System.out.println("in controller");
        String dob = request.getParameter("dob");
        String securityAns = request.getParameter("SecurityAns");
                                                                    
        UserBean checkForgotpassDetails = new UserBean();
        checkForgotpassDetails.setDob(dob);
        checkForgotpassDetails.setSecurityAns(securityAns);

        boolean check = Userdao.checkUserDetailsForForgotPass(checkForgotpassDetails);
                
        if (check == true) {
            HttpSession session = request.getSession();
            session.setAttribute("dob", dob);
            session.setAttribute("securityAns", securityAns);
            response.sendRedirect("changePass.jsp");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("forgotpass.jsp");
            rd.include(request, response);
            out.print("not valid data");
        }

    }
}
