package creational.factoryMethod;

import creational.factoryMethod.factory.IDirectorFactory;
import creational.factoryMethod.factory.IEmployeeFactory;
import creational.factoryMethod.factory.IManagerFactory;
import creational.factoryMethod.impls.DirectorFactoryImpl;
import creational.factoryMethod.impls.EmployeeFactoryImpl;
import creational.factoryMethod.impls.ManagerFactoryImpl;
import creational.factoryMethod.models.Employee;
import creational.factoryMethod.models.HR;

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
