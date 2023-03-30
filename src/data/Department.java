/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author Duy
 */
public class Department extends Audit {

    private String departmentID;
    private String nameDepartment;

    public Department() {
        super();
    }

    public Department(String departmentID, String nameDepartment, String createDate, String lastUpdateDate) {
        super(createDate, lastUpdateDate);
        this.departmentID = departmentID;
        this.nameDepartment = nameDepartment;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    @Override
    public String printOut() {
        return departmentID + " | " + nameDepartment + " | " + super.getCreateDate() + " | " + super.getLastUpdateDate() + " | ";
    }

}
