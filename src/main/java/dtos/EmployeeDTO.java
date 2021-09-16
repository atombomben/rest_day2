/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class EmployeeDTO {
    
    private long id;
    private String name;
    private String address;

    public EmployeeDTO(Employee employee) {
        if(employee.getId() != null)
            this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
    }

    public static List<Employee> getEmployeesDtos(List<Employee> ep){
        List<Employee> epl = new ArrayList();
        ep.forEach(emp->epl.add(new Employee(emp)));
        return epl;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
