public class Manager extends Staff implements ICalculator {
    private String title;

    public Manager(String id, String name, int age, double coefficientSalary, String startDate, Department department, int numDaysOff, String title) {
        super(id, name, age, coefficientSalary, startDate, department, numDaysOff);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public double calculateSalary() {
        int resAllowance = 0;
        if (title.equalsIgnoreCase("Business Leader")) {
            resAllowance = 8000000;
        }
        if (title.equalsIgnoreCase("Project Leader")) {
            resAllowance = 5000000;
        }
        if (title.equalsIgnoreCase("Technical Leader")) {
            resAllowance = 6000000;
        }
        return getCoefficientSalary() * 5000000 + resAllowance;
    }
    public void displayInformation() {
        System.out.printf("%-6s%-25s%-6s%-20s%-20s%-20s%-20s%-20s%-20s\n", getId(), getName(), getAge(), getCoefficientSalary(), getStartDate(), getDepartmentName(), getNumDaysOff(), "", getTitle());
    }
    public void displaySalary() {
        System.out.printf("%-8s%-25s%,.2f\n", getId(), getName(), calculateSalary());
    }
}