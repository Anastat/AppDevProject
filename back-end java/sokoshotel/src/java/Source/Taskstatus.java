/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kasperi
 */
@Entity
@Table(name = "taskstatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taskstatus.findAll", query = "SELECT t FROM Taskstatus t")
    , @NamedQuery(name = "Taskstatus.findByTaskStatusID", query = "SELECT t FROM Taskstatus t WHERE t.taskStatusID = :taskStatusID")
    , @NamedQuery(name = "Taskstatus.findByStatusName", query = "SELECT t FROM Taskstatus t WHERE t.statusName = :statusName")})
public class Taskstatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taskStatusID")
    private Integer taskStatusID;
    @Basic(optional = false)
    
    @Size(min = 1, max = 50)
    @Column(name = "statusName")
    private String statusName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskStatus")
    private Collection<Tasks> tasksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "changes")
    private Collection<Taskhistory> taskhistoryCollection;

    public Taskstatus() {
    }

    public Taskstatus(Integer taskStatusID) {
        this.taskStatusID = taskStatusID;
    }

    public Taskstatus(Integer taskStatusID, String statusName) {
        this.taskStatusID = taskStatusID;
        this.statusName = statusName;
    }

    public Integer getTaskStatusID() {
        return taskStatusID;
    }

    public void setTaskStatusID(Integer taskStatusID) {
        this.taskStatusID = taskStatusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @XmlTransient
    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    @XmlTransient
    public Collection<Taskhistory> getTaskhistoryCollection() {
        return taskhistoryCollection;
    }

    public void setTaskhistoryCollection(Collection<Taskhistory> taskhistoryCollection) {
        this.taskhistoryCollection = taskhistoryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskStatusID != null ? taskStatusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taskstatus)) {
            return false;
        }
        Taskstatus other = (Taskstatus) object;
        if ((this.taskStatusID == null && other.taskStatusID != null) || (this.taskStatusID != null && !this.taskStatusID.equals(other.taskStatusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Source.Taskstatus[ taskStatusID=" + taskStatusID + " ]";
    }
    
}
