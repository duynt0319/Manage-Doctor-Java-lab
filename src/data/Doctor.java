/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package data;

/**
 *
 * @author Duy
 */
public class Doctor extends Audit{

    private String doctorID;
    private String nameDoctor;
    private String sex;
    private String address;
    private String departmentID;


    public Doctor() {
        super();
    }

    public Doctor(String doctorID, String nameDoctor, String sex, String address, String departmentID, String createDate, String lastUpdateDate) {
        super(createDate, lastUpdateDate);
        this.doctorID = doctorID;
        this.nameDoctor = nameDoctor;
        this.sex = sex;
        this.address = address;
        this.departmentID = departmentID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    @Override
    public String printOut() {
        return doctorID + " | " + nameDoctor + " | " + sex + " | " + address + " | " + departmentID + " | " + super.getCreateDate() + " | " + super.getLastUpdateDate()+ " | ";
    }

}
