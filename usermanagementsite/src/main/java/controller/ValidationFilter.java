package controller;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/Signup")
public class ValidationFilter implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws java.io.IOException, ServletException {

                String erpg = "register.jsp";
                String firstName = request.getParameter("firstname");
                String lastName = request.getParameter("lastname");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                // String gender = request.getParameter("gender");
                String dob = request.getParameter("dob");
                String pass = request.getParameter("pass");
                String securityAns = request.getParameter("SecurityAns");
            
        if (firstName == null || firstName.equals("") ||lastName == null || lastName.equals("") ||email == null || email.equals("")||phone == null || phone.equals("")||pass == null || pass.equals("")||dob == null || dob.equals("") ||securityAns == null || securityAns.equals("") ) {
            System.out.println("BackEnd validation check");
            request.setAttribute("errMsg " ,"No Field should be empty");
            
            RequestDispatcher rd = request.getRequestDispatcher(erpg);
            rd.include(request, response);

        } 
         else {
            chain.doFilter(request, response); 
        }
     }
}
