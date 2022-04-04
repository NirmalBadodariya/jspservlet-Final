package controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import dao.Userdao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AddressBean;
import model.UserBean;
import service.SignupService;

@WebServlet("/EditDetails")
public class EditDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private dao.Userdao Userdao;
    private SignupService signupService;
    Logger log = Logger.getLogger(Userdao.class.getName());
    public void init() {
        Userdao = new dao.Userdao();
        BasicConfigurator.configure();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            

            UserBean detailsOfUsers = Userdao.getUserDetails(email);
            ArrayList<AddressBean> addresses = detailsOfUsers.getAddresses();
            request.setAttribute("detailsofUser", detailsOfUsers);
            request.setAttribute("addresses", addresses);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            log.error("Exc:" + e);
        }
    }
}