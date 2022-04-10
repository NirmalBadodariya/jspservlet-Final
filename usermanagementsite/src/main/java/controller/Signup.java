package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
                ArrayList<String> errors = new ArrayList<>();
        Part filePart = request.getPart("image");
        String firstName = request.getParameter("firstname");
        if (firstName.equals("")) {
        errors.add("FIRSTNAME");
        }
        if(errors!=null){
            request.setAttribute("errors", errors);
        }
        int hiddenId=0;
        try {
            
            hiddenId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            //TODO: handle exception
        }
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String pass = request.getParameter("pass");
        InputStream image = filePart.getInputStream();
        String securityAns = request.getParameter("SecurityAns");
            
        int i = 0;
        ArrayList<AddressBean> addresses = new ArrayList<>();
        ArrayList<AddressBean> newAddresses = new ArrayList<>();
        List<Integer> currentUserAddressIdList = new ArrayList<>();

        HttpSession session = request.getSession();
        // for(int j =0;j<addressIdList.size();j++){
        // System.out.println("List: "+addressIdList.get(j));
        // }
        ArrayList<AddressBean> oldAddresses =  (ArrayList<AddressBean>) session.getAttribute("UserOldAddresses");  
        for(AddressBean obj : oldAddresses)
            { System.out.println(obj.getAddressLine1()); }    
        int addressId = -1;
        while (true) {
            String ALine1 = request.getParameter("Address[" + i + "][address_line1]");
            if (ALine1 == null) {
                break;
            }

            // System.out.println(request.getParameter(("Address[" + i +"][address_id]")).equals(""));
            
            String ALine2 = request.getParameter("Address[" + i + "][address_line2]");
            String city = request.getParameter("Address[" + i + "][city]");
            int pin = Integer.parseInt(request.getParameter("Address[" + i +
            "][pincode]"));
            String state = request.getParameter("Address[" + i + "][state]");

            
            // String temp = "";
            // if(request.getParameter("Address[" + i + "][address_id]") != null){
                
            //     temp = request.getParameter("Address[" + i + "][address_id]");
            // }
            // if(temp!=null){
            //     addressId = Integer.parseInt(request.getParameter("Address[" + i + "][address_id]"));
            //     currentUserAddressIdList.add(addressId);
            // }
            
            if(request.getParameter("Address[" + i + "][address_id]")!=null){
            // if (request.getParameter("Address[" + i + "][address_id]")!=null && !request.getParameter("Address[" + i + "][address_id]").isEmpty()) {
                System.out.println("temp not null");
                  currentUserAddressIdList.add(addressId);

            }
            else{
                System.out.println("came in else");
                addressId=-1;
            }

            if(addressId==-1){
                newAddresses.add(new AddressBean(addressId, ALine1, ALine2, city, state, pin));
                System.out.println("came if -1");
            }
            else{
                for(AddressBean obj : oldAddresses)
                    { 
                        System.out.println("session: aline"+obj.getAddressLine1());
                        System.out.println("legit: aline"+ALine1);
                        if(obj.getAddressLine1()!=ALine1){
                            addresses.add(new AddressBean(addressId, ALine1, ALine2, city, state, pin));
                        }
                    }   
            }
            i++;
        }        



        UserBean newUser = new UserBean();
        newUser.setId(hiddenId);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setGender(gender);
        newUser.setDob(dob);
        newUser.setPass(pass);
        newUser.setImage(image);
        newUser.setSecurityAns(securityAns);

        if (hiddenId == 0) {
            newUser.setAddresses(newAddresses);
            String id = signupService.insertUser(newUser);
            System.out.println("newuser deails");
            response.sendRedirect("home.jsp");
                       
            // if (id != null) {
            //     HttpSession session = request.getSession();
            //     session.setAttribute("id", id);
            //     session.setAttribute("email", newUser.getEmail());
            //     session.setAttribute("firstName", newUser.getFirstName());
            //     session.setAttribute("lastName", newUser.getLastName());
            //     session.setAttribute("pass", newUser.getPass());
            //     session.setAttribute("phone", newUser.getPhone());
            //     response.sendRedirect("home.jsp");
            // }
        } else {
        List<Integer> addressIdList = Userdao.getAddressId(hiddenId);
        
        Userdao.UpdateNewUserDetails(newUser);
        // adding new addresses to db
        Userdao.addnewAddress(newAddresses,hiddenId);
        // updating addresses to db
      Userdao.updateaddress(addresses,hiddenId);

        // Deleted ids from user addresses  
            
        // removing deleted addresses from db
        addressIdList.removeAll(currentUserAddressIdList);
        Userdao.removeAddresses(addressIdList);
            response.sendRedirect("home.jsp");
        }
    }
        
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }
}
