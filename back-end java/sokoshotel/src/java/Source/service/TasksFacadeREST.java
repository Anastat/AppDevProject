/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source.service;

import Source.Tasks;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

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

    @POST
    @Path("sortnew")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByNew(@FormParam("id") int id) {
        List<Tasks> results = em.createNamedQuery("Tasks.findAll", Tasks.class).getResultList();
        List<Tasks> valid = new ArrayList();
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Tasks result : results) {
            if (result.getTaskStatus().getStatusName().equals("New") && result.getDepartment().getDepartmentID() == id) {
                valid.add(result);
            }
        }
        for (Tasks tasks : valid) {
            obj.put("id", tasks.getTaskID());
            obj.put("duedate", tasks.getDueDate());
            obj.put("duetime", tasks.getDueTime());
            obj.put("department", tasks.getDepartment().getDepartmentID());
            obj.put("title", tasks.getTitle());
            obj.put("place", tasks.getPlace().getPlaceID());
            obj.put("taskstatus", tasks.getTaskStatus().getStatusName());
            obj.put("details", tasks.getDetils());
            obj.put("attachment", tasks.getAttachment());
            arr.put(obj);          
            obj = new JSONObject();           
        }
        return arr.toString();
    }

    @POST
    @Path("sortprocess")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByProcess(@FormParam("id") int id) {
        List<Tasks> results = em.createNamedQuery("Tasks.findAll", Tasks.class).getResultList();
        List<Tasks> valid = new ArrayList();
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Tasks result : results) {
            if (result.getTaskStatus().getStatusName().equals("In process") && result.getDepartment().getDepartmentID() == id) {
                valid.add(result);
            }
        }
        for (Tasks tasks : valid) {
            obj.put("id", tasks.getTaskID());
            obj.put("duedate", tasks.getDueDate());
            obj.put("duetime", tasks.getDueTime());
            obj.put("department", tasks.getDepartment().getDepartmentID());
            obj.put("title", tasks.getTitle());
            obj.put("place", tasks.getPlace().getPlaceID());
            obj.put("taskstatus", tasks.getTaskStatus().getStatusName());
            obj.put("details", tasks.getDetils());
            obj.put("attachment", tasks.getAttachment());
            arr.put(obj);          
            obj = new JSONObject();           
        }
        return arr.toString();
    }

    @POST
    @Path("sortdone")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByDone(@FormParam("id") int id) {
        List<Tasks> results = em.createNamedQuery("Tasks.findAll", Tasks.class).getResultList();
        List<Tasks> valid = new ArrayList();
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Tasks result : results) {
            if (result.getTaskStatus().getStatusName().equals("Done") && result.getDepartment().getDepartmentID() == id) {
                valid.add(result);
            }
        }
        for (Tasks tasks : valid) {
            obj.put("id", tasks.getTaskID());
            obj.put("duedate", tasks.getDueDate());
            obj.put("duetime", tasks.getDueTime());
            obj.put("department", tasks.getDepartment().getDepartmentID());
            obj.put("title", tasks.getTitle());
            obj.put("place", tasks.getPlace().getPlaceID());
            obj.put("taskstatus", tasks.getTaskStatus().getStatusName());
            obj.put("details", tasks.getDetils());
            obj.put("attachment", tasks.getAttachment());
            arr.put(obj);          
            obj = new JSONObject();           
        }
        return arr.toString();
    }

    @POST
    @Path("sortcancelled")
    @Produces(MediaType.APPLICATION_JSON)
    public String sortByCancelled(@FormParam("id") int id) {
        List<Tasks> results = em.createNamedQuery("Tasks.findAll", Tasks.class).getResultList();
        List<Tasks> valid = new ArrayList();
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Tasks result : results) {
            if (result.getTaskStatus().getStatusName().equals("Cancelled") && result.getDepartment().getDepartmentID() == id) {
                valid.add(result);
            }
        }
        for (Tasks tasks : valid) {
            obj.put("id", tasks.getTaskID());
            obj.put("duedate", tasks.getDueDate());
            obj.put("duetime", tasks.getDueTime());
            obj.put("department", tasks.getDepartment().getDepartmentID());
            obj.put("title", tasks.getTitle());
            obj.put("place", tasks.getPlace().getPlaceID());
            obj.put("taskstatus", tasks.getTaskStatus().getStatusName());
            obj.put("details", tasks.getDetils());
            obj.put("attachment", tasks.getAttachment());
            arr.put(obj);          
            obj = new JSONObject();           
        }
        return arr.toString();
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
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Tasks find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tasks> findAll() {
        return super.findAll();
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
