package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

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
import service.UserDataManipultionService;

/**
 * Servlet implementation class signup
 */
@MultipartConfig(maxFileSize = 999999999)

@WebServlet("/Signup")
public class Signup extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private dao.Userdao Userdao;
    private SignupService signupService;
    private UserDataManipultionService UserDataManipultionService;

   static final Logger log = Logger.getLogger(Signup.class.getName());

    public void init() {
        Userdao = new dao.Userdao();

        signupService = new SignupService();
        UserDataManipultionService = new UserDataManipultionService();
        BasicConfigurator.configure();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("image");
        String firstName = request.getParameter("firstname");
        // if (firstName.equals("")) {
        // errors.add("FIRSTNAME");
        // }
        // if(errors!=null){
        // request.setAttribute("errors", errors);
        // }
        int hiddenId = 0;
        try {

            hiddenId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            log.error("Exc:"+e);
        }
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String pass = request.getParameter("pass");
        InputStream image = filePart.getInputStream();
        String securityAns = request.getParameter("SecurityAns");
        ArrayList<AddressBean> addresses = new ArrayList<>();
        ArrayList<AddressBean> newAddresses = new ArrayList<>();
        List<Integer> currentUserAddressIdList = new ArrayList<>();

        HttpSession session = request.getSession();

        ArrayList<AddressBean> oldAddresses = (ArrayList<AddressBean>) session.getAttribute("UserOldAddresses");
        int i = 0;

        while (true) {
            int addressId = -1;
            String ALine1 = request.getParameter("Address[" + i + "][address_line1]");
            if (ALine1 == null) {
                break;
            }

            String ALine2 = request.getParameter("Address[" + i + "][address_line2]");
            String city = request.getParameter("Address[" + i + "][city]");
            int pin = Integer.parseInt(request.getParameter("Address[" + i +
                    "][pincode]"));
            String state = request.getParameter("Address[" + i + "][state]");

            String temp = "";
            if (request.getParameter("Address[" + i + "][address_id]") != null) {

                temp = request.getParameter("Address[" + i + "][address_id]");
            }

            try {
                addressId = Integer.parseInt(temp);

                currentUserAddressIdList.add(addressId);

            } catch (Exception e) {
                log.error("Exc:"+e);
            }
            System.out.println(addressId);
            if (addressId == -1) {
                newAddresses.add(new AddressBean(addressId, ALine1, ALine2, city, state, pin));

            } else {

                for (AddressBean obj : oldAddresses) {
                    if (obj.getAddressId() != addressId || !(obj.getAddressLine1().equals(ALine1))
                    || !(obj.getAddressLine2().equals(ALine2)) || !(obj.getCity().equals(city))
                    || !(obj.getState().equals(state)) || !(obj.getPincode() == pin)) {
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
            // setting data of newuser in database
            newUser.setAddresses(newAddresses);
            String id = signupService.insertUser(newUser);
            log.info("newUser Details");

            if (id != null) {
                // setting email for getting data while update
                String FromAdmin = (String) session.getAttribute("fromAdmin");
                if(FromAdmin!=null){
                    
                    response.sendRedirect("adminHome1.jsp");
                }
                else{
                    session.setAttribute("email", newUser.getEmail());
                    response.sendRedirect("home.jsp");
                }
            }
        } else {
            session.setAttribute("email", newUser.getEmail());
            // if its not nwe user it comes here
            List<Integer> addressIdList = new ArrayList<>();

            // updating user's Basic data to db
            UserDataManipultionService.updateNewUserDetails(newUser);

            // adding new addresses to db
            UserDataManipultionService.addnewAddress(newAddresses, hiddenId);

            // updating addresses to db
            UserDataManipultionService.updateaddress(addresses, hiddenId);
            // Deleted ids from user addresses
            for (AddressBean obj : oldAddresses) {
                addressIdList.add(obj.getAddressId());
            }

            // removing deleted addresses from db
            addressIdList.removeAll(currentUserAddressIdList);
            for (int t = 0; t < addressIdList.size(); t++) {
                System.out.println(addressIdList.get(t));
            }

            Userdao.removeAddresses(addressIdList);
            // who is editing
            String FromAdmin = (String) session.getAttribute("fromAdmin");
            if (FromAdmin != null) {
                response.sendRedirect("adminHome1.jsp");
            } else {
                response.sendRedirect("home.jsp");
            }
        }

    }

}
