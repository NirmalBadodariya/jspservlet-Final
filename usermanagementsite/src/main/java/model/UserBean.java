package model;

import java.io.InputStream;

public class UserBean {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String dob;
    private String pass;
    private String SecurityAns;
    private InputStream image;
    private String ALine1;
    private String ALine2;

    public String getALine1() {
        return ALine1;
    }

    public void setALine1(String aLine1) {
        ALine1 = aLine1;
    }

    public String getALine2() {
        return ALine2;
    }

    public void setALine2(String aLine2) {
        ALine2 = aLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String city;
    private int pin;
    private String state;

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getSecurityAns() {
        return SecurityAns;
    }

    public void setSecurityAns(String securityAns) {
        SecurityAns = securityAns;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    private String security_ans;
    private String user_profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSecurity_ans() {
        return security_ans;
    }

    public void setSecurity_ans(String security_ans) {
        this.security_ans = security_ans;
    }

    public String getUser_profile() {
        return user_profile;
    }

    public void setUser_profile(String user_profile) {
        this.user_profile = user_profile;
    }

}
