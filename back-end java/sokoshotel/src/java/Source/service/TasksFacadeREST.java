/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source.service;

import Source.Departments;
import Source.Place;
import Source.Tasks;
import Source.Taskstatus;
import Source.Users;
import com.sun.xml.ws.developer.servlet.HttpSessionScope;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.faces.annotation.RequestParameterMap;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Kasperi
 */
@Stateless
@Path("tasksrest")
public class TasksFacadeREST extends AbstractFacade<Tasks> {

    @PersistenceContext(unitName = "sokoshotelPU")
    private EntityManager em;

    public TasksFacadeREST() {
        super(Tasks.class);
    }
    
    @GET
    @Path("taskListAll")
    @Produces({MediaType.APPLICATION_JSON})
    public String findAllWithoutParam() {
        return super.findAllDepartmentsWithoutParam();
    }
    
    @GET
    @Path("sortnew")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByNew() {
        return super.findByParameter( "New");
    }

    @GET
    @Path("sortprocess")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByProcess() {
        return super.findByParameter("In process");
    }

    @GET
    @Path("sortdone")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByDone() {
      
        return super.findByParameter("Done");
    }

    @GET
    @Path("sortcancelled")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByCancelled() {
        return super.findByParameter("Cancelled");
    }
    
     @GET
    @Path("sortnew/department{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByNew(@PathParam("id") Integer department) {
        return super.findByParameterDepartment(department, "New");
    }
    @GET
    @Path("sortprocess/department{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByProcess(@PathParam("id") Integer department) {
        return super.findByParameterDepartment(department, "In process");
    }
    
    @GET
    @Path("sortdone/department{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByDone(@PathParam("id") Integer department) {
      
        return super.findByParameterDepartment(department, "Done");
    }
    @GET
    @Path("sortcancelled/department{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByCancelledDepartment(@PathParam("id") Integer department) {
        return super.findByParameterDepartment(department, "Cancelled");
    }
    
    @POST
    @Path("addNewNote")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addNewNote(Tasks t){
        super.create(t);
        return "test";
    }
    

    @POST
    @Path("editNote/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editNote(@PathParam("id") int id, Tasks entity) {
        Tasks old = super.find(id);
        old.setAttachment(entity.getAttachment());
        old.setDepartment(entity.getDepartment());
        old.setDetails(entity.getDetails());
        old.setDueDate(entity.getDueDate());
        old.setDueTime(entity.getDueTime());
        old.setPlace(entity.getPlace());
        old.setTaskStatus(entity.getTaskStatus());
        old.setTitle(entity.getTitle());
        return "test";
    }
    
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Tasks entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Tasks entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("taskID{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Tasks find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("taskList")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Tasks> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("taskListAll/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String findAllWithUselessParam(@PathParam("id") String name) {
        return super.findAllDepartments(name);
    }
    
    @GET
    @Path("taskListAll/department{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String findAllWithUselessParam(@PathParam("id") Integer department) {
        return super.findAllDepartmentParameter(department);
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tasks> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
