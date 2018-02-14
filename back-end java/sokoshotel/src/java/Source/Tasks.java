/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kasperi
 */
@Entity
@Table(name = "tasks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t")
    , @NamedQuery(name = "Tasks.findByTaskID", query = "SELECT t FROM Tasks t WHERE t.taskID = :taskID")
    , @NamedQuery(name = "Tasks.findByDueDate", query = "SELECT t FROM Tasks t WHERE t.dueDate = :dueDate")
    , @NamedQuery(name = "Tasks.findByDueTime", query = "SELECT t FROM Tasks t WHERE t.dueTime = :dueTime")
    , @NamedQuery(name = "Tasks.findByTitle", query = "SELECT t FROM Tasks t WHERE t.title = :title")
    , @NamedQuery(name = "Tasks.findByDetils", query = "SELECT t FROM Tasks t WHERE t.detils = :detils")
    , @NamedQuery(name = "Tasks.findByAttachment", query = "SELECT t FROM Tasks t WHERE t.attachment = :attachment")})
public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taskID")
    private Integer taskID;
    @Basic(optional = false)
    
    @Column(name = "dueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Basic(optional = false)
    
    @Column(name = "dueTime")
    @Temporal(TemporalType.TIME)
    private Date dueTime;
    @Basic(optional = false)
    
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Size(max = 50)
    @Column(name = "detils")
    private String detils;
    @Size(max = 50)
    @Column(name = "attachment")
    private String attachment;
    @JoinColumn(name = "department", referencedColumnName = "departmentID")
    @ManyToOne(optional = false)
    private Departments department;
    @JoinColumn(name = "place", referencedColumnName = "placeID")
    @ManyToOne(optional = false)
    private Place place;
    @JoinColumn(name = "taskStatus", referencedColumnName = "taskStatusID")
    @ManyToOne(optional = false)
    private Taskstatus taskStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskNumber")
    private Collection<Taskhistory> taskhistoryCollection;

    public Tasks() {
    }

    public Tasks(Integer taskID) {
        this.taskID = taskID;
    }

    public Tasks(Integer taskID, Date dueDate, Date dueTime, String title) {
        this.taskID = taskID;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.title = title;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetils() {
        return detils;
    }

    public void setDetils(String detils) {
        this.detils = detils;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Taskstatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Taskstatus taskStatus) {
        this.taskStatus = taskStatus;
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
        hash += (taskID != null ? taskID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.taskID == null && other.taskID != null) || (this.taskID != null && !this.taskID.equals(other.taskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Source.Tasks[ taskID=" + taskID + " ]";
    }
    
}
