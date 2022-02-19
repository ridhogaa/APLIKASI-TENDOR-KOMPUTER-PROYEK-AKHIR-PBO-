/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LoginRegister;

/**
 *
 * @author Ridho Gymnastiar Al Rasyid
 */
public interface DataUser {

    void setUsername(String username);

    void setFullname(String fullname);

    void setEmail(String email);

    void setPhoneNumber(String phonenumber);

    void setPassword(String password);

    String getUsername();

    String getFullname();

    String getEmail();

    String getPhonenumber();

    String getPassword();
}
