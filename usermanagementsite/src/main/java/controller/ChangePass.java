package controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import dao.Userdao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ForgotPassBean;

@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private dao.Userdao Userdao;
    Logger log = Logger.getLogger(Userdao.class.getName());
    public void init() {
        Userdao = new dao.Userdao();
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

        ForgotPassBean forgotPass = new ForgotPassBean();
        String newPass = request.getParameter("newPass");
        HttpSession session = request.getSession();
       String dob = (String) session.getAttribute("dob");
       String securityAns = (String) session.getAttribute("securityAns");
        forgotPass.setNewPass(newPass);
        forgotPass.setDob(dob);
        forgotPass.setSecurityAns(securityAns);
                
        Userdao.setNewPass(forgotPass);

        response.sendRedirect("index.jsp");

    }
}
