public abstract class Staff {
    private String id;
    private String name;
    private int age;
    private double coefficientSalary;
    private String startDate;
    private Department department;
    private int numDaysOff;

    public Staff(String id, String name, int age, double coefficientSalary, String startDate, Department department, int numDaysOff) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.coefficientSalary = coefficientSalary;
        this.startDate = startDate;
        this.department = department;
        this.numDaysOff = numDaysOff;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getCoefficientSalary() {
        return coefficientSalary;
    }

    public String getStartDate() {
        return startDate;
    }

    public Department getDepartment() {
        return department;
    }

    public String getDepartmentName() {
        return department.getDepartmentName();
    }

    public int getNumDaysOff() {
        return numDaysOff;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCoefficientSalary(double coefficientSalary) {
        this.coefficientSalary = coefficientSalary;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setNumDaysOff(int numDaysOff) {
        this.numDaysOff = numDaysOff;
    }
    public abstract void displayInformation();
    public abstract void displaySalary();
}