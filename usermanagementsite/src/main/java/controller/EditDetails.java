package controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AddressBean;
import model.UserBean;
import service.GetUserDetails;
@WebServlet("/EditDetails")
public class EditDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GetUserDetails GetUserDetails;
    Logger log = Logger.getLogger(EditDetails.class.getName());
    public void init() {
        GetUserDetails = new GetUserDetails();
        
        BasicConfigurator.configure();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            System.out.println("hereinedit");
            HttpSession session = request.getSession();
            UserBean detailsOfUsers;
            ArrayList<AddressBean> addresses;
                
            String email = (String) session.getAttribute("email");
            
            System.out.println("mail: "+ email);
            
            detailsOfUsers = GetUserDetails.getUserDetails(email);
            // detailsOfUsers = Userdao.getUserDetails(email);
            addresses = detailsOfUsers.getAddresses();
             
            request.setAttribute("detailsofUser", detailsOfUsers);
            request.setAttribute("addresses", addresses);
            session.setAttribute("UserOldAddresses", addresses);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            log.error("Exc:" + e);
        }
    }
}
