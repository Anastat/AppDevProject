/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source.service;

import Source.Departments;
import Source.Rights;
import Source.Taskhistory;
import Source.Users;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.Response;

/**
 *
 * @author Kasperi
 */
@Stateless
@Path("users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "sokoshotelPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Override
    @Path("newuser")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        super.create(entity);
    }

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_HTML)
    public Response checkLogin(@FormParam("username") String name, @FormParam("password") String pw) throws URISyntaxException {
        /*final Collection<Taskhistory> collection = new ArrayList<>();
        collection.add(new Taskhistory());*/
        List<Users> results = em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", name).getResultList();
        if (!results.isEmpty() && name.equals(results.get(0).getUsername())) {
            if (results.get(0).getPassword().equals(pw)) {
                URL url;
                try {
                    url = new URL("http://localhost:46419/sokoshotel/mainpage.html"); //login succesfull
                    URI uri = url.toURI();
                    return Response.seeOther(uri).build(); //logged in succesfull
                } catch (MalformedURLException ex) {
                    Logger.getLogger(UsersFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                URL url;
                try {
                    url = new URL("http://localhost:46419/sokoshotel/?dc=wrong"); //wrong password
                    URI uri = url.toURI();
                    return Response.seeOther(uri).build();//logged in failed
                } catch (MalformedURLException ex) {
                    Logger.getLogger(UsersFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {

            URL url;
            try {
                url = new URL("http://localhost:46419/sokoshotel/?dc=wrong"); //wrong username
                URI uri = url.toURI();
                return Response.seeOther(uri).build();//logged in failed
            } catch (MalformedURLException ex) {
                Logger.getLogger(UsersFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*Users user = new Users(name,pw,firstname,surname,new Departments(1), new Rights(1));
        super.create(user);*/
        //URL url;
        return null;
        //return "<p>user created</p>";
    }
    
    @GET
    @Path("getRights/{name}")
    public int getRight(@PathParam("name") String name){
        Users results = em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", name).getSingleResult();
        return results.getRights().getRightsID();
    }
    
    @GET
    @Path("getDepartment/{name}")
    public int getDepartment(@PathParam("name") String name){
        Users results = em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", name).getSingleResult();
        return results.getDepartment().getDepartmentID();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Users entity) {
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
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
