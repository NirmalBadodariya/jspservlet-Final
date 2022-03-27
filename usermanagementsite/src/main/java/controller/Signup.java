package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.UserBean;

/**
 * Servlet implementation class signup
 */
@MultipartConfig(maxFileSize = 999999999)

@WebServlet("/Signup")
public class Signup extends HttpServlet {
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

        Part filePart = request.getPart("image");
                
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String pass = request.getParameter("pass");
        InputStream image = filePart.getInputStream();
        String securityAns = request.getParameter("SecurityAns");
                
        String ALine1 = request.getParameter("ALine1");
        String ALine2 = request.getParameter("ALine2");
        String city = request.getParameter("city");
        int pin = Integer.parseInt(request.getParameter("pin"));
        String state = request.getParameter("state");

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
        newUser.setALine1(ALine1);
        newUser.setALine2(ALine2);
        newUser.setCity(city);
        newUser.setPin(pin);
        newUser.setState(state);
        Userdao.insertUser(newUser);

                
        HttpSession session = request.getSession();
        session.setAttribute("id", newUser.getId() + "");
        session.setAttribute("email", newUser.getEmail());
        session.setAttribute("firstName", newUser.getFirstName());
        session.setAttribute("lastName", newUser.getLastName());
        session.setAttribute("pass", newUser.getPass());
        session.setAttribute("phone", newUser.getPhone());
        response.sendRedirect("home.jsp");
    }
        
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }
}
