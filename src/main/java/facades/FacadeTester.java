package facades;

import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utils.EMF_Creator;

/**
 *
 * @author Atom
 */
public class FacadeTester {
    
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade ef = EmployeeFacade.getEmployeeFacade(emf);
        ef.create(new Employee("Daniel","Odense",20000));
        ef.create(new Employee("Arkadii", "Odense", 0));
        ef.create(new Employee("Muslim", "Odense", 20000));
    }
    
    public static void checkSalary(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade ef = EmployeeFacade.getEmployeeFacade(emf);
        
        List<Employee> highSalaryEmployee = ef.getEmployeesWithHighestSalary();
        System.out.println(highSalaryEmployee.size());
    }
    
    public static void main(String[] args) {
        populate();
        checkSalary();
        
    }
    
}
