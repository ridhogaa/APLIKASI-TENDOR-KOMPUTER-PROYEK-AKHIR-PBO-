/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginRegister;

/**
 *
 * @author Ridho Gymnastiar Al Rasyid
 */
public class Membership extends User{
    private String status;
    private int points;
    public Membership(int id, String status, int points) {
        super(id);
        setStatus(status);
        setPoints(points);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public String getStatus(){
        return status;
    }

    public int getPoints() {
        return points;
    }
    
}
