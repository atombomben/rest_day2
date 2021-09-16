/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.EmployeeFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author peter
 */
@Path("employee")
public class EmployeeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }

    private final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private final EmployeeFacade FACADE =  EmployeeFacade.getEmployeeFacade(EMF);
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    /**
     * Retrieves representation of an instance of rest.EmployeeResource
     * @return an instance of java.lang.String
     */
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployees() {
    
    
    return GSON.toJson(FACADE.getAllEmployees());
          
   }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@PathParam("id") long id) {
    
    return GSON.toJson(FACADE.getEmployeeById(id));
          
   }
    
    @Path("/highestpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPaid() {
    
    
    return GSON.toJson(FACADE.getEmployeesWithHighestSalary());
          
   }
    
    @Path("/name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeName(@PathParam("name") String name) {
    
    
    return GSON.toJson(FACADE.getEmployeeByName(name));
          
   }
    
}
