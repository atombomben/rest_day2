/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import entities.*;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author peter
 */
public class EmployeeFacade {
    
    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getEmployeeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long employeeCount = (long)em.createQuery("SELECT COUNT(e) FROM Employee e").getSingleResult();
            return employeeCount;
        }finally{  
            em.close();
        }
    }
    
    public List<Employee> getAllEmployees() {
        
        EntityManager em = getEntityManager();
        
        try {
        TypedQuery<Employee> q1 = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = q1.getResultList();
        return employees;
        } finally {
            em.close();
        }
        
    }
    
    public Employee getEmployeeById(long id){
        
        EntityManager em = getEntityManager();
        
        try {
        TypedQuery<Employee> q1 = em.createQuery("SELECT e FROM Employee e WHERE e.id = " + id, Employee.class);
        List<Employee> employees = q1.getResultList();
        for (Employee e : employees) {
            return e;
        }
        return null;
           
        } finally {
            em.close();
        }
    }
    
    public Employee create(Employee ep){
        Employee emp = new Employee(ep.getName(), ep.getAddress(), ep.getSalary());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return emp;
    }
    
    public List<Employee> getEmployeeByName (String name) {
        EntityManager em = getEntityManager();
        try {
        TypedQuery<Employee> q1 = em.createQuery("SELECT e FROM Employee e WHERE e.name = :Name", Employee.class);
        q1.setParameter("Name",name);
        List<Employee> employees = q1.getResultList();
        
        return employees;
        
        }finally {
            em.close();
        }
    }
    
    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            
        //int highSalary = (int)em.createQuery("select max(e.salary) from Employee e").getSingleResult();
        //TypedQuery<Employee> q2 = em.createQuery("SELECT e FROM Employee e WHERE e.salary = :highSalary", Employee.class);
        //q2.setParameter("highSalary", highSalary);
        
        TypedQuery<Employee> q2 = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) from Employee e)", Employee.class);
        List<Employee> employees = q2.getResultList();
            
        return employees;
        
        } finally {
            em.close();
        }
    }
    
    
}
