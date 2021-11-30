import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class HumanResources {
    public static ArrayList<Staff> listStaff = new ArrayList<>(); //create arraylist for all staffs

    /* create default department */
    public static ArrayList<Department> listDepartment = new ArrayList<>();
    public static Department business = new Department("BIZ", "Business", 0);
    public static Department project = new Department("PROJ", "Project", 0);
    public static Department itSolutions = new Department("ITS", "IT Solutions", 0);

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        dummy(); //create some existing staffs and department before
        menu(); //main flow of the program
    }
    public static void dummy() {
        listStaff.add(new Employee("E01","Nguyen Van A",23,1.5, "12-02-2020", business,1,30));
        listStaff.add(new Employee("E02","Tran Van C",27,1.5, "01-06-2017", itSolutions,4,25));
        listStaff.add(new Manager("M03","Le Thi B",33,2.1,"29-09-2014", project,6,"Project Leader"));
        listStaff.add(new Manager("M04","Pham Thi D",40,2.1,"16-07-2015", business,5,"Business Leader"));
        listDepartment.add(business);
        listDepartment.add(project);
        listDepartment.add(itSolutions);
        for (Staff staff : listStaff) {
            if (staff.getDepartment().equals(business)) {
                business.setNumOfStaffs(business.getNumOfStaffs() + 1);
            }
            if (staff.getDepartment().equals(project)) {
                project.setNumOfStaffs(project.getNumOfStaffs() + 1);
            }
            if (staff.getDepartment().equals(itSolutions)) {
                itSolutions.setNumOfStaffs(itSolutions.getNumOfStaffs() + 1);
            }
        }
    }

    public static void menu() {
        do {
            System.out.println("1. Company staff information");
            System.out.println("2. Department information");
            System.out.println("3. List staff in each department");
            System.out.println("4. Add new staff");
            System.out.println("5. Search staff");
            System.out.println("6. Staff salary in descending order");
            System.out.println("7. Staff salary in ascending order");
            System.out.println("0. Exit");
            System.out.println("------------------------------------------------------------------------");
            System.out.print("Please choose a feature: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    showAllStaffs();
                    break;
                case 2:
                    showAllDep();
                    break;
                case 3:
                    showStaffByDep();
                    break;
                case 4:
                    addStaff();
                    break;
                case 5:
                    searchStaff();
                    break;
                case 6:
                    sortDescending();
                    break;
                case 7:
                    sortAscending();
                    break;
                case 0:
                    return;
            }
        } while(true);
    }

    public static void showAllStaffs() {
        System.out.println("List of All Staffs: ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-6s%-25s%-6s%-20s%-20s%-20s%-20s%-20s%-20s\n","ID", "Name", "Age", "Coefficient Salary", "Start Date", "Department", "Days Off", "Overtime Hours", "Title");
        for (Staff staff : listStaff) {
            staff.displayInformation();
        }
        System.out.println();
    }

    public static void showAllDep() {
        System.out.println("List of Departments: ");
        System.out.println("---------------------");
        for (Department department : listDepartment) {
            System.out.println(department.toString() + "\n");
        }
    }

    /* create arraylist of staff in each department */
    public static ArrayList<Staff> getListStaff(Department department) {
        ArrayList<Staff> resultList = new ArrayList<>();
        for (Staff staff : listStaff) {
            if (department.equals(staff.getDepartment())) {
                resultList.add(staff);
            }
        }
        return resultList;
    }
    public static void showStaffByDep() {
        for (Department department : listDepartment) {
            System.out.println(department.toString());
            System.out.printf("%-6s%-25s%-6s%-20s%-20s%-20s%-20s%-20s%-20s\n","ID", "Name", "Age", "Coefficient Salary", "Start Date", "Department", "Days Off", "Overtime Hours", "Title");
            ArrayList<Staff> staffByDep = getListStaff(department);
            for (Staff staff : staffByDep) {
                staff.displayInformation();
            }
            System.out.println();
        }
    }

    /* validate id input */
    public static boolean validateStaffById (String id) {
        for (Staff staff : listStaff) {
            if (staff.getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }
    public static void addStaff() {
        do {
            System.out.println("Please choose: 1 - Employee. 2 - Manager");
            int choice = sc.nextInt();

            System.out.print("ID: ");
            String id = sc.next();

            while (!validateStaffById(id)) {
                System.out.println("There is an existing staff with this ID, please input again!");
                System.out.print("ID: ");
                id = sc.next();
            }

            System.out.print("Name: ");
            sc = new Scanner(System.in);
            String name = sc.nextLine();

            System.out.print("Age: ");
            int age = sc.nextInt();

            System.out.print("Coefficient Salary: ");
            double coefficientSalary = sc.nextDouble();

            System.out.print("Start Date (dd-MM-yyyy): ");
            String startDate = null;
            boolean input;
            /* validate date input */
            do {
                try {
                    startDate = sc.next();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate.parse(startDate, format);
                    String[] s = startDate.split("-");
                    int day = Integer.parseInt(s[0]);
                    int month = s.length > 1 ? Integer.parseInt(s[1]) : 1;
                    int year = s.length > 2 ? Integer.parseInt(s[2]) : 1;
                    LocalDate.of(year, month, day);
                    input = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage() + ", please enter again!");
                    System.out.print("Start Date (dd-MM-yyyy): ");
                    input = false;
                }
            } while(!input);

            System.out.println("Choose Department : 1 - Business. 2 - Project. 3 - IT Solutions ");
            Department department = null;
            int dep = sc.nextInt();
            switch (dep) {
                case 1:
                    department = business;
                    department.setNumOfStaffs(department.getNumOfStaffs() + 1);
                    break;
                case 2:
                    department = project;
                    department.setNumOfStaffs(department.getNumOfStaffs() + 1);
                    break;
                case 3:
                    department = itSolutions;
                    department.setNumOfStaffs(department.getNumOfStaffs() + 1);
                    break;
            }

            System.out.print("Days Off: ");
            int numDaysOff = sc.nextInt();

            /* add Employee featured field and add to Staff arraylist */
            if (choice == 1) {
                System.out.print("Overtime Hours: ");
                int overtimeHours = sc.nextInt();
                Employee employee = new Employee(id, name, age, coefficientSalary, startDate, department, numDaysOff, overtimeHours);
                listStaff.add(employee);
            }
            /* add Manager featured field and add to Staff arraylist */
            if (choice == 2) {
                System.out.println("Choose Title: 1 - Business Leader. 2 - Project Leader. 3 - Technical Leader");
                String title = "";
                int titleChoice = sc.nextInt();
                switch (titleChoice) {
                    case 1:
                        title = "Business Leader";
                        break;
                    case 2:
                        title = "Project Leader";
                        break;
                    case 3:
                        title = "Technical Leader";
                        break;
                }
                Manager manager = new Manager(id, name, age, coefficientSalary, startDate, department, numDaysOff, title);
                listStaff.add(manager);
            }
            System.out.println("Do you want to continue? (Y=yes, N=no)");
            String choose = sc.next();
            if (!choose.equalsIgnoreCase("Y")) {
                break;
            }
        } while(true);
    }

    /* return search results by id */
    public static int searchById(String id) {
        for (int i = 0; i < listStaff.size(); i++) {
            Staff staff = listStaff.get(i);
            if (staff.getId().equalsIgnoreCase(id)) {
                staff.displayInformation();
                return i;
            }
        }
        return -1;
    }
    /* return search results by name */
    public static ArrayList<Staff> searchByName(String name) {
        ArrayList<Staff> resultList = new ArrayList<>();
        for (Staff staff : listStaff) {
            if (staff.getName().contains(name)) {
                resultList.add(staff);
                staff.displayInformation();
            }
        }
        return resultList;
    }
    public static void searchStaff() {
        do {
            System.out.println("Please enter: 1 - Staff ID. 2 - Staff Name");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.print("ID: ");
                String id = sc.next();
                System.out.printf("%-6s%-25s%-6s%-20s%-20s%-20s%-20s%-20s%-20s\n","ID", "Name", "Age", "Coefficient Salary", "Start Date", "Department", "Days Off", "Overtime Hours", "Title");
                int pos = searchById(id);
                if (pos < 0) {
                    System.out.println("Staff with this ID does not exist!");
                }
            }
            if (choice == 2) {
                System.out.print("Name: ");
                sc = new Scanner(System.in);
                String name = sc.nextLine();
                System.out.printf("%-6s%-25s%-6s%-20s%-20s%-20s%-20s%-20s%-20s\n","ID", "Name", "Age", "Coefficient Salary", "Start Date", "Department", "Days Off", "Overtime Hours", "Title");
                ArrayList<Staff> list = searchByName(name);
                if (list.size() == 0) {
                    System.out.println("Staff with this name does not exist!");
                }
            }
            System.out.println("Do you want to continue? (Y=yes, N=no)");
            String choose = sc.next();
            if(!choose.equalsIgnoreCase("Y")) {
                break;
            }
        } while(true);

    }

    /* get staff salary, distinguished by type */
    public static double getStaffSalary(Staff staff) {
        return ((ICalculator) staff).calculateSalary();
    }
    /* sort salary by descending order */
    public static void sortDescending() {
        listStaff.sort((o1, o2) -> (int) (getStaffSalary(o2) - getStaffSalary(o1)));
        System.out.println("SALARY TABLE SORT IN DESCENDING ORDER");
        System.out.printf("%-8s%-25s%-25s\n", "ID", "Name", "Salary");
        for (Staff staff : listStaff) {
            staff.displaySalary();
        }
        System.out.println();
    }
    /* sort salary by ascending order */
    public static void sortAscending() {
        listStaff.sort((o1, o2) -> (int) (getStaffSalary(o1) - getStaffSalary(o2)));
        System.out.println("SALARY TABLE SORT IN ASCENDING ORDER");
        System.out.printf("%-8s%-25s%-25s\n", "ID", "Name", "Salary");
        for (Staff staff : listStaff) {
            staff.displaySalary();
        }
        System.out.println();
    }
}