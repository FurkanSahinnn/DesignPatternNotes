package factoryMethod.models;

import java.util.ArrayList;
import java.util.List;

public class HR {
    private List<Employee> employeeList = new ArrayList<>();

    public HR() {}

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addNewEmployee(Employee employee) {
        employeeList.add(employee);
    }
}
