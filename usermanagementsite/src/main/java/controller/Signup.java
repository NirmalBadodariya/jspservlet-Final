package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.AddressBean;
import model.UserBean;
import service.SignupService;

/**
 * Servlet implementation class signup
 */
@MultipartConfig(maxFileSize = 999999999)

@WebServlet("/Signup")
public class Signup extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private dao.Userdao Userdao;
    private SignupService signupService;
    Logger log = Logger.getLogger(Signup.class.getName());
    public void init() {
        Userdao = new dao.Userdao();

        signupService = new SignupService();
        BasicConfigurator.configure();

    }
  
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        Part filePart = request.getPart("image");
        String firstName = request.getParameter("firstname");
        if(firstName.equals("")){
            request.setAttribute("firstname","firstname should not be empty");
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.include(request, response);
        }
        
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String pass = request.getParameter("pass");
        InputStream image = filePart.getInputStream();
        String securityAns = request.getParameter("SecurityAns");
        
        UserBean newUser = new UserBean();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setGender(gender);
        newUser.setDob(dob);
        newUser.setPass(pass);
        newUser.setImage(image);
        newUser.setSecurityAns(securityAns);
        
        int i = 0;
        ArrayList<AddressBean> addresses = new ArrayList<>();
        while (true) {
            String ALine1 = request.getParameter("Address[" + i + "][address_line1]");
            if (ALine1 == null) {
                break;
            }
            String ALine2 = request.getParameter("Address[" + i + "][address_line2]");
            String city = request.getParameter("Address[" + i + "][city]");
            int pin = Integer.parseInt(request.getParameter("Address[" + i +
                    "][pincode]"));
            String state = request.getParameter("Address[" + i + "][state]");
            i++;
            addresses.add(new AddressBean(ALine1, ALine2, city, state, pin));
        }
        newUser.setAddresses(addresses);
        
        String id  = signupService.insertUser(newUser);
        
        if (id != null) {
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            session.setAttribute("email", newUser.getEmail());
            session.setAttribute("firstName", newUser.getFirstName());
            session.setAttribute("lastName", newUser.getLastName());
            session.setAttribute("pass", newUser.getPass());
            session.setAttribute("phone", newUser.getPhone());
            response.sendRedirect("home.jsp");
        }
    }
    
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }
}
