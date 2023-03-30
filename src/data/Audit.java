/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author Duy
 */
public class Audit {
    private String createDate;
    private String lastUpdateDate;

    public Audit(String createDate, String lastUpdateDate) {
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Audit(){}

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String printOut() {
        return "Audit{" + "createDate=" + createDate + ", lastUpdateDate=" + lastUpdateDate + '}';
    }
    
    
}
