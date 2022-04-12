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
import service.ForgotPassService;

@WebServlet("/ForgotPass")
public class ForgotPass extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ForgotPassService ForgotPassService;
  Logger log = Logger.getLogger(ForgotPass.class.getName());
    public void init() {
        ForgotPassService = new ForgotPassService();
        BasicConfigurator.configure();
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
        String dob = request.getParameter("dob");
        String securityAns = request.getParameter("SecurityAns");
                
        UserBean checkForgotpassDetails = new UserBean();
        checkForgotpassDetails.setDob(dob);
        checkForgotpassDetails.setSecurityAns(securityAns);

        boolean check = ForgotPassService.checkUserDetailsForForgotPass(checkForgotpassDetails);
                
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
