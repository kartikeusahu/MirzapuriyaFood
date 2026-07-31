package com.example.mirzapuriyafood.Models;

public class Users {
    private String profilepic;
    private String userName;
    private String mail;
    private String password;
    private String userID;
    private String mobileNo;

    // Default constructor (required for Firebase)
    public Users() {}

    // Constructor for SignUp
    public Users(String userName, String mail, String password, String mobileNo) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.mobileNo = mobileNo;
    }

    // Constructor with profile pic and userID
    public Users(String profilepic, String userName, String mail, String password, String userID) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userID = userID;
    }

    // Getters and Setters
    public String getProfilepic() { return profilepic; }
    public void setProfilepic(String profilepic) { this.profilepic = profilepic; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }
}