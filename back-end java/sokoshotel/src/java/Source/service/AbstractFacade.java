/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source.service;
import Source.Tasks;
import Source.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Kasperi
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
	public String findAllDepartments (String name) {
       List<Tasks> results = getEntityManager().createNamedQuery("Tasks.findAll", Tasks.class).getResultList();
        Users users = getEntityManager().createNamedQuery("Users.findByUsername", Users.class).setParameter("username", name).getSingleResult();
        List<Tasks> valid = new ArrayList();
        for (Tasks result : results) {
            if (users.getRights().getRightsID()==1) valid.add(result);
            else if (result.getDepartment().getDepartmentID() == users.getDepartment().getDepartmentID()) {
                valid.add(result);
            }
        }
        return putDataToReturn(valid);
    }
    
        public String findAllDepartmentParameter (Integer department) {
        List<Tasks> results = getEntityManager().createNamedQuery("Tasks.findAll", Tasks.class).getResultList();
        List<Tasks> valid = new ArrayList();
        for (Tasks result : results) {
            if (result.getDepartment().getDepartmentID().equals(department)) valid.add(result);
            
        }
        return putDataToReturn(valid);
    }    
    public String findByParameter (String param) {
        List<Tasks> results = getEntityManager().createNamedQuery("Tasks.findAll", Tasks.class).getResultList();
     
        List<Tasks> valid = new ArrayList();
        for (Tasks result : results) {
            if (result.getTaskStatus().getStatusName().equals(param)){
                valid.add(result);
            }
        }
        return putDataToReturn(valid);
    }
    public String findByParameterDepartment (Integer department, String param) {
        List<Tasks> results = getEntityManager().createNamedQuery("Tasks.findAll", Tasks.class).getResultList();
        List<Tasks> valid = new ArrayList();
        for (Tasks result : results) {
            if (result.getTaskStatus().getStatusName().equals(param) && result.getDepartment().getDepartmentID().equals(department)){
                 valid.add(result);
            }
        }
        return putDataToReturn(valid);
    }
    public String findUsersDepartment (String name) {
        Users user = getEntityManager().createNamedQuery("Users.findByUsername", Users.class).setParameter("username", name).getSingleResult();
        return user.getDepartment().getDepartmentID().toString();
    }
    
    public String findAllDepartmentsWithoutParam () {
        List<Tasks> results = getEntityManager().createNamedQuery("Tasks.findAll", Tasks.class).getResultList();
        List<Tasks> valid = new ArrayList();
        for (Tasks result : results) {
            valid.add(result);
            
        }
        return putDataToReturn(valid);
    }
    
    private String putDataToReturn (List<Tasks> valid) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Tasks tasks : valid) {
            obj.put("taskID", tasks.getTaskID());
           obj.put("duedate", tasks.getDueDate());
            obj.put("duetime", tasks.getDueTime());
            obj.put("department", tasks.getDepartment().getDepartmentID());
            obj.put("title", tasks.getTitle());
            obj.put("place", tasks.getPlace().getPlaceName());
            obj.put("taskstatus", tasks.getTaskStatus().getTaskStatusID());
            obj.put("details", tasks.getDetails());
            obj.put("attachment", tasks.getAttachment());
            arr.put(obj);          
            obj = new JSONObject();           
        }
        return arr.toString();
    }
}