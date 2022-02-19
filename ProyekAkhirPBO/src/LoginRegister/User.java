/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginRegister;

/**
 *
 * @author Ridho Gymnastiar Al Rasyidser
 */
public class User implements DataUser {

    private String fullname, username, email, phonenumber, password;
    private int id;

    public User(String fullname, String username, String email, String phonenumber, String password) {
        this.setFullname(fullname);
        this.setUsername(username);
        this.setEmail(email);
        this.setPhoneNumber(phonenumber);
        this.setPassword(password);
    }

    public User(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    @Override
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPhoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getFullname() {
        return fullname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhonenumber() {
        return phonenumber;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
