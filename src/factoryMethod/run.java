package factoryMethod;

import factoryMethod.factory.IDirectorFactory;
import factoryMethod.factory.IEmployeeFactory;
import factoryMethod.factory.IManagerFactory;
import factoryMethod.impls.DirectorFactoryImpl;
import factoryMethod.impls.EmployeeFactoryImpl;
import factoryMethod.impls.ManagerFactoryImpl;
import factoryMethod.models.Employee;
import factoryMethod.models.HR;

public class run {
    public static void main(String[] args) {
        HR hr = new HR();

        IEmployeeFactory employeeFactory = new EmployeeFactoryImpl();
        IManagerFactory managerFactory = new ManagerFactoryImpl();
        IDirectorFactory directorFactory = new DirectorFactoryImpl();


        hr.addNewEmployee(employeeFactory.create("employee1"));
        hr.addNewEmployee(employeeFactory.create("employee2"));
        hr.addNewEmployee(managerFactory.create("manager1", "Software"));
        hr.addNewEmployee(directorFactory.create("director1", "Management", "Have new software produced."));

        for (Employee employee : hr.getEmployeeList()) {
            System.out.println(employee);
        }
    }
}
