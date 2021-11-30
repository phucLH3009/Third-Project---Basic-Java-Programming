public class Department {
    private String departmentID;
    private String departmentName;
    private int numOfStaffs;

    public Department(String departmentID, String departmentName, int numOfStaffs) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.numOfStaffs = numOfStaffs;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getNumOfStaffs() {
        return numOfStaffs;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setNumOfStaffs(int numOfStaffs) {
        this.numOfStaffs = numOfStaffs;
    }

    public String toString() {
        return "Department ID: " + getDepartmentID() + "\n" + "Department: " + getDepartmentName() + "\n" + "Number of Staffs: " + getNumOfStaffs();
    }
}