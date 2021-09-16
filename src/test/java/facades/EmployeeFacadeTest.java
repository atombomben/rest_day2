package facades;

import utils.EMF_Creator;
import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class EmployeeFacadeTest {

    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;

    public EmployeeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = EmployeeFacade.getEmployeeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
            em.persist(new Employee("Daniel","Odense",20000));
            em.persist(new Employee("Arkadii", "Odense", 0));
            em.persist(new Employee("Muslim", "Odense", 20000));
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testCreateEmployeeMethod() throws Exception {
        facade.create(new Employee("Peter","København",14000));
        assertEquals(4, facade.getEmployeeCount());
    }
    
    @Test
    public void testGetAllEmployeesMethod() throws Exception {
        List<Employee> employees = facade.getAllEmployees();
        assertEquals(3, employees.size());
    }
    
    @Test
    public void testGetEmployeesWithHighestSalary() throws Exception{
        facade.create(new Employee("Peter","København",20000));
        assertEquals(3, facade.getEmployeesWithHighestSalary().size());
    }

    @Test
    public void testGetEmployeeByName() throws Exception{
        List<Employee> employees = facade.getEmployeeByName("Muslim");
        assertEquals(1, employees.size());
    }
    
    //@Test
    public void testGetEmployeeById() throws Exception{
        Employee employee = facade.getEmployeeById(17);
        assertEquals("Daniel", employee.getName());
    }    

}
