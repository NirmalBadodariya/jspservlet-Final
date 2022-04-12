package controller;

import java.io.IOException;
import java.io.Serializable;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ForgotPassBean;
import service.ChangePassService;
@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private transient ChangePassService ChangePassService;
    Logger log = Logger.getLogger(ChangePass.class.getName());
    public void init() {
        ChangePassService = new ChangePassService();
        BasicConfigurator.configure();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ForgotPassBean forgotPass = new ForgotPassBean();
        String newPass = request.getParameter("newPass");
        HttpSession session = request.getSession();
       String dob = (String) session.getAttribute("dob");
       String securityAns = (String) session.getAttribute("securityAns");
        forgotPass.setNewPass(newPass);
        forgotPass.setDob(dob);
        forgotPass.setSecurityAns(securityAns);
                
        ChangePassService.setNewPass(forgotPass);
        // Userdao.setNewPass(forgotPass);

        response.sendRedirect("index.jsp");

    }
}
