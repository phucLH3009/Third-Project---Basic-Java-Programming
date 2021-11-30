public class Employee extends Staff implements ICalculator {
    private int overtimeHours;

    public Employee(String id, String name, int age, double coefficientSalary, String startDate, Department department, int numDaysOff, int overtimeHours) {
        super(id, name, age, coefficientSalary, startDate, department, numDaysOff);
        this.overtimeHours = overtimeHours;
    }
    public int getOvertimeHours() {
        return overtimeHours;
    }
    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double calculateSalary() {
        return getCoefficientSalary() * 3000000 + getOvertimeHours() * 200000;
    }
    public void displayInformation() {
        System.out.printf("%-6s%-25s%-6s%-20s%-20s%-20s%-20s%-20s%-20s\n", getId(), getName(), getAge(), getCoefficientSalary(), getStartDate(), getDepartmentName(), getNumDaysOff(), getOvertimeHours(), "");
    }
    public void displaySalary() {
        System.out.printf("%-8s%-25s%,.2f\n", getId(), getName(), calculateSalary());
    }
}